package org.example.pointAcces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.depot.Chambre;
import org.example.pointAcces.arguments.ArgumentsReservation;
import org.example.pointAcces.exception.AgenceInconueException;
import org.example.pointAcces.arguments.ArgumentsRecherche;

import java.util.ArrayList;

@WebService(
        name = "HotelPort", targetNamespace = "http://hotel.pointAcces.example.org/"
)
public interface HotelPort {
    @WebMethod
    @WebResult(name = "listHotels")
    ArrayList<Chambre> rechercheOffre(@WebParam(name="argsRecherche") ArgumentsRecherche argsRecherche) throws AgenceInconueException;

    @WebMethod
    @WebResult(name = "idDemande")
    String reserverOffre(@WebParam(name="argsReserve") ArgumentsReservation argsReserve) throws AgenceInconueException;

}
