package org.example.depot;

import org.example.logiqueMetier.SecretHasher;

public class AgenceSecret {
    private String nomAgence;
    private SecretHasher.Empreinte secretAgence;
    private double coefTarif;

    public AgenceSecret(){}

    public String getNomAgence() {
        return nomAgence;
    }
    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public boolean verifSecret(String secret){
        return SecretHasher.verifier(secret.toCharArray(), secretAgence);
    }
    public void setSecretAgence(String secretAgence) {
        this.secretAgence = SecretHasher.deriver(secretAgence.toCharArray());
    }

    public double getCoefTarif() {
        return coefTarif;
    }

    public void setCoefTarif(double coefTarif) {
        this.coefTarif = coefTarif;
    }

}