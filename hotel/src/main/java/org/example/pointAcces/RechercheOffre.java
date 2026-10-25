package org.example.pointAcces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.depot.Chambre;

import java.util.ArrayList;


@WebService(
        name = "rechercheOffre", targetNamespace = "http://example.org/"
)
public interface RechercheOffre {
    @WebMethod
    @WebResult(name = "listHotels")
    ArrayList<Chambre> recherche(@WebParam(name="argsRecherche") ArgumentsRecherche argsRecherche) throws AgenceInconueException;

}
