package fr.formation.command;

public class CreateFilmCommand {
    
    private String filmId;
    private String paiementId;

    public String getFilmId() {
        return filmId;
    }
    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
    public String getPaiementId() {
        return paiementId;
    }
    public void setPaiementId(String paiementId) {
        this.paiementId = paiementId;
    }
  
}
