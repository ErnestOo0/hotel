package org.example.depot;

public class Offre {
    private String nomHotel;
    private String nomChambre;
    private DateDecomposee dateArrivee;
    private DateDecomposee dateRetour;

    public Offre() {}

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getNomChambre() {
        return nomChambre;
    }

    public void setNomChambre(String nomChambre) {
        this.nomChambre = nomChambre;
    }

    public DateDecomposee getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(DateDecomposee dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public DateDecomposee getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(DateDecomposee dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String toString() {
        return nomHotel+nomChambre+dateArrivee.toString()+dateRetour.toString();
    }
}
