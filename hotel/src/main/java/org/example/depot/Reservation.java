package org.example.depot;

public class Reservation {
    private Voyageur client;
    private DateDecomposee dateArrivee;
    private DateDecomposee dateDepare;

    public Reservation() {}

    public Reservation(DateDecomposee dateArrivee, DateDecomposee dateDepare) {
        this.dateArrivee = dateArrivee;
        this.dateDepare = dateDepare;
    }

    public Reservation(Voyageur client, DateDecomposee dateArrivee, DateDecomposee dateDepare) {
        this.client = client;
        this.dateArrivee = dateArrivee;
        this.dateDepare = dateDepare;
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

        //si l'arrivée pose probleme

        if(dateArrivee.isApres(resa.getDateArrivee()) && dateArrivee.isAvant(resa.getDateDepare())) {
            return true;
        }
        //si le depart pose probleme
        if(dateDepare.isApres(resa.getDateArrivee()) && dateDepare.isAvant(resa.getDateDepare())){
            return true;
        }
        return false;
    }

    public boolean datesValides(){
        return dateDepare.isApres(dateArrivee) && !dateArrivee.passee();
    }

}
