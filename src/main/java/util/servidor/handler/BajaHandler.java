package util.servidor.handler;

import java.io.IOException;

import org.apache.http.HttpStatus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import modelo.servidor.Servidor;
import util.http.HttpUtil;

public class BajaHandler implements HttpHandler {

    private Servidor servidor;

    public BajaHandler(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String token = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "token");

        if (servidor.comprobarToken(token)) {
            servidor.baja(token);
            httpExchange.sendResponseHeaders(HttpStatus.SC_OK, 0);
        } else {
            httpExchange.sendResponseHeaders(HttpStatus.SC_BAD_REQUEST, 0);
        }
    }
}
