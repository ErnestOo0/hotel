package org.example;

import jakarta.xml.ws.Endpoint;
import org.example.depot.Chambre;
import org.example.depot.Hotel;
import org.example.depot.Tarif;
import org.example.pointAcces.RechercheOffreImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainHotel {
    public static void main(String[] args) {
        String a1 = "Agence_Ernest-xXx-Maxime_Agence";
        String nh = "hotel du parc";
        String vh = "Montpellier";
        int neh = 3;
        Hotel h = new Hotel(nh,vh,neh);
        h.addAgencesPartenaire(a1);

        Chambre c = new Chambre();
        int num = 1;
        int pl = 2;
        String nomT = "regulier";
        long p = 10;
        String d = "€";
        Tarif t = new Tarif(nomT,p,d);

        c.setNumero(num);
        c.setPlaces(pl);
        c.setTarif(t);
        h.addChambre(c);

        String adresse = args.length > 0 ? args[0] : "http://localhost:8080/agence";
        Endpoint.publish(adresse, new RechercheOffreImpl(h));
        System.out.println("Service publie  : " + adresse);
        System.out.println("Contrat WSDL    : " + adresse + "?wsdl");
        System.out.println("Schema XSD      : " + adresse + "?xsd=1");
        System.out.println("Ctrl+C pour arreter.");
    }
}