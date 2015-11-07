package modelo.servidor.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.sun.net.httpserver.HttpServer;

import modelo.contenido.Contenido;
import modelo.servidor.Servidor;
import modelo.token.Token;
import util.servidor.ServidorUtil;
import util.servidor.handler.AltaHandler;
import util.servidor.handler.BajaHandler;

public class ServidorImpl implements Servidor {

    private HttpServer httpServer;

    private Map<String, Token> tokens;

    public ServidorImpl(int puerto) {
        try {
            tokens = Maps.newHashMap();
            httpServer = HttpServer.create(new InetSocketAddress(puerto), 0);
            httpServer.createContext("/alta", new AltaHandler(this));
            httpServer.createContext("/baja", new BajaHandler(this));
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            System.out.println("Error trying to create server");
        }
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }

    @Override
    public String obtenerNombre() {
        return null;
    }

    @Override
    public String alta() {
        Token token = ServidorUtil.generarToken();
        tokens.put(token.getValor(), token);
        return token.getValor();
    }

    @Override
    public void baja(String token) {
        tokens.remove(token);
    }

    @Override
    public void agregar(Contenido contenido, String token) {

    }

    @Override
    public void eliminar(Contenido contenido, String token) {

    }

    @Override
    public List<Contenido> buscar(String subcadena, String token) {
        return null;
    }

    @Override
    public boolean comprobarToken(String token) {
        return tokens.containsKey(token);
    }
}
