package org.example.depot;

public class Offre {
    private String nomHotel;
    private String nomChambre;
    private DateDecomposee dateArrivee;
    private DateDecomposee dateRetour;
    private double prix;
    private String adresseHotel;

    public Offre() {}

    public Offre(String nomHotel, String nomChambre, DateDecomposee dateArrivee, DateDecomposee dateRetour, double prix, String adresseHotel) {
        this.nomHotel = nomHotel;
        this.nomChambre = nomChambre;
        this.dateArrivee = dateArrivee;
        this.dateRetour = dateRetour;
        this.prix = prix;
        this.adresseHotel = adresseHotel;
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

    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public String getAdresseHotel() {
        return adresseHotel;
    }

    public void setAdresseHotel(String adresseHotel) {
        this.adresseHotel = adresseHotel;
    }
}
