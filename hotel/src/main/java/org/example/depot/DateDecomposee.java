package org.example.depot;

import java.time.LocalDate;

public class DateDecomposee {

    private int jour;
    private int mois;
    private int annee;

    public DateDecomposee() { }

    public DateDecomposee(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public int getJour() { return jour; }
    public void setJour(int jour) { this.jour = jour; }

    public int getMois() { return mois; }
    public void setMois(int mois) { this.mois = mois; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public boolean isAvant(DateDecomposee dd) {
        return this.toLocalDate().isBefore(dd.toLocalDate());
    }

    public boolean isApres(DateDecomposee dd) {
        return this.toLocalDate().isAfter(dd.toLocalDate());
    }

    public boolean passee(){
        return this.toLocalDate().isBefore(LocalDate.now());
    }

    public LocalDate toLocalDate() {
        return LocalDate.of(annee, mois, jour);
    }

    public String toString() {
        return annee+"."+mois+"."+jour;
    }
}