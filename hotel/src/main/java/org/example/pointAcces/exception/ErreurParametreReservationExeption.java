package org.example.pointAcces.exception;

import jakarta.xml.ws.WebFault;

import java.util.concurrent.ExecutionException;

@WebFault(name="erreurParametreReservationFault")
public class ErreurParametreReservationExeption extends Exception {
    public ErreurParametreReservationExeption() {}
    public ErreurParametreReservationExeption(String message) {
        super(message);
    }
}
