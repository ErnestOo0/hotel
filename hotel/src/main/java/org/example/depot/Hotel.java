package org.example.depot;

import org.example.pointAcces.arguments.Agence;

import java.util.ArrayList;

public class Hotel {
    private String nom;
    private String ville;
    private int etoiles;
    private ArrayList<Chambre> chambres;
    private ArrayList<AgenceSecret> agencesPartenaires;

    public Hotel() {
        this.chambres = new ArrayList<>();
        this.agencesPartenaires = new ArrayList<>();
    }

    public Hotel(String nom, String ville, int etoiles) {
        this.nom = nom;
        this.ville = ville;
        this.etoiles = etoiles;
        this.chambres = new ArrayList<>();
        this.agencesPartenaires = new ArrayList<>();
    }

    public String getVille(){
        return ville;
    }

    public int getEtoiles(){
        return etoiles;
    }

    public ArrayList<Chambre> getChambres(){
        return chambres;
    }

    private boolean numeroLibre(Chambre chambre){
        for(Chambre c : chambres){
            if(c.getNumero() == chambre.getNumero()){
                return false;
            }
        }
        return true;
    }

    public void addChambre(Chambre chambre){
        if(numeroLibre(chambre)){
            chambres.add(chambre);
        }
    }

    public ArrayList<AgenceSecret> getAgencesPartenaires(){
        return agencesPartenaires;
    }

    public void addAgencesPartenaire(AgenceSecret agence){
        if(!agencesPartenaires.contains(agence)){
            agencesPartenaires.add(agence);
        }
    }

    public boolean estAgencePartenaire(Agence agence){
        for(AgenceSecret a : agencesPartenaires){
            if(a.getNomAgence().equals(agence.getNomAgence())){
                return true;
            }
        }
        return false;
    }

    public boolean authentificationAgence(Agence agence){
        for(AgenceSecret a : agencesPartenaires){
            if(a.getNomAgence().equals(agence.getNomAgence()) && a.verifSecret(agence.getSecretAgence())){
                return true;
            }
        }
        return false;
    }
}
