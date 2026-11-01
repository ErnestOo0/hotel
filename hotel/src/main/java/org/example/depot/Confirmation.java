package org.example.depot;

public class Confirmation {
    private String refReservation;
    private String idDemande;
    private boolean dejaReserve;

    public Confirmation() {}
    public Confirmation(String idDemande){
        this.idDemande = idDemande;
        dejaReserve = false;
    }

    public boolean isDejaReserve() {
        return dejaReserve;
    }

    public void setDejaReserve(boolean dejaReserve) {
        this.dejaReserve = dejaReserve;
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
