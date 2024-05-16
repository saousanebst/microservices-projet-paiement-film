package fr.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Film;

public interface FilmRepository extends JpaRepository<Film, String>{
    
    
    public List<Film> findAllByIdAndUser(String id, String userId);

}
