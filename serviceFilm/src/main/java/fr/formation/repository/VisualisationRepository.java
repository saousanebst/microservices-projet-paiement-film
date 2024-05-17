package fr.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.enumerator.LocationEtat;
import fr.formation.model.Visualisation;

public interface VisualisationRepository extends JpaRepository<Visualisation, String>{

    public List<Visualisation> findAllByEtat(LocationEtat etat);

    

}
