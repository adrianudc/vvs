package util.token;

import modelo.token.Token;

public class TokenUtil {

    public final static String ADMIN_TOKEN = "bjk35hj345kf3hjkt4fdfj2f2";

    public static void incrementarContador(Token token) {
        incrementarContador(token, 1);
    }

    public static void incrementarContador(Token token, int incremento) {
        token.setContador(token.getContador() + incremento);
    }

}
