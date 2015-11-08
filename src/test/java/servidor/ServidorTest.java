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
import modelo.contenido.impl.ContenidoImpl;
import modelo.servidor.Servidor;
import modelo.servidor.impl.ServidorBackup;
import modelo.servidor.impl.ServidorImpl;
import util.http.HttpUtil;
import util.json.JSONUtil;
import util.servidor.ServidorTestUtil;
import util.servidor.ServidorUtil;
import util.token.TokenUtil;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ServidorTest {

    private static Servidor servidor;

    private static Servidor servidorBackup1;

    private static Servidor servidorBackup2;

    private static Servidor servidorBackup3;

    private static HttpURLConnection connection;

    @Before
    public void setUp() {
        servidor = new ServidorImpl("Servidor1", 8080);
        servidorBackup3 = new ServidorBackup("Servidor3", 8083, null);
        servidorBackup2 = new ServidorBackup("Servidor2", 8082, (ServidorBackup) servidorBackup3);
        servidorBackup1 = new ServidorBackup("Servidor1", 8081, (ServidorBackup) servidorBackup2);
    }

    @After
    public void cleanUp() {
        ((ServidorImpl) servidor).getHttpServer().stop(0);
        ((ServidorBackup) servidorBackup1).getHttpServer().stop(0);
        ((ServidorBackup) servidorBackup2).getHttpServer().stop(0);
        ((ServidorBackup) servidorBackup3).getHttpServer().stop(0);
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
    public void servidorBuscarTestSinPublicidad() throws IOException {
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

        String jsonResponse = buscarContenido("Test");
        List<ContenidoImpl> result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        List<Contenido> expected = Lists
                .newArrayList(ServidorUtil.obtenerPublicidad(), contenido1, contenido2, contenido3,
                        ServidorUtil.obtenerPublicidad(), contenido4, contenido5, contenido6,
                        ServidorUtil.obtenerPublicidad(), contenido7);

        assertEquals(expected, result);

        List<Contenido> wrongExpected = Lists
                .newArrayList(ServidorUtil.obtenerPublicidad(), contenido1, contenido2, contenido3,
                        ServidorUtil.obtenerPublicidad(), contenido4, contenido5, contenido6,
                        contenido7, ServidorUtil.obtenerPublicidad());

        assertNotEquals(wrongExpected, result);
    }

    @Test
    public void servidorEliminarYBuscarTestResultOK() throws IOException {
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

        String jsonResponse = buscarContenido("Test");
        List<ContenidoImpl> result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        List<Contenido> expected = Lists
                .newArrayList(ServidorUtil.obtenerPublicidad(), contenido1, contenido2, contenido3,
                        ServidorUtil.obtenerPublicidad(), contenido4, contenido5, contenido6,
                        ServidorUtil.obtenerPublicidad(), contenido7);

        assertEquals(expected, result);

        Map<String, String> params = ServidorTestUtil.getTokenParams(TokenUtil.ADMIN_TOKEN);
        params.put("contenido", JSONUtil.objectTOJSON(contenido4));
        connection = HttpUtil.sendPost("http://localhost:8080/eliminar", params);
        connection.getResponseCode();

        jsonResponse = buscarContenido("Test");
        result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        expected = Lists
                .newArrayList(ServidorUtil.obtenerPublicidad(), contenido1, contenido2, contenido3,
                        ServidorUtil.obtenerPublicidad(), contenido5, contenido6, contenido7);

        assertEquals(expected, result);
    }

    @Test
    public void servidorBackupAgregarContenidoTestResultOK() throws IOException {
        Map<String, String> params = ServidorTestUtil.getTokenParams(TokenUtil.ADMIN_TOKEN);
        Contenido contenido = new ContenidoImpl("Test", 23);
        params.put("contenido", JSONUtil.objectTOJSON(contenido));
        connection = HttpUtil.sendPost("http://localhost:8082/agregar", params);
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);

        Contenido contenido1 = new ContenidoImpl("Test2", 30);
        params.put("contenido", JSONUtil.objectTOJSON(contenido1));
        connection = HttpUtil.sendPost("http://localhost:8081/agregar", params);
        response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);

        Contenido contenido2 = new ContenidoImpl("OtherTest3", 30);
        params.put("contenido", JSONUtil.objectTOJSON(contenido2));
        connection = HttpUtil.sendPost("http://localhost:8083/agregar", params);
        response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);

        Contenido contenido3 = new ContenidoImpl("OtherTest4", 30);
        params.put("contenido", JSONUtil.objectTOJSON(contenido3));
        connection = HttpUtil.sendPost("http://localhost:8083/agregar", params);
        response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);

        String jsonResponse = buscarContenido("http://localhost:8081/buscar", "Test", null, false);
        List<ContenidoImpl> result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        List<Contenido> expected = Lists.newArrayList(ServidorUtil.obtenerPublicidad(), contenido1);
        assertEquals(expected, result);

        jsonResponse = buscarContenido("http://localhost:8082/buscar", "othertest", null, false);
        result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        expected = Lists.newArrayList(ServidorUtil.obtenerPublicidad(), contenido2, contenido3);
        assertEquals(expected, result);
    }

    @Test
    public void servidorBuscarConCaducidadDeToken() throws IOException {
        String token = obtenerTokenAltaServidor();
        String jsonResponse = buscarContenido("Test", token); // Token value: 1

        List<ContenidoImpl> result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        assertFalse(result.contains(ServidorUtil.obtenerPublicidad()));

        buscarContenido("Test", token);
        buscarContenido("Test", token);
        buscarContenido("Test", token);
        buscarContenido("Test", token); // Token value: 5
        buscarContenido("Test", token);
        buscarContenido("Test", token);
        buscarContenido("Test", token);
        buscarContenido("Test", token);
        buscarContenido("Test", token); // Token value: 10

        result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        assertFalse(result.contains(ServidorUtil.obtenerPublicidad()));

        jsonResponse = buscarContenido("Test", token); // Token value: 10

        result = Lists.newArrayList(JSONUtil.jsonToObject(jsonResponse, ContenidoImpl[].class));
        assertTrue(result.contains(ServidorUtil.obtenerPublicidad()));
    }

    // Static methods

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

    private static String buscarContenido(String subCadena) throws IOException {
        return buscarContenido("http://localhost:8080/buscar", subCadena, null, false);
    }

    private static String buscarContenido(String subCadena, String token) throws IOException {
        return buscarContenido("http://localhost:8080/buscar", subCadena, token, false);
    }

    private static String buscarContenido(String url, String subCadena, String token, boolean useAdminToken) throws
            IOException {
        Map<String, String> params = Maps.newHashMap();
        if (useAdminToken) {
            params.put("token", TokenUtil.ADMIN_TOKEN);
        } else if (token != null) {
            params.put("token", token);
        }

        params.put("subCadena", subCadena);
        connection = HttpUtil.sendPost(url, params);
        int response = connection.getResponseCode();
        assertEquals(HttpStatus.SC_OK, response);

        return IOUtils.toString(connection.getInputStream(), Charsets.UTF_8.name());
    }
}
