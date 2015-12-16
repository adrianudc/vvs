package util.servidor.handler;

import com.sun.net.httpserver.HttpExchange;
import modelo.servidor.Servidor;
import org.apache.http.HttpStatus;
import util.http.HttpUtil;
import util.servidor.BaseHandler;

import java.io.IOException;

/**
 *
 */
public class BajaHandler extends BaseHandler {

    /**
     *
     */
    private Servidor servidor;

    /**
     *
     * @param servidor -
     */
    public BajaHandler(final Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void handleRequest(final HttpExchange httpExchange) throws IOException {
        String token = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "token");

        if (servidor.comprobarToken(token)) {
            servidor.baja(token);
            httpExchange.sendResponseHeaders(HttpStatus.SC_OK, 0);
        } else {
            httpExchange.sendResponseHeaders(HttpStatus.SC_BAD_REQUEST, 0);
        }
    }
}
