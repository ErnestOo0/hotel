package org.example.depot;

import java.util.ArrayList;

public class Chambre{

    private String nom;
    private int places;
    private Tarif tarif;
    private ArrayList<Reservation> listReservations;

    public Chambre() {
        listReservations = new ArrayList<>();
    }

    public Chambre(String nom, int places, Tarif tarif){
        this.nom = nom;
        this.places = places;
        this.tarif = tarif;
        this.listReservations = new ArrayList<>();
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    public int getPlaces(){
        return this.places;
    }
    public void setPlaces(int places){
        this.places = places;
    }

    public Tarif getTarif(){
        return this.tarif;
    }
    public void setTarif(Tarif tarif){
        this.tarif = tarif;
    }

    public ArrayList<Reservation> getListReservations(){
        return this.listReservations;
    }
    public void setListReservations(ArrayList<Reservation> listReservations){
        this.listReservations = listReservations;
    }

    //pas sur que ce soit ici
    public boolean disponible(Reservation dates){
        for(Reservation reservation : listReservations){
            if(reservation.conflict(dates)){
                return false;
            }
        }
        return true;
    }

    public boolean newResa(Reservation datesResa){
        if(disponible(datesResa)){
            listReservations.add(datesResa);
            return true;
        }
        return false;
    }
}
