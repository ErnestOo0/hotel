package org.example.depot;

import java.util.ArrayList;

public class Hotel {
    private String nom;
    private String ville;
    private int etoiles;
    private ArrayList<Chambre> chambres;
    private ArrayList<String> agencesPartenaires;

    public Hotel() {}

    public String getVille(){
        return ville;
    }

    public int getEtoiles(){
        return etoiles;
    }

    public ArrayList<Chambre> getChambres(){
        return chambres;
    }
}
