package util.servidor.handler;

import com.sun.net.httpserver.HttpExchange;
import modelo.servidor.Servidor;
import org.apache.http.HttpStatus;
import util.servidor.BaseHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 */
public class AltaHandler extends BaseHandler {

    /**
     *
     */
    private Servidor servidor;

    /**
     * @param servidor -
     */
    public AltaHandler(final Servidor servidor) {
        this.servidor = servidor;
    }

    /**
     * @param httpExchange -
     * @throws IOException -
     */
    @Override
    public void handleRequest(final HttpExchange httpExchange) throws IOException {
        String token = servidor.alta();
        System.out.println("Generated token: " + token);
        httpExchange.sendResponseHeaders(HttpStatus.SC_OK, token.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(token.getBytes());
        os.close();
    }
}
