package org.example.logiqueMetier;

import org.example.depot.Chambre;
import org.example.depot.DateDecomposee;
import org.example.depot.Hotel;
import org.example.depot.Reservation;
import org.example.pointAcces.arguments.ArgumentsReservation;
import org.example.pointAcces.arguments.Expiration;
import org.example.pointAcces.arguments.InfoBancaires;
import org.example.pointAcces.exception.AgenceInconueException;
import org.example.pointAcces.arguments.ArgumentsRecherche;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Logique {

    public ArrayList<Chambre> offresDispo(ArgumentsRecherche argsRecherche, Hotel h) throws AgenceInconueException {
        if(!h.estAgencePartenaire(argsRecherche.getAgence())){
            throw new AgenceInconueException("l'agence \""+argsRecherche.getAgence()+"\" n'est pas partenaire");
        }

        ArrayList<Chambre> res = new ArrayList<>();
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
                res.add(chambreHotel);
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
        DateDecomposee arrivee = args.getDateArrivee();
        DateDecomposee depart = args.getDateDepart();
        long prixMax = args.getPrixMax();
        //on suppose que le départ est avant l'arrivée
        long duree = ChronoUnit.DAYS.between(arrivee.toLocalDate(),depart.toLocalDate());
        return duree*chambreHotel.getTarif().getPrix() <= prixMax;
    }

    public String reserver(ArgumentsReservation argsReserve, Hotel h)  throws AgenceInconueException {
        //verifier secret
        if(!h.authentificationAgence(argsReserve.getAgence())){
            //modifier l'erreur
            throw new AgenceInconueException("Impossible d'authentifier l'agence");
        }
        if(!infoBancairesValides(argsReserve.getInfoBancaires())){
            //modifier l'erreur
            throw new AgenceInconueException("Info bancaires invalides");
        }

        return "";
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
}
