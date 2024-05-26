package fr.formation.api;
import fr.formation.service.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventApiController {

    // Injection du service HdfsService
    @Autowired
    private HdfsService hdfsService;

    // Méthode pour récupérer les événements notables pour une année donnée
    @GetMapping("/{year}")
    public ResponseEntity<List<String>> getNotableEventsByYear(@PathVariable int year) {
        try {
            // Appel du service pour récupérer les événements notables
            List<String> events = hdfsService.getNotableEvents(year);
            // Retourne la liste des événements avec un statut OK
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            // En cas d'erreur, retourne une réponse avec un statut d'erreur interne du serveur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
