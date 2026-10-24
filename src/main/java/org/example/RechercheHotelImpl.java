package org.example;

import jakarta.jws.WebService;

@WebService(endpointInterface = "org.example.RechercheHotel",
        serviceName = "RechercheHotelService",
        portName = "RechercheHotelSoapPort",
        targetNamespace = "http://exemple.org/")
public class RechercheHotelImpl implements RechercheHotel {

    @Override
    public ReponseRecherche recherche(ArgumentsRecherche argsRecherche){
        return new ReponseRecherche();
    }
}
