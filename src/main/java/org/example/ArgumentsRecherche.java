package org.example;

import java.util.Date;

public class ArgumentsRecherche {
    private String ville;
    private Date dateArrivee;
    private Date dateDepart;
    private int nbPersonnes;
    private int nbEtoiles;
    private long prixMax;

    public ArgumentsRecherche() {}

    public ArgumentsRecherche(String ville, Date dateArrivee, Date dateDepart, int nbPersonnes, int nbEtoiles, long prixMax) {
        this.ville = ville;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.nbPersonnes = nbPersonnes;
        this.nbEtoiles = nbEtoiles;
        this.prixMax = prixMax;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }
    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getDateDepart() {
        return dateDepart;
    }
    public void setDateDepart(Date dateDepart) {
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
}
