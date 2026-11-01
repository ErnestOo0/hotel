package org.example.depot;

import org.example.pointAcces.arguments.Agence;

import java.util.ArrayList;
import java.util.Objects;

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

    public String getNom() {
        return nom;
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

    private boolean nomLibre(Chambre chambre){
        for(Chambre c : chambres){
            if(Objects.equals(c.getNom(), chambre.getNom())){
                return false;
            }
        }
        return true;
    }

    public void addChambre(Chambre chambre){
        if(nomLibre(chambre)){
            chambres.add(chambre);
        }
    }

    public Chambre getChambreByNom(String nom){
        for(Chambre c : chambres){
            if(Objects.equals(c.getNom(), nom)){
                return c;
            }
        }
        System.out.println("Aucunne chambre n'a le numero "+nom);
        return null;
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
