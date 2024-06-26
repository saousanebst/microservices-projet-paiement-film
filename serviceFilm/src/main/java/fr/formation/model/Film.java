package fr.formation.model;

import java.math.BigDecimal;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "film")
public class Film {
    
    @Id
    @UuidGenerator
    private String id;
    
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;
    
    private int nombreVue;

    @Column(nullable = false)
    private BigDecimal prixLocation;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getNombreVue() {
        return nombreVue;
    }
    public void setNombreVue(int nombreVue) {
        this.nombreVue = nombreVue;
    }
    
    public void incrementNombreVue() {
        this.nombreVue++;
    }
    public BigDecimal getPrixLocation() {
        return prixLocation;
    }
    public void setPrixLocation(BigDecimal prixLocation) {
        this.prixLocation = prixLocation;
    }
    
   
}
