package fr.formation.api;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.service.HdfsService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventApiController {

    // Ajout du logger
    private static final Logger logger = LoggerFactory.getLogger(EventApiController.class);

    // Injection du service HdfsService
    @Autowired
    private HdfsService hdfsService;

    // Méthode pour récupérer les événements notables pour une année donnée
    @GetMapping("/{year}")
    public ResponseEntity<List<String>> getNotableEventsByYear(@PathVariable int year) {
        
        // Log pour indiquer la réception de la requête
        logger.info("Requête reçue pour l'année: {}", year);

        try {
            // Appel du service pour récupérer les événements notables
            List<String> events = hdfsService.getNotableEvents(year);

            // Log pour indiquer le nombre d'événements récupérés
            logger.info("Événements récupérés: {}", events.size()); 

            // Retourne la liste des événements avec un statut OK
            return ResponseEntity.ok(events);

        } catch (Exception e) {

            // En cas d'erreur, log et retourne une réponse avec un statut d'erreur interne du serveur
            logger.error("Erreur lors de la récupération des événements", e);
            
            // En cas d'erreur, retourne une réponse avec un statut d'erreur interne du serveur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
