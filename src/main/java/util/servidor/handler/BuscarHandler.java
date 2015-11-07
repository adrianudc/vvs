package util.servidor.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import modelo.contenido.Contenido;
import modelo.servidor.Servidor;
import util.http.HttpUtil;

public class BuscarHandler implements HttpHandler {

    private Servidor servidor;

    public BuscarHandler(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String token = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "token");
        String subCadena = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "subCadena");

        List<Contenido> contenidos = servidor.buscar(subCadena, token);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String respuestaJson = objectMapper.writeValueAsString(contenidos);

        httpExchange.sendResponseHeaders(HttpStatus.SC_OK, respuestaJson.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(respuestaJson.getBytes());
        os.close();
    }
}
