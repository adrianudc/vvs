package util.servidor;

import java.math.BigInteger;
import java.security.SecureRandom;

import modelo.token.Token;

public class ServidorUtil {

    private static SecureRandom random = new SecureRandom();

    public static Token generarToken() {
        return new Token(randomToken());
    }

    private static String randomToken() {
        return new BigInteger(130, random).toString(32);
    }

}
