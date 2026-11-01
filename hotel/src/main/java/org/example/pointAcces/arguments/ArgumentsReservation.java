package org.example.pointAcces.arguments;

public class ArgumentsReservation {
    private Agence agence;
    private String idOffre;
    private Voyageur voyageur;
    private InfoBancaires infoBancaires;

    public ArgumentsReservation() {}

    public Agence getAgence() {
        return agence;
    }
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public String getIdOffre() {
        return idOffre;
    }
    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public Voyageur getVoyageur() {
        return voyageur;
    }
    public void setVoyageur(Voyageur voyageur) {
        this.voyageur = voyageur;
    }

    public InfoBancaires getInfoBancaires() {
        return infoBancaires;
    }
    public void setInfoBancaires(InfoBancaires infoBancaires) {
        this.infoBancaires = infoBancaires;
    }
}
