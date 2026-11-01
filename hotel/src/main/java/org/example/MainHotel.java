package org.example;

import jakarta.xml.ws.Endpoint;
import org.example.depot.AgenceSecret;
import org.example.depot.Chambre;
import org.example.depot.Hotel;
import org.example.depot.Tarif;
import org.example.pointAcces.HotelPortImpl;

import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainHotel {
    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        String nh = args[1];
        String vh = args[2];
        String adresse = "http://localhost:"+port+"/hotel";

        AgenceSecret a1 = new AgenceSecret();
        a1.setNomAgence("Agence_Ernest-xXx-Maxime_Agence");
        a1.setSecretAgence("12345");
        a1.setCoefTarif(1.20);

        AgenceSecret a2 = new AgenceSecret();
        a2.setNomAgence("Agence_ErnestN");
        a2.setSecretAgence("24012005");
        a2.setCoefTarif(1.05);

        AgenceSecret a3 = new AgenceSecret();
        a3.setNomAgence("Agence_MaximeJ");
        a3.setSecretAgence("67676767");
        a3.setCoefTarif(1.50);

        Tarif t1 = new Tarif("regulier",10,"€");
        Tarif t2 = new Tarif("regulier",20,"€");

        Chambre c11 = new Chambre();
        c11.setNom("petite1");
        c11.setPlaces(2);
        c11.setTarif(t1);

        Chambre c12 = new Chambre();
        c12.setNom("petite2");
        c12.setPlaces(2);
        c12.setTarif(t1);

        Chambre c21 = new Chambre();
        c21.setNom("grande1");
        c21.setPlaces(4);
        c21.setTarif(t2);

        Chambre c22 = new Chambre();
        c22.setNom("grande2");
        c22.setPlaces(4);
        c22.setTarif(t2);


        Hotel h;
        if(nh.equals("royal")){
            h = new Hotel(nh, vh, 5, "1 rue de la ville");
            h.addChambre(c11);
            h.addChambre(c21);

            h.addAgencesPartenaire(a1);
            h.addAgencesPartenaire(a3);

        } else if (nh.equals("ibis")) {
            h = new Hotel(nh, vh, 2, "1 rue de la ville");
            h.addChambre(c11);
            h.addChambre(c12);
            h.addAgencesPartenaire(a1);
            h.addAgencesPartenaire(a2);
            h.addAgencesPartenaire(a3);
        }else{
            h = new Hotel(nh, vh, 4, "1 rue de la ville");
            h.addChambre(c11);
            h.addChambre(c12);
            h.addChambre(c21);
            h.addChambre(c22);
            h.addAgencesPartenaire(a1);
            h.addAgencesPartenaire(a2);
            h.addAgencesPartenaire(a3);
        }

        Endpoint.publish(adresse, new HotelPortImpl(h));
        System.out.println("Service publie  : " + adresse);
        System.out.println("Contrat WSDL    : " + adresse + "?wsdl");
        System.out.println("Schema XSD      : " + adresse + "?xsd=1");
        System.out.println("Ctrl+C pour arreter.");
    }
}