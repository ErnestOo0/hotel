package org.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;


@WebService(
        name = "rechercheHotel", targetNamespace = "http://example.org/"
)
public interface RechercheHotel {
    @WebMethod
    @WebResult(name = "listHotels")
    ReponseRecherche recherche(@WebParam(name="argsRecherche") ArgumentsRecherche argsRecherche);

}
