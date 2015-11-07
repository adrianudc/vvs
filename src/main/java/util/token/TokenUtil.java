package util.token;

import modelo.token.Token;

public class TokenUtil {

    public static void incrementarContador(Token token) {
        incrementarContador(token, 1);
    }

    public static void incrementarContador(Token token, int incremento) {
        token.setContador(token.getContador() + incremento);
    }

}
