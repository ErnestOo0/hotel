package org.example.depot;

import java.time.LocalDate;

public class Reservation {
    private LocalDate dateArrivee;
    private LocalDate dateDepare;

    public Reservation(LocalDate dateArrivee, LocalDate dateDepare) {
        this.dateArrivee = dateArrivee;
        this.dateDepare = dateDepare;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }
    public LocalDate getDateDepare() {
        return dateDepare;
    }

    public boolean conflict(Reservation resa) {
        //si l'arrivée pose probleme
        if(dateArrivee.isAfter(resa.getDateArrivee()) && dateArrivee.isBefore(resa.getDateDepare())) {
            return true;
        }
        //si le depart pose probleme
        if(dateDepare.isAfter(resa.getDateArrivee()) && dateDepare.isBefore(resa.getDateDepare())){
            return true;
        }
        return false;
    }
}
