package org.example.depot;

public class Confirmation {
    private String refReservation;
    private String idDemande;
    private boolean dejaReserver;

    public Confirmation() {}
    public Confirmation(String idDemande){
        this.idDemande = idDemande;
        dejaReserver = false;
    }

    public boolean isDejaReserver() {
        return dejaReserver;
    }

    public void setDejaReserver(boolean dejaReserver) {
        this.dejaReserver = dejaReserver;
    }

    public String getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(String idDemande) {
        this.idDemande = idDemande;
    }

    public String getRefReservation() {
        return refReservation;
    }

    public void setRefReservation(String refReservation) {
        this.refReservation = refReservation;
    }
}
