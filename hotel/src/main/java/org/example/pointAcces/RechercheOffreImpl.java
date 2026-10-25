package org.example.pointAcces;

import jakarta.jws.WebService;
import org.example.depot.Chambre;
import org.example.depot.Hotel;
import org.example.logiqueMetier.Logique;

import java.util.ArrayList;

@WebService(endpointInterface = "org.example.pointAcces.RechercheOffre",
        serviceName = "RechercheOffreService",
        portName = "RechercheOffreSoapPort",
        targetNamespace = "http://pointAcces.exemple.org/")
public class RechercheOffreImpl implements RechercheOffre {

    private Logique l;
    private Hotel h;

    public RechercheOffreImpl() {
        l = new Logique();
        h = new Hotel();
    }

    public RechercheOffreImpl(Hotel hotel) {
        l = new Logique();
        h = hotel;
    }

    @Override
    public ArrayList<Chambre> recherche(ArgumentsRecherche argsRecherche) throws AgenceInconueException{
        return l.offresDispo(argsRecherche, h);//utiliser fonction logique metier
    }
}
