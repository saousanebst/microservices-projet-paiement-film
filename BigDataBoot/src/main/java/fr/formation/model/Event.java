package fr.formation.model;

public class Event {
    

    private String date ; 

    private double bz; 

    private double variationMagnetique;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
   
}
