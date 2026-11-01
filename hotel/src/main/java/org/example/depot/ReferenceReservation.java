package org.example.depot;

public class ReferenceReservation {
    private Chambre chambre;
    private Reservation reservation;

    public ReferenceReservation(){}

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
