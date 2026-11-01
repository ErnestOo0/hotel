package org.example.depot;

import org.example.logiqueMetier.SecretHasher;

public class AgenceSecret {
    private String nomAgence;
    private SecretHasher.Empreinte secretAgence;
    private double coefTarif;

    public AgenceSecret(){
        coefTarif = 1;
    }

    public String getNomAgence() {
        return nomAgence;
    }
    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public boolean verifSecret(String secret){
        if(secret == null){
            return false;
        }
        return SecretHasher.verifier(secret.toCharArray(), secretAgence);
    }
    public void setSecretAgence(String secretAgence) {
        this.secretAgence = SecretHasher.deriver(secretAgence.toCharArray());
    }
    public SecretHasher.Empreinte getSecretAgence() {
        return secretAgence;
    }

    public double getCoefTarif() {
        return coefTarif;
    }

    public void setCoefTarif(double coefTarif) {
        this.coefTarif = coefTarif;
    }

}