package fr.formation.command;

import java.math.BigDecimal;



public class VisualisationCommand {
    
    private String id;
    private String userId;
    private BigDecimal prixLocation;

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
    public BigDecimal getPrixLocation() {
        return prixLocation;
    }
    public void setPrixLocation(BigDecimal prixLocation) {
        this.prixLocation = prixLocation;
    }
   
    

}
