package org.example.pointAcces;

import jakarta.jws.WebService;
import org.example.depot.Chambre;
import org.example.depot.Confirmation;
import org.example.depot.Hotel;
import org.example.depot.Offre;
import org.example.logiqueMetier.Logique;
import org.example.pointAcces.arguments.ArgumentsReservation;
import org.example.pointAcces.exception.AgenceInconueException;
import org.example.pointAcces.arguments.ArgumentsRecherche;
import org.example.pointAcces.exception.ErreurParametreReservationExeption;

import java.util.ArrayList;

@WebService(endpointInterface = "org.example.pointAcces.HotelPort",
        serviceName = "HotelService",
        portName = "HotelSoapPort",
        targetNamespace = "http://pointAcces.exemple.org/")
public class HotelPortImpl implements HotelPort {
    private final Logique l;
    private final Hotel h;

    public HotelPortImpl() {
        l = new Logique();
        h = new Hotel();
    }

    public HotelPortImpl(Hotel hotel) {
        l = new Logique();
        h = hotel;
    }

    @Override
    public ArrayList<Offre> rechercheOffre(ArgumentsRecherche argsRecherche) throws AgenceInconueException {
        return l.offresDispo(argsRecherche, h);//utiliser fonction logique metier
    }

    @Override
    public Confirmation reserverOffre(ArgumentsReservation argsReserve)  throws ErreurParametreReservationExeption {
        return l.reserver(argsReserve, h);
    }
}
