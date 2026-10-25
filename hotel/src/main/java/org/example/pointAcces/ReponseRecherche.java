package org.example.pointAcces;

import java.util.ArrayList;

public class ReponseRecherche {
    ArrayList<String> listOffres;

    public ReponseRecherche() {
        listOffres = new ArrayList<>();
    }

    public ArrayList<String> getListOffres() {
        return listOffres;
    }
    public void setListOffres(ArrayList<String> listOffres) {
        this.listOffres = listOffres;
    }
}
