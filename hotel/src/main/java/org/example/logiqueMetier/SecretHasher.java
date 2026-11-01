package org.example.logiqueMetier;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public final class SecretHasher {

    private static final String ALGO = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 600_000;
    private static final int SEL_OCTETS = 16;
    private static final int CLE_BITS = 256;
    private static final SecureRandom RNG = new SecureRandom();

    public record Empreinte(byte[] sel, int iterations, byte[] hash) { }

    private SecretHasher() { }

    /** Hacher : à l'enregistrement de l'agence. */
    public static Empreinte deriver(char[] secret) {
        byte[] sel = new byte[SEL_OCTETS];
        RNG.nextBytes(sel);                                   // étape 1
        byte[] hash = calculer(secret, sel, ITERATIONS);      // étape 2
        return new Empreinte(sel, ITERATIONS, hash);          // étape 3 : à stocker
    }

    /** Vérifier : à chaque réservation. */
    public static boolean verifier(char[] secretPresente, Empreinte stockee) {
        byte[] calcule = calculer(secretPresente, stockee.sel(), stockee.iterations());
        return MessageDigest.isEqual(calcule, stockee.hash());
    }

    private static byte[] calculer(char[] secret, byte[] sel, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(secret, sel, iterations, CLE_BITS);
        try {
            return SecretKeyFactory.getInstance(ALGO).generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException("Dérivation impossible", e);
        } finally {
            spec.clearPassword();
        }
    }
}