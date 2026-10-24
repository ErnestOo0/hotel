package org.example;

import java.util.ArrayList;

public class ReponseRecherche {
    ArrayList<String> listHotels;

    public ReponseRecherche() {
        listHotels = new ArrayList<>();
    }

    public ArrayList<String> getListHotels() {
        return listHotels;
    }
    public void setListHotels(ArrayList<String> listHotels) {
        this.listHotels = listHotels;
    }
}
