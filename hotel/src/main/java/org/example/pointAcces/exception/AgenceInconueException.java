package org.example.pointAcces.exception;

import jakarta.xml.ws.WebFault;

@WebFault(name="agenceInconueFault")
public class AgenceInconueException extends Exception {
    public AgenceInconueException() {
        super();
    }

    public AgenceInconueException(String message) {
        super(message);
    }
}
