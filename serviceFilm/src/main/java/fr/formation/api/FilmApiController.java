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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.formation.enumerator.LocationEtat;
import fr.formation.model.Film;
import fr.formation.model.Visualisation;
import fr.formation.repository.FilmRepository;
import fr.formation.repository.VisualisationRepository;
import fr.formation.request.CreateFilmRequest;
import fr.formation.response.FilmResponse;

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

   /*
    @PostMapping("/verif-location")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FilmResponse> locationFilm(@RequestParam String id, @RequestParam String userId) {
        
        
        return films.stream()
                    .map(this::map) // Utilisez votre méthode map pour mapper les films en FilmResponse
                    .toList();
    }*/

    /* 
    @PostMapping("/verif-location")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmResponse locationFilm(@RequestParam String id, @RequestParam String userId) {
        // Récupérer les informations du film à partir de son identifiant (id)
        Film film = filmRepository.getFilmById(id);
        
        // Vérifier si l'utilisateur peut visualiser le film
        boolean utilisateurPeutVisualiser = paiementService.verifierLocation(userId, film.getPrixLocation());
        
        // Si l'utilisateur peut visualiser le film, retourner les détails du film
        if (utilisateurPeutVisualiser) {
            return map(film); // Utilisez votre méthode map pour mapper le film en FilmResponse
        } else {
            // Gérer le cas où l'utilisateur ne peut pas visualiser le film
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "L'utilisateur n'est pas autorisé à visualiser ce film.");
        }
    }*/

    @PostMapping("/louer")
    @ResponseStatus(HttpStatus.CREATED)
    public String louerFilm(@RequestParam String userId, @RequestParam String filmId, @RequestParam double prixLocation) {
        

        // Créer une nouvelle instance de Visualisation en attente
        Visualisation visualisation = new Visualisation();
        visualisation.setUserId(userId);
        visualisation.setFilmId(filmId);
        visualisation.setEtat(LocationEtat.ATTENTE);


        // Envoyer un événement de demande de location au service paiement
        streamBridge.send("demande.location", visualisation);
        
        // Sauvegarder la visualisation dans la base de données
        Visualisation savedVisualisation = visualisationRepository.save(visualisation);

        // Retourner l'ID de la visualisation créée
        return savedVisualisation.getId();
    }

     
}

   


