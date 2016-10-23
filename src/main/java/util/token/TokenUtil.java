package util.token;

import modelo.token.Token;

/**
 *
 */
public abstract class TokenUtil {

    /**
     *
     */
    public static final String ADMIN_TOKEN = "bjk35hj345kf3hjkt4fdfj2f2";

    /**
     *
     * @param token a incrementar
     */
    public static void incrementarContador(final Token token) {
        incrementarContador(token, 1);
    }

    /**
     *
     * @param token a incrementar
     * @param incremento valor del incremento
     */
    private static void incrementarContador(final Token token, final int incremento) {
        token.setContador(token.getContador() + incremento);
    }

}
