package fr.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.formation.command.VisualisationCommand;
import fr.formation.enumerator.LocationEtat;
import fr.formation.model.Film;
import fr.formation.model.Visualisation;
import fr.formation.repository.FilmRepository;
import fr.formation.repository.VisualisationRepository;
import fr.formation.request.CreateFilmRequest;
import fr.formation.request.CreateVisualisationRequest;
import fr.formation.response.FilmResponse;
import fr.formation.response.VisualisationResponse;

@RestController
@RequestMapping("/api/film")
@CrossOrigin("*")
public class FilmApiController {
    
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private VisualisationRepository visualisationRepository;

    @GetMapping
    public List<FilmResponse> findAll() {
        
        return this.filmRepository.findAll().stream()           
            .map(this::map)
            .toList();
    }

    @GetMapping("/vis")
    public List<VisualisationResponse> findAllByEtat() {
        
        return this.visualisationRepository.findAllByEtat(LocationEtat.ACCEPTED).stream()      
            .map(this::map)
            .toList();
    }

    private VisualisationResponse map(Visualisation visualisation){

        VisualisationResponse responseVis = new VisualisationResponse();

        BeanUtils.copyProperties(visualisation, responseVis);
              
        String name = this.circuitBreakerFactory.create("paiementService").run(
            () -> this.restTemplate.getForObject("lb://paiement-service/api/paiement/" + visualisation.getId()+ "/name", String.class)
            ,
            t -> "no name"
        );

        responseVis.setUserId(name);      
        return responseVis;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String ajoutFilm(@RequestBody CreateFilmRequest request) {

        Film film = new Film();
        
        BeanUtils.copyProperties(request, film);

        this.filmRepository.save(film);
       
        return film.getId();

    }

    private FilmResponse map(Film film){

        FilmResponse response = new FilmResponse();

        BeanUtils.copyProperties(film, response);
              
        String name = this.circuitBreakerFactory.create("paiementService").run(
            () -> this.restTemplate.getForObject("lb://paiement-service/api/paiement/" + film.getId()+ "/name", String.class)
            ,
            t -> "no name"
        );

        response.setUserId(name);      
        return response;
    }

  
    @PostMapping("/louer")
    @ResponseStatus(HttpStatus.CREATED)
    public String louerFilm(@RequestBody CreateVisualisationRequest requestVisualisation) {
        

        // Créer une nouvelle instance de Visualisation en attente
        Visualisation visualisation = new Visualisation();
        
        BeanUtils.copyProperties(requestVisualisation, visualisation);

        visualisation.setEtat(LocationEtat.ATTENTE);

        // Sauvegarder la visualisation dans la base de données
        Visualisation savedVisualisation = visualisationRepository.save(visualisation);

        VisualisationCommand visualisationCommand = new VisualisationCommand();

        visualisationCommand.setId(savedVisualisation.getId());
        visualisationCommand.setUserId(savedVisualisation.getUserId());
        visualisationCommand.setPrixLocation(requestVisualisation.getPrixLocation());

        // Envoyer un événement de demande de location au service paiement
        streamBridge.send("verif.credit.location", visualisationCommand);
        
    
        // Retourner l'ID de la visualisation créée
        return savedVisualisation.getId();
    }

     
}

   


