package org.example.pointAcces.arguments;

import org.example.depot.Offre;
import org.example.depot.Voyageur;

public class ArgumentsReservation {
    private String idDemande;
    private Agence agence;
    private Offre offre;
    private Voyageur voyageur;
    private InfoBancaires infoBancaires;
    private String adresseHotel;

    public ArgumentsReservation() {}

    public String getIdDemande() {
        return idDemande;
    }
    public void setIdDemande(String idDemande) {
        this.idDemande = idDemande;
    }

    public Agence getAgence() {
        return agence;
    }
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Offre getOffre() {
        return offre;
    }
    public void setOffre(Offre offre) {
        this.offre = offre;
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

    public String getAdresseHotel(){ return adresseHotel;}
    public void setAdresseHotel(String adresse){this.adresseHotel=adresse;}
}
