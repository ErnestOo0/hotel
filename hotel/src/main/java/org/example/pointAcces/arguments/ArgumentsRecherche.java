package org.example.pointAcces.arguments;

import org.example.depot.DateDecomposee;

public class ArgumentsRecherche {
    private Agence agence;
    private String ville;
    private DateDecomposee dateArrivee;// peut etre utiliser son propre type pour ne pas dépendre de java et apres convertir
    private DateDecomposee dateDepart;
    private int nbPersonnes;
    private int nbEtoiles;
    private double prixMax;

    public ArgumentsRecherche() {}

    public ArgumentsRecherche(Agence agence, String ville, DateDecomposee dateArrivee, DateDecomposee dateDepart, int nbPersonnes, int nbEtoiles, long prixMax) {
        this.agence = agence;
        this.ville = ville;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.nbPersonnes = nbPersonnes;
        this.nbEtoiles = nbEtoiles;
        this.prixMax = prixMax;
    }

    public Agence getAgence() {
        return agence;
    }
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public DateDecomposee getDateArrivee() {
        return dateArrivee;
    }
    public void setDateArrivee(DateDecomposee dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public DateDecomposee getDateDepart() {
        return dateDepart;
    }
    public void setDateDepart(DateDecomposee dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }
    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }
    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public double getPrixMax() {
        return prixMax;
    }
    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }
}
