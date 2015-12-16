package util.servidor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.HttpStatus;

import java.io.IOException;

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
     */
    public abstract void handleRequest(final HttpExchange httpExchange) throws IOException;
}
