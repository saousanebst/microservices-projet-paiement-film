package fr.formation.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @Column(nullable=false)
    private LocalDateTime date ; 

    private double bz; 

    private double variationMagnetique;

    public Event(){};


    public Event(LocalDateTime date, double bz, double variationMagnetique) {
        this.date = date;
        this.bz = bz;
        this.variationMagnetique = variationMagnetique;
    }

 

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getBz() {
        return bz;
    }

    public void setBz(double bz) {
        this.bz = bz;
    }

    public double getVariationMagnetique() {
        return variationMagnetique;
    }

    public void setVariationMagnetique(double variationMagnetique) {
        this.variationMagnetique = variationMagnetique;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



   
}
