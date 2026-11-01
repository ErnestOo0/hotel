package org.example;

import jakarta.xml.ws.Endpoint;
import org.example.depot.AgenceSecret;
import org.example.depot.Chambre;
import org.example.depot.Hotel;
import org.example.depot.Tarif;
import org.example.pointAcces.HotelPortImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainHotel {
    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        String adresse = "http://localhost:"+port+"/hotel";

        AgenceSecret a1 = new AgenceSecret();
        String noma1 = "Agence_Ernest-xXx-Maxime_Agence";
        String secret = "12345";
        a1.setNomAgence(noma1);
        a1.setSecretAgence(secret);

        String nh = args[1];
        String vh = args[2];
        int neh = 3;
        Hotel h = new Hotel(nh,vh,neh, adresse);
        h.addAgencesPartenaire(a1);

        Chambre c = new Chambre();
        String nom = "1";
        int pl = 2;
        String nomT = "regulier";
        long p = 10;
        String d = "€";
        Tarif t = new Tarif(nomT,p,d);

        c.setNom(nom);
        c.setPlaces(pl);
        c.setTarif(t);
        h.addChambre(c);

        Endpoint.publish(adresse, new HotelPortImpl(h));
        System.out.println("Service publie  : " + adresse);
        System.out.println("Contrat WSDL    : " + adresse + "?wsdl");
        System.out.println("Schema XSD      : " + adresse + "?xsd=1");
        System.out.println("Ctrl+C pour arreter.");
    }
}