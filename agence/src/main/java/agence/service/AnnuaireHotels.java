package agence.service;

import agence.client.hotel.*;
import jakarta.xml.ws.WebServiceException;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnuaireHotels {

    private final List<String> WSDLS;

    public AnnuaireHotels() {
        WSDLS = new ArrayList<>();
        WSDLS.add("http://localhost:8080/hotel?wsdl");
        WSDLS.add("http://localhost:8081/hotel?wsdl");
    }

    public AnnuaireHotels(List<String> WSDLS) {
        this.WSDLS = WSDLS;
    }

    private final Map<String, HotelPort> proxys = new ConcurrentHashMap<>();

    public List<String> hotels() {
        return WSDLS;
    }

    /** Retourne le proxy de cet hôtel, en le créant à la première demande. */
    public HotelPort port(String urlWsdl) throws MalformedURLException {
        HotelPort existant = proxys.get(urlWsdl);
        if (existant != null) {
            return existant;
        }
        HotelPort port = connecter(urlWsdl);
        proxys.put(urlWsdl, port);
        return port;
    }

    private static HotelPort connecter(String urlWsdl) throws MalformedURLException {
        return new HotelService(URI.create(urlWsdl).toURL()).getHotelSoapPort();
    }

    public List<Offre> rechercheOffre(ArgumentsRecherche argrr){
        List<Offre> offres = new ArrayList<>();
        for (String wsdl : hotels()) {
            try {
                HotelPort port = port(wsdl);
                offres.addAll(port.rechercheOffre(argrr));

            } catch (WebServiceException | MalformedURLException e) {
                //System.out.println("Hôtel ignoré : " + wsdl + " (" + e.getMessage() + ")");
                System.out.println(wsdl +" injoignable");
            } catch (AgenceInconueException_Exception e) {
                System.out.println("Erreur " + e.getMessage());
            }
        }
        return offres;
    }

    public Confirmation reserverOffre(ArgumentsReservation argrr){
        Confirmation conf = new Confirmation();
        try {
            HotelPort port = port(argrr.getAdresseHotel());
            conf = port.reserverOffre(argrr);

        } catch (WebServiceException | MalformedURLException e) {
            //System.out.println("Hôtel ignoré : " + argrr.getAdresseHotel() + " (" + e.getMessage() + ")");
            System.out.println(argrr.getAdresseHotel() +" ne répond plus");
        } catch (ErreurParametreReservationExeption_Exception e) {
            System.out.println("Erreur reservation: " + e + " : Veuillez essayer une autre offre.");

        }

        return conf;
    }
}