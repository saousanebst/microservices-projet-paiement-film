package fr.formation.eventconsumer;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.enumerator.LocationEtat;
import fr.formation.model.Film;
import fr.formation.model.Visualisation;
import fr.formation.repository.FilmRepository;
import fr.formation.repository.VisualisationRepository;

@Component("onLocationValidated")
public class LocationAcceptedEventConsumer implements Consumer<String>{

    @Autowired
    private VisualisationRepository visualisationRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void accept(String visualisationId) {
        
        Optional<Visualisation> optVisualisation = this.visualisationRepository.findById(visualisationId);

        if (optVisualisation.isPresent()) {
            Visualisation visualisation = optVisualisation.get();
            
            visualisation.setEtat(LocationEtat.ACCEPTED);

            this.visualisationRepository.save(visualisation);

            //Si location accepté nbVue incremente de 1
            Optional<Film> optFilm = this.filmRepository.findById(visualisation.getFilmId());
            if (optFilm.isPresent()) {
                Film film = optFilm.get();
                film.incrementNombreVue();
                this.filmRepository.save(film);

        
        }
    }
    }
}
