package agence.service;

import agence.client.hotel.*;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String nomAgence = "Agence_Ernest-xXx-Maxime_Agence";
        Agence a = new Agence();
        a.setNomAgence(nomAgence);
        a.setSecretAgence("12345");
        HotelService service = new HotelService();
        HotelPort port = service.getHotelSoapPort();

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

            argrr.setAgence(a);
            argrr.setDateArrivee(da);
            argrr.setDateDepart(dd);
            argrr.setVille(v);
            argrr.setNbEtoiles(ne);
            argrr.setNbPersonnes(np);
            argrr.setPrixMax(pmax);

            List<Chambre> lc = port.rechercheOffre(argrr);
            System.out.println(lc.size()+" chambres répondent à la demande");
            for (Chambre c : lc) {
                System.out.println("chambre n°"+c.getNom()+" : "+c.getTarif().getPrix()+c.getTarif().getDevise());
            }
        }catch(AgenceInconueException_Exception e){
            System.out.println("Erreur "+ e.getMessage());
        }
    }
}