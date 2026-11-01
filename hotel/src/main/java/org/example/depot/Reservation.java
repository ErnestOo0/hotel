package org.example.depot;

import org.example.pointAcces.arguments.Agence;

public class Reservation {
    private String nomAgence;
    private Voyageur client;
    private DateDecomposee dateArrivee;
    private DateDecomposee dateDepare;

    public Reservation() {}

    public Reservation(DateDecomposee dateArrivee, DateDecomposee dateDepare) {
        this.dateArrivee = dateArrivee;
        this.dateDepare = dateDepare;
    }

    public Reservation(String nomAgence ,Voyageur client, DateDecomposee dateArrivee, DateDecomposee dateDepare) {
        this.nomAgence = nomAgence;
        this.client = client;
        this.dateArrivee = dateArrivee;
        this.dateDepare = dateDepare;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public Voyageur getClient() {
        return client;
    }
    public void setClient(Voyageur client) {
        this.client = client;
    }

    public DateDecomposee getDateArrivee() {
        return dateArrivee;
    }
    public void setDateArrivee(DateDecomposee dateArrivee) {}

    public DateDecomposee getDateDepare() {
        return dateDepare;
    }
    public void setDateDepare(DateDecomposee dateDepare) {}

    public boolean conflict(Reservation resa) {

        return dateArrivee.toLocalDate().isBefore(resa.getDateDepare().toLocalDate())
                && resa.getDateArrivee().toLocalDate().isBefore(dateDepare.toLocalDate());
    }

    public boolean datesValides(){
        return dateDepare.isApres(dateArrivee) && !dateArrivee.passee();
    }

}
