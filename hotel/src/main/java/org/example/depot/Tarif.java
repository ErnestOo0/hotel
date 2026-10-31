package org.example.depot;

public class Tarif {
    private String nom;
    private long prix;
    private String devise;

    public Tarif() {}

    public Tarif(String nom, long prix, String devise) {
        this.nom = nom;
        this.prix = prix;
        this.devise = devise;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getPrix() {
        return prix;
    }
    public void setPrix(long prix) {
        this.prix = prix;
    }

    public String getDevise() {
        return devise;
    }
    public void setDevise(String devise) {
        this.devise = devise;
    }
}
