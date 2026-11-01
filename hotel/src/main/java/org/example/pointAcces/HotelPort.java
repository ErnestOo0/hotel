package org.example.pointAcces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.depot.Chambre;
import org.example.depot.Confirmation;
import org.example.depot.Offre;
import org.example.pointAcces.arguments.ArgumentsReservation;
import org.example.pointAcces.exception.AgenceInconueException;
import org.example.pointAcces.arguments.ArgumentsRecherche;
import org.example.pointAcces.exception.ErreurParametreReservationExeption;

import java.util.ArrayList;

@WebService(
        name = "HotelPort", targetNamespace = "http://hotel.pointAcces.example.org/"
)
public interface HotelPort {
    @WebMethod
    @WebResult(name = "listHotels")
    ArrayList<Offre> rechercheOffre(@WebParam(name="argsRecherche") ArgumentsRecherche argsRecherche) throws AgenceInconueException;

    @WebMethod
    @WebResult(name = "idDemande")
    Confirmation reserverOffre(@WebParam(name="argsReserve") ArgumentsReservation argsReserve) throws ErreurParametreReservationExeption;

}
