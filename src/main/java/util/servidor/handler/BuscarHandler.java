package util.servidor.handler;

import com.sun.net.httpserver.HttpExchange;
import modelo.contenido.Contenido;
import modelo.servidor.Servidor;
import org.apache.http.HttpStatus;
import util.http.HttpUtil;
import util.json.JSONUtil;
import util.servidor.BaseHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 *
 */
public class BuscarHandler extends BaseHandler {

    /**
     *
     */
    private Servidor servidor;

    /**
     *
     * @param servidor -
     */
    public BuscarHandler(final Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void handleRequest(final HttpExchange httpExchange) throws IOException {
        String token = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "token");
        String subCadena = HttpUtil.getQueryValueFromHttpExchange(httpExchange, "subCadena");

        List<Contenido> contenidos = servidor.buscar(subCadena, token);
        String respuestaJson = JSONUtil.objectTOJSON(contenidos);
        httpExchange.sendResponseHeaders(HttpStatus.SC_OK, respuestaJson.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(respuestaJson.getBytes());
        os.close();
    }
}
