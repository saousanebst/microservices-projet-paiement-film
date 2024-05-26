package fr.formation.model;

import java.time.LocalDateTime;

public class Event {
    

    private LocalDateTime date ; 

    private double bz; 

    private double variationMagnetique;


    public Event(LocalDateTime date2, double bz, double variationMagnetique) {
        this.date = date2;
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
   
}
