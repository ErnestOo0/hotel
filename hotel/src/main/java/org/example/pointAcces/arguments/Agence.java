package org.example.pointAcces.arguments;

public class Agence {
    private String nomAgence;
    private String secretAgence;
    private double coefTarif;

    public Agence(){}

    public String getNomAgence() {
        return nomAgence;
    }
    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public String getSecretAgence() {
        return secretAgence;
    }
    public void setSecretAgence(String secretAgence) {
        this.secretAgence = secretAgence;
    }


    public double getCoefTarif() {
        return coefTarif;
    }

    public void setCoefTarif(double coefTarif) {
        this.coefTarif = coefTarif;
    }

}
