package fr.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Event;
import fr.formation.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventApiController {
    
    @Autowired
    private EventService hdfsService;

    // Endpoint pour obtenir le nombre d'événements notables pour une année donnée
    @GetMapping("/{year}/count")
    public int getNotableEventCount(@PathVariable int year) throws Exception {
        return hdfsService.getNotableEvents(year).size();
    }

    // Endpoint pour obtenir les détails des événements notables pour une année donnée
    @GetMapping("/{year}/details")
    public List<Event> getNotableEventDetails(@PathVariable int year) throws Exception {
        return hdfsService.getNotableEvents(year);
    }

    
}
