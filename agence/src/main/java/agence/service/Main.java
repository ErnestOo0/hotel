package agence.service;

import agence.client.hotel.*;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String nomAgence = "Agence_Ernest-xXx-Maxime_Agence";
        RechercheOffreService service = new RechercheOffreService();
        RechercheOffre port = service.getRechercheOffreSoapPort();

        DateDecomposee da = new DateDecomposee();
        da.setJour(2);
        da.setMois(11);
        da.setAnnee(2026);

        DateDecomposee dd = new DateDecomposee();
        dd.setJour(8);
        dd.setMois(11);
        dd.setAnnee(2026);

        String v = "Montpellier";

        int ne = 2;
        int np = 1;
        long pmax = 200;



        try {
            ArgumentsRecherche argrr = new ArgumentsRecherche();

            argrr.setAgence(nomAgence);
            argrr.setDateArrivee(da);
            argrr.setDateDepart(dd);
            argrr.setVille(v);
            argrr.setNbEtoiles(ne);
            argrr.setNbPersonnes(np);
            argrr.setPrixMax(pmax);

            List<Chambre> lc = port.recherche(argrr);
            System.out.println(lc.size()+" chambres répondent à la demande");
            for (Chambre c : lc) {
                System.out.println("chambre n°"+c.getNumero()+" : "+c.getTarif().getPrix()+c.getTarif().getDevise());
            }
        }catch(AgenceInconueException_Exception e){
            System.out.println("Erreur "+ e.getMessage());
        }
    }
}