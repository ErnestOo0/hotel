package org.example.depot;

public class Tarif {
    private String nom;
    private long prix;

    public Tarif(String nom, long prix) {
        this.nom = nom;
        this.prix = prix;
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
}
