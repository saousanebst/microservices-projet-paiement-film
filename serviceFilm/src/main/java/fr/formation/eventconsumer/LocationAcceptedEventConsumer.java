package fr.formation.eventconsumer;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.enumerator.LocationEtat;
import fr.formation.model.Visualisation;
import fr.formation.repository.VisualisationRepository;

@Component("onLocationValided")
public class LocationAcceptedEventConsumer implements Consumer<String>{

    @Autowired
    private VisualisationRepository visualisationRepository;


    @Override
    public void accept(String visualisationId) {
        
        Optional<Visualisation> optVisualisation = this.visualisationRepository.findById(visualisationId);

        if (optVisualisation.isPresent()) {
            Visualisation visualisation = optVisualisation.get();
            
            visualisation.setEtat(LocationEtat.OK);

            this.visualisationRepository.save(visualisation);
        }
    }
    


}
