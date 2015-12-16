package util.servidor;

import modelo.contenido.Contenido;
import modelo.contenido.impl.track.Anuncio;
import modelo.token.Token;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 */
public class ServidorUtil {

    /**
     *
     */
    public static final Contenido PUBLICIDAD = new Anuncio();

    /**
     *
     */
    private static SecureRandom random = new SecureRandom();

    /**
     *
     * @return nuevo token
     */
    public static Token generarToken() {
        return new Token(randomToken());
    }

    /**
     *
     * @return valor aleatorio para asignar a un token
     */
    private static String randomToken() {
        return new BigInteger(130, random).toString(32);
    }

    /**
     *
     * @return valor de publicidad
     */
    public static Contenido obtenerPublicidad() {
        return PUBLICIDAD;
    }

}
