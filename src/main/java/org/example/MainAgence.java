package org.example;

import jakarta.xml.ws.Endpoint;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainAgence {
    public static void main(String[] args) {
        String adresse = args.length > 0 ? args[0] : "http://localhost:8080/agence";
        Endpoint.publish(adresse, new RechercheHotelImpl());
        System.out.println("Service publie  : " + adresse);
        System.out.println("Contrat WSDL    : " + adresse + "?wsdl");
        System.out.println("Schema XSD      : " + adresse + "?xsd=1");
        System.out.println("Ctrl+C pour arreter.");
    }
}