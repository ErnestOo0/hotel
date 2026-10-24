package org.example.logiqueMetier;

import org.example.depot.Chambre;
import org.example.depot.Hotel;
import org.example.depot.Reservation;
import org.example.pointAcces.ArgumentsRecherche;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Logique {

    public ArrayList<Chambre> offresDispo(ArgumentsRecherche argsRecherche, Hotel h){
        ArrayList<Chambre> res = new ArrayList<>();
        if(!bonneVille(argsRecherche, h)){
            return res;
        }
        if(!assezEtoiles(argsRecherche, h)){
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

    private boolean datesDispo(Chambre chambreHotel, ArgumentsRecherche args){
        LocalDate arrivee = args.getDateArrivee();
        LocalDate depart = args.getDateDepart();
        Reservation r = new Reservation(arrivee, depart);
        return chambreHotel.disponible(r);
    }

    private boolean tarifCorrect(Chambre chambreHotel, ArgumentsRecherche args){
        LocalDate arrivee = args.getDateArrivee();
        LocalDate depart = args.getDateDepart();
        long prixMax = args.getPrixMax();
        //on suppose que le départ est avant l'arrivée
        long duree = ChronoUnit.DAYS.between(arrivee,depart);
        return duree*chambreHotel.getTarif().getPrix() <= prixMax;

    }
}
