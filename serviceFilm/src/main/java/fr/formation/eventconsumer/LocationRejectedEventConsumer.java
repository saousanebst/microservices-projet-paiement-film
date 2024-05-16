package fr.formation.eventconsumer;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.repository.VisualisationRepository;

@Component("onLocationRejected")
public class LocationRejectedEventConsumer implements Consumer<String>{

    @Autowired
    private VisualisationRepository visualisationRepository;


    @Override
    public void accept(String visualisationId) {
        
        this.visualisationRepository.deleteById(visualisationId);
    }
    


}
