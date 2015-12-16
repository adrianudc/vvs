package util.servidor.handler;

import java.io.IOException;

import org.apache.http.HttpStatus;

import com.sun.net.httpserver.HttpExchange;

import modelo.servidor.TokenServidor;
import util.http.HttpUtil;
import util.servidor.BaseHandler;

public class BajaHandler extends BaseHandler {

    private TokenServidor servidor;

    public BajaHandler(TokenServidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void handleRequest(HttpExchange httpExchange) throws IOException {
        String token = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "token");

        if (servidor.comprobarToken(token)) {
            servidor.baja(token);
            httpExchange.sendResponseHeaders(HttpStatus.SC_OK, 0);
        } else {
            httpExchange.sendResponseHeaders(HttpStatus.SC_BAD_REQUEST, 0);
        }
    }
}
