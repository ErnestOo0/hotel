package org.example.pointAcces.arguments;

public class Expiration {
    private int mois;
    private int annee;

    public Expiration(){}

    public int getMois(){
        return mois;
    }
    public void setMois(int mois){
        this.mois = mois;
    }

    public int getAnnee(){
        return annee;
    }
    public void setAnnee(int annee){
        this.annee = annee;
    }
}
