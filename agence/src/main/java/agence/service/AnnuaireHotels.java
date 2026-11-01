package agence.service;

import agence.client.hotel.*;
import agence.service.exception.HotelInjoignableException;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

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
        HotelPort port =  new HotelService(URI.create(urlWsdl).toURL()).getHotelSoapPort();
        //Delais
        Map<String, Object> ctx = ((BindingProvider) port).getRequestContext();
        ctx.put("jakarta.xml.ws.client.connectionTimeout", 2_000);
        ctx.put("jakarta.xml.ws.client.receiveTimeout", 5_000);
        return port;
    }

    public List<Offre> rechercheOffre(ArgumentsRecherche argrr) {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        try {
            List<CompletableFuture<List<Offre>>> taches = hotels().stream()
                    .map(wsdl -> CompletableFuture
                            .supplyAsync(() -> interroger(wsdl, argrr), pool)
                            .completeOnTimeout(List.of(), 8, TimeUnit.SECONDS)
                            .exceptionally(e -> {
                                System.out.println(wsdl + " injoignable");
                                return List.of();
                            }))
                    .toList();

            List<Offre> offres = new ArrayList<>();
            for (CompletableFuture<List<Offre>> t : taches) {
                offres.addAll(t.join());
            }
            return offres;
        } finally {
            pool.shutdownNow();
        }
    }

    private List<Offre> interroger(String wsdl, ArgumentsRecherche argrr) {
        try {
            return port(wsdl).rechercheOffre(argrr);
        } catch (AgenceInconueException_Exception e) {
            System.out.println(wsdl + " : refus (" + e.getMessage() + ")");
            return List.of();
        } catch (MalformedURLException e) {
            throw new WebServiceException("URL invalide : " + wsdl, e);
        }
    }

    private static final int MAX_ESSAIS = 3;


    public Confirmation reserverOffre(ArgumentsReservation argrr) {
        Confirmation c = new Confirmation();
        try {
            c = reservation(argrr);
            System.out.println(c.isDejaReserve()
                    ? "Réservation déjà enregistrée : " + c.getRefReservation()
                    : "Réservation créée : " + c.getRefReservation());

        } catch (ErreurParametreReservationExeption_Exception e) {
            // refus de l'hôtel : réponse fiable, la demande n'a pas été enregistrée
            System.out.println("Réservation refusée : " + e.getMessage()
                    + " [" + e + "]");

        } catch (HotelInjoignableException e) {
            // panne : on ne sait pas si c'est enregistré
            System.out.println(e.getMessage() + ". Relancez la même demande pour vérifier.");
        }
        return c;
    }
    public Confirmation reservation(ArgumentsReservation argrr)
            throws ErreurParametreReservationExeption_Exception, HotelInjoignableException {

        String hotel = argrr.getAdresseHotel();
        WebServiceException derniere = null;


        for (int essai = 1; essai <= MAX_ESSAIS; essai++) {
            try {
                // même argrr, donc même demandeId à chaque essai
                return port(hotel).reserverOffre(argrr);

            } catch (SOAPFaultException e) {
                // le serveur a répondu par une erreur interne : inutile de rejouer
                throw new HotelInjoignableException(hotel, e);

            } catch (WebServiceException e) {
                // réseau coupé, délai dépassé : on rejoue la même demande
                derniere = e;
                pause(500L * essai);

            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Adresse d'hôtel invalide : " + hotel, e);
            }
            // les fautes métier (ErreurParametre...) ne sont PAS attrapées ici : elles remontent
        }
        throw new HotelInjoignableException(hotel, derniere);
    }

    private static void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}