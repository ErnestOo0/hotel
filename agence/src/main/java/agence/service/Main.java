package agence.service;

import agence.client.hotel.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String nomAgence = "Agence_Ernest-xXx-Maxime_Agence";
        RechercheOffreService service = new RechercheOffreService();
        RechercheOffre port = service.getRechercheOffreSoapPort();

        try {
            ArgumentsRecherche argrr = new ArgumentsRecherche();
            argrr.setAgence(nomAgence);
            port.recherche(argrr);
        }catch(AgenceInconueException_Exception e){
            System.out.println("Erreur "+ e.getMessage());
        }
    }
}