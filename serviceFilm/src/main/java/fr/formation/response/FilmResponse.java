package fr.formation.response;

public class FilmResponse {
    
    private String id;
    private String nom;
    private String description;
    private int nombreVue;
    private double prixLocation;

    private String userId;


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
    public double getPrixLocation() {
        return prixLocation;
    }
    public void setPrixLocation(double prixLocation) {
        this.prixLocation = prixLocation;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
