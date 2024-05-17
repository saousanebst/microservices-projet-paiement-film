package fr.formation.response;

import java.math.BigDecimal;

public class VisualisationResponse {
    
    private String id;
    private String userId;
    private String filmId; 
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
    public String getFilmId() {
        return filmId;
    }
    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
    public BigDecimal getPrixLocation() {
        return prixLocation;
    }
    public void setPrixLocation(BigDecimal prixLocation) {
        this.prixLocation = prixLocation;
    }


    
}
