package org.example.pointAcces;

import java.time.LocalDate;
import java.util.Date;

public class ArgumentsRecherche {
    private String ville;
    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private int nbPersonnes;
    private int nbEtoiles;
    private long prixMax;

    public ArgumentsRecherche() {}

    public ArgumentsRecherche(String ville, LocalDate dateArrivee, LocalDate dateDepart, int nbPersonnes, int nbEtoiles, long prixMax) {
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

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }
    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }
    public void setDateDepart(LocalDate dateDepart) {
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
