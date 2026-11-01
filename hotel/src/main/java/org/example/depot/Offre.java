package org.example.depot;

public class Offre {
    private String nomHotel;
    private String nomChambre;
    private DateDecomposee dateArrivee;
    private DateDecomposee dateRetour;
    private long prix;

    public Offre() {}

    public Offre(String nomHotel, String nomChambre, DateDecomposee dateArrivee, DateDecomposee dateRetour, long prix) {
        this.nomHotel = nomHotel;
        this.nomChambre = nomChambre;
        this.dateArrivee = dateArrivee;
        this.dateRetour = dateRetour;
        this.prix = prix;
    }

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

    public long getPrix() {
        return prix;
    }
    public void setPrix(long prix) {
        this.prix = prix;
    }
}
