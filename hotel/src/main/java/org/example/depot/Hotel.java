package org.example.depot;

import java.util.ArrayList;

public class Hotel {
    private String nom;
    private String ville;
    private int etoiles;
    private ArrayList<Chambre> chambres;
    private ArrayList<String> agencesPartenaires;

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

    public ArrayList<String> getAgencesPartenaires(){
        return agencesPartenaires;
    }

    public void addAgencesPartenaire(String agence){
        if(!agencesPartenaires.contains(agence)){
            agencesPartenaires.add(agence);
        }
    }

    public boolean estAgencePartenaire(String agence){
        return agencesPartenaires.contains(agence);
    }
}
