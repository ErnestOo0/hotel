package org.example.pointAcces.arguments;

public class InfoBancaires {
    private String numero;
    private Expiration expiration;
    private String cvc;

    private InfoBancaires(){}

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Expiration getExpiration() {
        return expiration;
    }
    public void setExpiration(Expiration expiration) {
        this.expiration = expiration;
    }

    public String getCvc() {
        return cvc;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
