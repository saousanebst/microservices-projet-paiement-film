package fr.formation.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import fr.formation.model.Film;

public interface FilmRepository extends JpaRepository<Film, String>{

    //ici faudra creer methode getFilmById
	//Film getFilmById(String id);
    
    

}
