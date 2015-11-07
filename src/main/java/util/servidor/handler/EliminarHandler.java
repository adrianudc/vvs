package util.servidor.handler;

import java.io.IOException;

import org.apache.http.HttpStatus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import modelo.contenido.Contenido;
import modelo.contenido.ContenidoImpl;
import modelo.servidor.Servidor;
import util.http.HttpUtil;
import util.json.JSONUtil;
import util.token.TokenUtil;

public class EliminarHandler implements HttpHandler {

    private Servidor servidor;

    public EliminarHandler(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String token = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "token");

        if (token == null || !token.equals(TokenUtil.ADMIN_TOKEN)) {
            httpExchange.sendResponseHeaders(HttpStatus.SC_FORBIDDEN, 0);
        } else {
            try {
                String contenidoString = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "contenido");
                Contenido contenido = JSONUtil.jsonToObject(contenidoString, ContenidoImpl.class);
                servidor.eliminar(contenido, token);
                httpExchange.sendResponseHeaders(HttpStatus.SC_OK, 0);
            } catch (Exception e) {
                httpExchange.sendResponseHeaders(HttpStatus.SC_BAD_REQUEST, 0);
            }
        }
    }
}
