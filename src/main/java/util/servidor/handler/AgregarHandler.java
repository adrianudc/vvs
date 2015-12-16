package util.servidor.handler;

import com.sun.net.httpserver.HttpExchange;
import modelo.contenido.Contenido;
import modelo.contenido.impl.ContenidoImpl;
import modelo.servidor.Servidor;
import org.apache.http.HttpStatus;
import util.http.HttpUtil;
import util.json.JSONUtil;
import util.servidor.BaseHandler;
import util.token.TokenUtil;

import java.io.IOException;

/**
 *
 */
public class AgregarHandler extends BaseHandler {

    /**
     *
     */
    private Servidor servidor;

    /**
     * @param servidor -
     */
    public AgregarHandler(final Servidor servidor) {
        this.servidor = servidor;
    }

    /**
     * @param httpExchange
     * @throws IOException
     */
    @Override
    public void handleRequest(final HttpExchange httpExchange) throws IOException {
        String token = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "token");

        if (token == null || !token.equals(TokenUtil.ADMIN_TOKEN)) {
            httpExchange.sendResponseHeaders(HttpStatus.SC_FORBIDDEN, 0);
        } else {
            try {
                String contenidoString = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "contenido");
                Contenido contenido = JSONUtil.jsonToObject(contenidoString, ContenidoImpl.class);
                servidor.agregar(contenido, token);
                httpExchange.sendResponseHeaders(HttpStatus.SC_OK, 0);
            } catch (Exception e) {
                httpExchange.sendResponseHeaders(HttpStatus.SC_BAD_REQUEST, 0);
            }
        }
    }
}
