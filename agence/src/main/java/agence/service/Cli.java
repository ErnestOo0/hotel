package agence.service;

import agence.client.hotel.Agence;
import agence.client.hotel.ArgumentsRecherche;
import agence.client.hotel.ArgumentsReservation;
import agence.client.hotel.Confirmation;
import agence.client.hotel.DateDecomposee;
import agence.client.hotel.Expiration;
import agence.client.hotel.InfoBancaires;
import agence.client.hotel.Offre;
import agence.client.hotel.Voyageur;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Cli {

    private final Scanner scanner = new Scanner(System.in);
    private final AnnuaireHotels annuaire;
    private final Agence agence;
    private List<Offre> offres = new ArrayList<>();

    public Cli(AnnuaireHotels annuaire, Agence agence) {
        this.annuaire = annuaire;
        this.agence = agence;
    }

    public void lancer() {
        int choix = -1;
        while (choix != 0) {
            System.out.println();
            System.out.println("1. Rechercher un séjour");
            System.out.println("2. Réserver une offre");
            System.out.println("0. Quitter");
            choix = lireEntier("Votre choix : ");

            if (choix == 1) {
                rechercher();
            } else if (choix == 2) {
                reserver();
            } else if (choix != 0) {
                System.out.println("Choix inconnu.");
            }
        }
        System.out.println("Au revoir.");
    }

    private void rechercher() {
        ArgumentsRecherche args = new ArgumentsRecherche();
        args.setAgence(agence);
        args.setVille(lireTexte("Ville : "));

        System.out.println("Date d'arrivée :");
        DateDecomposee arrivee = lireDate();
        DateDecomposee depart;
        while (true) {
            System.out.println("Date de départ :");
            depart = lireDate();
            if (enLocalDate(depart).isAfter(enLocalDate(arrivee))) {
                break;
            }
            System.out.println("La date de départ doit être après la date d'arrivée.");
        }
        args.setDateArrivee(arrivee);
        args.setDateDepart(depart);

        args.setNbPersonnes(lireEntier("Nombre de personnes : ", 1, -1));
        args.setNbEtoiles(lireEntier("Nombre d'étoiles minimum (0 à 5) : ", 0, 5));
        args.setPrixMax((double) lireEntier("Prix maximum (€) : ", 1, -1));

        try {
            offres = annuaire.rechercheOffre(args);
        } catch (WebServiceException e) {
            afficherErreur(e);
            return;
        }
        afficherOffres();
    }

    private void reserver() {
        if (offres.isEmpty()) {
            System.out.println("Faites d'abord une recherche qui donne des offres.");
            return;
        }
        afficherOffres();

        int indice = lireEntier("Indice de l'offre à réserver (-1 pour annuler) : ", -1, offres.size() - 1);
        if (indice == -1) {
            return;
        }
        Offre offre = offres.get(indice);

        Voyageur voyageur = new Voyageur();
        voyageur.setPrenomVoyageur(lireTexte("Prénom du voyageur : "));
        voyageur.setNomVoyageur(lireTexte("Nom du voyageur : "));

        InfoBancaires infoBancaires = new InfoBancaires();
        infoBancaires.setNumero(lireChiffres("Numéro de carte (16 chiffres) : ", 16, 16));
        Expiration expiration = new Expiration();
        while (true) {
            int mois = lireEntier("Mois d'expiration (1 à 12) : ", 1, 12);
            int annee = lireEntier("Année d'expiration : ", 2000, 2100);
            if (YearMonth.of(annee, mois).isBefore(YearMonth.now())) {
                System.out.println("Cette carte est expirée.");
                continue;
            }
            expiration.setMois(mois);
            expiration.setAnnee(annee);
            break;
        }
        infoBancaires.setExpiration(expiration);
        infoBancaires.setCvc(lireChiffres("CVC (3 chiffres) : ", 3, 3));

        ArgumentsReservation demande = new ArgumentsReservation();
        demande.setAgence(agence);
        demande.setIdDemande(UUID.randomUUID().toString());
        demande.setInfoBancaires(infoBancaires);
        demande.setOffre(offre);
        demande.setVoyageur(voyageur);
        demande.setAdresseHotel(offre.getAdresseHotel());

        try {
            Confirmation confirmation = annuaire.reserverOffre(demande);
            if (confirmation.isDejaReserve()) {
                System.out.println("Cette demande avait déjà été traitée.");
            } else {
                System.out.println("Réservation confirmée.");
            }
            System.out.println("Référence de réservation : " + confirmation.getRefReservation());
        } catch (WebServiceException e) {
            afficherErreur(e);
        }
    }

    private void afficherOffres() {
        if (offres.isEmpty()) {
            System.out.println("Aucune offre disponible.");
            return;
        }
        for (int i = 0; i < offres.size(); i++) {
            Offre o = offres.get(i);
            System.out.println("----------------------------------------");
            System.out.println("[" + i + "] Hôtel : " + o.getNomHotel());
            System.out.println("    Arrivée : " + o.getDateArrivee().getJour() + "/"
                    + o.getDateArrivee().getMois() + "/" + o.getDateArrivee().getAnnee());
            System.out.println("    Départ  : " + o.getDateRetour().getJour() + "/"
                    + o.getDateRetour().getMois() + "/" + o.getDateRetour().getAnnee());
            System.out.println("    Prix    : " + o.getPrix() + " €");
        }
        System.out.println("----------------------------------------");
    }

    private void afficherErreur(WebServiceException e) {
        if (e instanceof SOAPFaultException faute) {
            System.out.println("Refus de l'hôtel : " + faute.getFault().getFaultString());
        } else {
            System.out.println("Un hôtel ne répond pas ou est injoignable.");
        }
    }

    private String lireTexte(String invite) {
        String texte = "";
        while (texte.isEmpty()) {
            System.out.print(invite);
            texte = scanner.nextLine().trim();
        }
        return texte;
    }

    private int lireEntier(String invite) {
        while (true) {
            System.out.print(invite);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier.");
            }
        }
    }

    private int lireEntier(String invite, int min, int max) {
        while (true) {
            int valeur = lireEntier(invite);
            if (max == - 1) { return valeur; }
            if (valeur >= min && valeur <= max) {
                return valeur;
            }
            System.out.println("Veuillez entrer un nombre entre " + min + " et " + max + ".");
        }
    }

    private String lireChiffres(String invite, int min, int max) {
        while (true) {
            System.out.print(invite);
            String texte = scanner.nextLine().replace(" ", "");
            if (texte.matches("\\d{" + min + "," + max + "}")) {
                return texte;
            }
            System.out.println("Saisie invalide : uniquement des chiffres, entre " + min + " et " + max + ".");
        }
    }

    private DateDecomposee lireDate() {
        while (true) {
            int jour = lireEntier("  Jour (1 à 31) : ", 1, 31);
            int mois = lireEntier("  Mois (1 à 12) : ", 1, 12);
            int annee = lireEntier("  Année : ", 2000, 2100);
            try {
                LocalDate.of(annee, mois, jour);
            } catch (DateTimeException e) {
                System.out.println("Cette date n'existe pas, recommencez.");
                continue;
            }
            DateDecomposee date = new DateDecomposee();
            date.setJour(jour);
            date.setMois(mois);
            date.setAnnee(annee);
            return date;
        }
    }

    private LocalDate enLocalDate(DateDecomposee d) {
        return LocalDate.of(d.getAnnee(), d.getMois(), d.getJour());
    }
}