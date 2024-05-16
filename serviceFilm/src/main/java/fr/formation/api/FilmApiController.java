package fr.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.formation.model.Film;
import fr.formation.repository.FilmRepository;
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
            () -> this.restTemplate.getForObject("lb://paiement-service/api/paiement/" + film.getUserId() + "/name", String.class)
            ,
            t -> "- no name -"
        );

        response.setUserId(name);
        
        return response;
    }

    
    @PostMapping("/verif-location")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FilmResponse> locationFilm(@RequestParam String id, @RequestParam String userId) {
        List<Film> films = this.filmRepository.findAllByIdAndUser(id, userId);
        return films.stream()
                    .map(this::map) // Utilisez votre méthode map pour mapper les films en FilmResponse
                    .toList();
    }

}
