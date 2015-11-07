package servidor;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.servidor.Servidor;
import modelo.servidor.impl.ServidorImpl;
import util.http.HttpUtil;
import util.servidor.ServidorTestUtil;

import static junit.framework.TestCase.assertEquals;

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
        assertEquals(response, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void servidorAltaTestResponseOK() throws IOException {
        HttpURLConnection connection = HttpUtil.sendGet("http://localhost:8080/alta");
        int response = connection.getResponseCode();
        assertEquals(response, HttpStatus.SC_OK);
    }

    @Test
    public void servidorBajaTestResponseOK() throws IOException {
        connection = HttpUtil.sendGet("http://localhost:8080/alta");
        int response = connection.getResponseCode();
        assertEquals(response, HttpStatus.SC_OK);
        String token = IOUtils.toString(connection.getInputStream(), Charsets.UTF_8.name());

        connection = HttpUtil.sendPost("http://localhost:8080/baja", ServidorTestUtil.getTokenParams(token));
        response = connection.getResponseCode();
        assertEquals(response, HttpStatus.SC_OK);
    }

    @Test
    public void servidorBajaTestResponseBadRequest() throws IOException {
        connection = HttpUtil.sendGet("http://localhost:8080/baja");
        int response = connection.getResponseCode();
        assertEquals(response, HttpStatus.SC_BAD_REQUEST);
    }
}
