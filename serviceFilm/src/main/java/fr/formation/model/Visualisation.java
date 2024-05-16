package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import fr.formation.enumerator.LocationEtat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "visualisation")
public class Visualisation {
    
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String filmId;

    @Enumerated(EnumType.STRING)
    private LocationEtat etat;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getFilmId() {
        return filmId;
    }
    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
    public LocationEtat getEtat() {
        return etat;
    }
    public void setEtat(LocationEtat etat) {
        this.etat = etat;
    }
      
}




