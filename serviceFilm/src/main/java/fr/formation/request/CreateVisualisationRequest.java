package fr.formation.request;

import java.math.BigDecimal;



public class CreateVisualisationRequest {
    
   private String userId;
   private String filmId; 
   private BigDecimal prixLocation;


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
