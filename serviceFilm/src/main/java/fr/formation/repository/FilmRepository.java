package fr.formation.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.enumerator.LocationEtat;
import fr.formation.model.Film;

public interface FilmRepository extends JpaRepository<Film, String>{

    
	//Film FindFilmById(String id);

    //public List<Film> findAllByUserIdAndEtat(String userId, LocationEtat etat);
    

}
