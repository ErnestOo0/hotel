package agence.service;

import agence.client.hotel.Agence;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Agence agence = new Agence();
        agence.setNomAgence("Agence_Ernest-xXx-Maxime_Agence");
        String secret = "12345";
        agence.setSecretAgence(secret);

        AnnuaireHotels annuaire =  new AnnuaireHotels(List.of(args[0].split(" ")));

        new Cli(annuaire, agence).lancer();
    }
}