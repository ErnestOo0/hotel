package org.example.depot;

import java.util.ArrayList;

public class Chambre{

private int numero;
private int places;
private Tarif tarif;
private ArrayList<Reservation> listReservations;

public Chambre() {}

public Chambre(int numero, int places, Tarif tarif){
    this.numero = numero;
    this.places = places;
    this.tarif = tarif;
    this.listReservations = new ArrayList<>();
}

public int getNumero(){
    return this.numero;
}
public void setNumero(int numero){
    this.numero = numero;
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

public void newResa(Reservation datesResa){
    if(disponible(datesResa)){
        listReservations.add(datesResa);
    }

    }
}
