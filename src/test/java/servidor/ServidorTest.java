package servidor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import modelo.contenido.Contenido;
import modelo.contenido.ContenidoImpl;
import modelo.servidor.Servidor;
import modelo.servidor.impl.ServidorImpl;
import util.http.HttpUtil;
import util.json.JSONUtil;
import util.servidor.ServidorTestUtil;
import util.servidor.ServidorUtil;
import util.token.TokenUtil;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ServidorTest {

    private static Servidor servidor;

    private static HttpURLConnection connection;

    @Before
    public void setUp() {
        servidor = new ServidorImpl(8080);
    }

    @After
    public void cleanUp() {
        ((ServidorImpl) servidor).getHttpServer().stop(0);
        connection.disconnect();
    }

    @Test
    public void servidorTestResponseNotFound() throws IOException {
        connection = HttpUtil.sendGet("http://localhost:8080/fail");
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_NOT_FOUND, response);
    }

    @Test
    public void servidorAltaTestResponseOK() throws IOException {
        HttpURLConnection connection = HttpUtil.sendGet("http://localhost:8080/alta");
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);
    }

    @Test
    public void servidorBajaTestResponseOK() throws IOException {
        String token = obtenerTokenAltaServidor();

        connection = HttpUtil.sendPost("http://localhost:8080/baja", ServidorTestUtil.getTokenParams(token));
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);
    }

    @Test
    public void servidorBajaTestResponseBadRequest() throws IOException {
        connection = HttpUtil.sendGet("http://localhost:8080/baja");
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_BAD_REQUEST, response);
    }

    @Test
    public void servidorAgregarContenidoTestResultOK() throws IOException {
        Map<String, String> params = ServidorTestUtil.getTokenParams(TokenUtil.ADMIN_TOKEN);
        Contenido contenido = new ContenidoImpl("Test", 23);
        params.put("contenido", JSONUtil.objectTOJSON(contenido));
        connection = HttpUtil.sendPost("http://localhost:8080/agregar", params);
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);
    }

    @Test
    public void servidorAgregarContenidoTestForbidden() throws IOException {
        Map<String, String> params = ServidorTestUtil.getTokenParams(obtenerTokenAltaServidor());
        Contenido contenido = new ContenidoImpl("Test", 23);
        params.put("contenido", JSONUtil.objectTOJSON(contenido));
        connection = HttpUtil.sendPost("http://localhost:8080/agregar", params);
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_FORBIDDEN, response);
    }

    @Test
    public void servidorBuscarTestEmptyResult() throws IOException {
        Map<String, String> params = ServidorTestUtil.getTokenParams(obtenerTokenAltaServidor());
        params.put("subCadena", "test");
        connection = HttpUtil.sendPost("http://localhost:8080/buscar", params);
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);
    }

    @Test
    public void servidorBuscarTestPublicidadResult() throws IOException {
        Map<String, String> params = Maps.newHashMap();
        params.put("subCadena", "test");
        connection = HttpUtil.sendPost("http://localhost:8080/buscar", params);
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);

        String jsonResponse = IOUtils.toString(connection.getInputStream(), Charsets.UTF_8.name());
        List<ContenidoImpl> result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        List<Contenido> expected = Lists.newArrayList(ServidorUtil.obtenerPublicidad());
        assertEquals(expected, result);
    }

    @Test
    public void servidorAgregarYBuscarTestResultOK() throws IOException {
        Contenido contenido1 = new ContenidoImpl("Test", 23);
        agregarContenido(contenido1);
        Contenido contenido2 = new ContenidoImpl("Test2", 24);
        agregarContenido(contenido2);
        Contenido contenido3 = new ContenidoImpl("Test3", 30);
        agregarContenido(contenido3);
        Contenido contenido4 = new ContenidoImpl("Test4", 32);
        agregarContenido(contenido4);
        Contenido contenido5 = new ContenidoImpl("Test5", 16);
        agregarContenido(contenido5);
        Contenido contenido6 = new ContenidoImpl("Test6", 13);
        agregarContenido(contenido6);
        Contenido contenido7 = new ContenidoImpl("Test7", 23);
        agregarContenido(contenido7);

        Map<String, String> params = Maps.newHashMap();
        params.put("subCadena", "Test");
        connection = HttpUtil.sendPost("http://localhost:8080/buscar", params);
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);

        String jsonResponse = IOUtils.toString(connection.getInputStream(), Charsets.UTF_8.name());
        List<ContenidoImpl> result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        List<Contenido> expected = Lists
                .newArrayList(ServidorUtil.obtenerPublicidad(), contenido1, contenido2, contenido3, contenido4,
                        ServidorUtil.obtenerPublicidad(), contenido5, contenido6, contenido7);

        assertEquals(expected, result);

        List<Contenido> wrongExpected = Lists
                .newArrayList(ServidorUtil.obtenerPublicidad(), contenido1, contenido2, contenido3, contenido4,
                        contenido5, contenido6, contenido7);

        assertNotEquals(wrongExpected, result);
    }

    private static String obtenerTokenAltaServidor() throws IOException {
        connection = HttpUtil.sendGet("http://localhost:8080/alta");
        return IOUtils.toString(connection.getInputStream(), Charsets.UTF_8.name());
    }

    private static void agregarContenido(Contenido contenido) throws IOException {
        Map<String, String> params = ServidorTestUtil.getTokenParams(TokenUtil.ADMIN_TOKEN);
        params.put("contenido", JSONUtil.objectTOJSON(contenido));
        connection = HttpUtil.sendPost("http://localhost:8080/agregar", params);
        connection.getResponseCode();
    }
}
