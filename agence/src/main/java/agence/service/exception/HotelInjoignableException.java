package agence.service.exception;

public class HotelInjoignableException extends Exception {
    public HotelInjoignableException(String hotel, Throwable cause) {
        super("Hôtel injoignable : " + hotel + " (résultat de la réservation inconnu)", cause);
    }
}