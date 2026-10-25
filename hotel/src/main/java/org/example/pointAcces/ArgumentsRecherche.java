package org.example.pointAcces;

import org.example.depot.DateDecomposee;

public class ArgumentsRecherche {
    private String agence;
    private String ville;
    private DateDecomposee dateArrivee;// peut etre utiliser son propre type pour ne pas dépendre de java et apres convertir
    private DateDecomposee dateDepart;
    private int nbPersonnes;
    private int nbEtoiles;
    private long prixMax;

    public ArgumentsRecherche() {}

    public ArgumentsRecherche(String agence, String ville, DateDecomposee dateArrivee, DateDecomposee dateDepart, int nbPersonnes, int nbEtoiles, long prixMax) {
        this.agence = agence;
        this.ville = ville;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.nbPersonnes = nbPersonnes;
        this.nbEtoiles = nbEtoiles;
        this.prixMax = prixMax;
    }

    public String getAgence() {
        return agence;
    }
    public void setAgence(String agence) {
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

    public long getPrixMax() {
        return prixMax;
    }
    public void setPrixMax(long prixMax) {
        this.prixMax = prixMax;
    }
}
