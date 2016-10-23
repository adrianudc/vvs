package util.servidor;

import java.io.IOException;

import org.apache.http.HttpStatus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 *
 */
public abstract class BaseHandler implements HttpHandler {

    /**
     *
     * @param httpExchange -
     * @throws IOException -
     */
    @Override
    public void handle(final HttpExchange httpExchange) throws IOException {
        try {
            handleRequest(httpExchange);
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            httpExchange.sendResponseHeaders(HttpStatus.SC_INTERNAL_SERVER_ERROR, 0);
        }
    }

    /**
     *
     * @param httpExchange -
     * @throws IOException -
     */
    public abstract void handleRequest(final HttpExchange httpExchange) throws IOException;
}
