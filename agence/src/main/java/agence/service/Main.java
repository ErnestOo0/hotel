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

            List<Offre> lo = port.rechercheOffre(argrr);
            System.out.println(lo.size() + " offre(s) répondent à la demande.");

            if (lo.isEmpty()) {
                System.out.println("Aucune offre disponible.");
                return;
            }

// Affichage des offres avec leur indice
            int index = 0;
            for (Offre o : lo) {
                System.out.println("----------------------------------------");
                System.out.println(" Indice  : [" + index + "]");
                System.out.println(" Hôtel   : " + o.getNomHotel());
                System.out.println(" Arrivée : " + o.getDateArrivee().getJour() + "/"
                        + o.getDateArrivee().getMois() + "/"
                        + o.getDateArrivee().getAnnee());
                System.out.println(" Retour  : " + o.getDateRetour().getJour() + "/"
                        + o.getDateRetour().getMois() + "/"
                        + o.getDateRetour().getAnnee());
                System.out.println(" Prix    : " + o.getPrix() + " €");
                index++;
            }
            System.out.println("----------------------------------------");

            Voyageur voyageur = new Voyageur();
            String prenom = "Ernest";
            String nom = "Jouault";
// voyageur.setPrenom(prenom); // selon vos setters disponibles
// voyageur.setNom(nom);

            String cvc = "620";
            String numero = "4202304242042923";

            Expiration expiration = new Expiration();
            expiration.setAnnee(2030);
            expiration.setMois(11);

            InfoBancaires infoBancairesClient = new InfoBancaires();
            infoBancairesClient.setCvc(cvc);
            infoBancairesClient.setExpiration(expiration);
            infoBancairesClient.setNumero(numero);

            java.util.Scanner scanner = new java.util.Scanner(System.in);

            while (true) {
                System.out.print("Entrez le numéro (l'indice) de l'offre que vous souhaitez réserver : ");
                int choix;

                try {
                    choix = Integer.parseInt(scanner.nextLine());
                    if (choix < 0 || choix >= lo.size()) {
                        System.out.println("Erreur : Indice invalide. Veuillez choisir entre 0 et " + (lo.size() - 1));
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre entier valide.");
                    continue;
                }

                Offre offreChoisie = lo.get(choix);

                ArgumentsReservation argumentsReservation = new ArgumentsReservation();
                argumentsReservation.setAgence(a);
                argumentsReservation.setIdDemande(String.valueOf(choix));
                argumentsReservation.setInfoBancaires(infoBancairesClient);
                argumentsReservation.setOffre(offreChoisie);
                argumentsReservation.setVoyageur(voyageur);


                ArgumentsReservation argumentsReservation2 = new ArgumentsReservation();
                argumentsReservation.setAgence(a);
                argumentsReservation.setIdDemande("2");
                argumentsReservation.setInfoBancaires(infoBancairesClient);
                argumentsReservation.setOffre(offreChoisie);
                argumentsReservation.setVoyageur(voyageur);

                Confirmation reponse2 = port.reserverOffre(argumentsReservation);


                try {
                    Confirmation reponse = port.reserverOffre(argumentsReservation2);

                    if (reponse.isDejaReserve()) {
                        System.out.println("Vous avez déjà reservé cette offre.");
                        System.out.println("Id-demande : " + reponse.getIdDemande());
                        System.out.println("Id-reservation : " + reponse.getRefReservation());
                    } else {
                        System.out.println("Réservation validée avec succès.");
                        System.out.println("Id-demande : " + reponse.getIdDemande());
                        System.out.println("Id-reservation : " + reponse.getRefReservation());
                    }
                    break;

                } catch (AgenceInconueException_Exception e) {
                    System.out.println("Erreur reservation: " + e + "Veuillez essayer une autre offre.");

                }

                scanner.close();
            }

        } catch (AgenceInconueException_Exception e) {
            System.out.println("Erreur " + e.getMessage());
        }
    }


}