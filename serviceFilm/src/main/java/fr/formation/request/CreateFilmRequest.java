package fr.formation.request;

import java.math.BigDecimal;

public class CreateFilmRequest {
    
    private String nom;
    private String description;
    private int nombreVue;
    private BigDecimal prixLocation;

    private String userId;

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
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BigDecimal getPrixLocation() {
        return prixLocation;
    }
    public void setPrixLocation(BigDecimal prixLocation) {
        this.prixLocation = prixLocation;
    }

}
