package org.example.pointAcces.recherche;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.depot.Chambre;
import org.example.pointAcces.arguments.ArgumentsRecherche;
import org.example.pointAcces.exception.AgenceInconueException;

import java.util.ArrayList;

@Deprecated
@WebService(
        name = "rechercheOffre", targetNamespace = "http://recherche.pointAcces.example.org/"
)
public interface RechercheOffre {
    @WebMethod
    @WebResult(name = "listHotels")
    ArrayList<Chambre> recherche(@WebParam(name="argsRecherche") ArgumentsRecherche argsRecherche) throws AgenceInconueException;

}
