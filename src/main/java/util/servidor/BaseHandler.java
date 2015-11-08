package util.servidor;

import java.io.IOException;

import org.apache.http.HttpStatus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class BaseHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            handleRequest(httpExchange);
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            httpExchange.sendResponseHeaders(HttpStatus.SC_INTERNAL_SERVER_ERROR, 0);
        }
    }

    public abstract void handleRequest(HttpExchange httpExchange) throws IOException;
}
