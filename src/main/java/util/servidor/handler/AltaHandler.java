package util.servidor.handler;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.HttpStatus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import modelo.servidor.Servidor;

public class AltaHandler implements HttpHandler {

    private Servidor servidor;

    public AltaHandler(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String token = servidor.alta();
        System.out.println("Generated token: " + token);
        httpExchange.sendResponseHeaders(HttpStatus.SC_OK, token.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(token.getBytes());
        os.close();
    }
}
