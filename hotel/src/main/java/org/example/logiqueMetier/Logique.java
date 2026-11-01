package org.example.logiqueMetier;

import org.example.depot.*;
import org.example.pointAcces.arguments.ArgumentsReservation;
import org.example.pointAcces.arguments.Expiration;
import org.example.pointAcces.arguments.InfoBancaires;
import org.example.pointAcces.exception.AgenceInconueException;
import org.example.pointAcces.arguments.ArgumentsRecherche;
import org.example.pointAcces.exception.ErreurParametreReservationExeption;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Logique {

    public ArrayList<Offre> offresDispo(ArgumentsRecherche argsRecherche, Hotel h) throws AgenceInconueException {
        if(!h.estAgencePartenaire(argsRecherche.getAgence())){
            throw new AgenceInconueException("l'agence \""+argsRecherche.getAgence()+"\" n'est pas partenaire");
        }

        ArrayList<Offre> res = new ArrayList<>();
        if(!bonneVille(argsRecherche, h)){
            System.out.println("mauvaise ville");
            return res;
        }
        if(!assezEtoiles(argsRecherche, h)){
            System.out.println("pas assez d'etoiles");
            return res;
        }
        if(!datesValides(argsRecherche)){
            System.out.println("dates invalides");
            return res;
        }
        for(Chambre chambreHotel : h.getChambres()){
            if(datesDispo(chambreHotel, argsRecherche)
                    && tarifCorrect(chambreHotel,argsRecherche)){
                Offre newO = new Offre(h.getNom(), chambreHotel.getNom(), argsRecherche.getDateArrivee(), argsRecherche.getDateDepart(), prixSejour(chambreHotel, argsRecherche));
                res.add(newO);
            }
        }
        return res;
    }

    private boolean bonneVille(ArgumentsRecherche args, Hotel h){
        String villeRecherche = args.getVille();
        String villeHotel = h.getVille();
        return villeRecherche.equals(villeHotel);
    }

    private boolean assezEtoiles(ArgumentsRecherche args, Hotel h){
        int nbEtoilesRecherche = args.getNbEtoiles();
        int etoilesHotel = h.getEtoiles();
        return etoilesHotel >= nbEtoilesRecherche;
    }

    private boolean datesValides(ArgumentsRecherche args){
        DateDecomposee arrivee = args.getDateArrivee();
        DateDecomposee depart = args.getDateDepart();
        Reservation r = new Reservation(arrivee, depart);
        return r.datesValides();
    }

    private boolean datesDispo(Chambre chambreHotel, ArgumentsRecherche args){
        DateDecomposee arrivee = args.getDateArrivee();
        DateDecomposee depart = args.getDateDepart();
        Reservation r = new Reservation(arrivee, depart);
        return chambreHotel.disponible(r);
    }

    private boolean tarifCorrect(Chambre chambreHotel, ArgumentsRecherche args){

        long prixMax = args.getPrixMax();
        //on suppose que le départ est avant l'arrivée
        return prixSejour(chambreHotel, args) <= prixMax;
    }

    private long prixSejour(Chambre chambreHotel, ArgumentsRecherche args){
        DateDecomposee arrivee = args.getDateArrivee();
        DateDecomposee depart = args.getDateDepart();
        long duree = ChronoUnit.DAYS.between(arrivee.toLocalDate(),depart.toLocalDate());
        return duree*chambreHotel.getTarif().getPrix();
    }

    public Confirmation reserver(ArgumentsReservation argsReserve, Hotel h)  throws ErreurParametreReservationExeption {
        //si on a la meme demande, onn indique qu'elle à deja été réservé
        Confirmation res = new Confirmation(argsReserve.getIdDemande());
        String refResa = h.getReservationByIdDemande(argsReserve.getIdDemande());
        System.out.println("nouvelle reservation");
        if(refResa != null){
            System.out.println("reservation deja effectue");
            res.setDejaReserve(true);
            res.setRefReservation(refResa);
            return res;
        }

        //verifier secret
        if(!h.authentificationAgence(argsReserve.getAgence())){
            System.out.println("secret invalide");
            //modifier l'erreur
            throw new ErreurParametreReservationExeption("Impossible d'authentifier l'agence");
        }
        System.out.println("secret valide");
        if(!infoBancairesValides(argsReserve.getInfoBancaires())){
            System.out.println("info bancaires invalide");
            //modifier l'erreur
            throw new ErreurParametreReservationExeption("Info bancaire res invalides");
        }
        System.out.println("info bancaires valide");
        //verifier existant
        if(!chambreValide(argsReserve, h)){
            throw new ErreurParametreReservationExeption("Chambre inconue");
        }

        //effectuer reservation, la verification de la validité se fait ici
        if(!reserverChambre(argsReserve, h)){
            System.out.println("Chambre deja reservee");
            throw new ErreurParametreReservationExeption("Chambre deja reservée");
        }
        System.out.println("Chambre peut etre reservee");
        //renvoyer reference
        refResa = makeRefResa(argsReserve);
        h.addReservation(argsReserve.getIdDemande(), refResa);
        res.setRefReservation(refResa);
        System.out.println("Reservation terminee");
        return res;
    }

    private boolean infoBancairesValides(InfoBancaires i){
        if(i.getNumero()== null || i.getCvc()==null || i.getExpiration()==null){
            return false;
        }
        if(!i.getNumero().matches("[0-9]{16}")){
            return false;
        }
        if(!i.getCvc().matches("[0-9]{3,4}")){
            return false;
        }
        Expiration e = i.getExpiration();
        DateDecomposee d = new DateDecomposee(1,e.getMois(),e.getAnnee());
        if(d.passee()){
            return false;
        }
        return true;
    }

    private boolean chambreValide(ArgumentsReservation argsReserve, Hotel h){
        Offre offreClient = argsReserve.getOffre();
        if(!h.getNom().equals(offreClient.getNomHotel())){
            System.out.println("mauvais hotel");
            return false;
        }
        Chambre c = h.getChambreByNom(offreClient.getNomChambre());
        if(c==null){
            System.out.println("chambre non valide");
            return false;
        }
        return true;
    }

    private boolean reserverChambre(ArgumentsReservation argsReserve, Hotel h){
        Offre offreClient = argsReserve.getOffre();
        String nomA = argsReserve.getAgence().getNomAgence();
        Voyageur v = argsReserve.getVoyageur();
        Chambre c =h.getChambreByNom(offreClient.getNomChambre());
        Reservation r = new Reservation(nomA,v, offreClient.getDateArrivee(), offreClient.getDateRetour());
        return c.newResa(r);
    }

    public String makeRefResa(ArgumentsReservation argsReserve){
        String res = "";
        res += argsReserve.getAgence().getNomAgence();
        res += argsReserve.getVoyageur().getNomVoyageur();
        res += argsReserve.getOffre().toString();
        return res;
    }
}


