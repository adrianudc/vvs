package modelo.servidor.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.op4j.Op;
import org.op4j.functions.ExecCtx;
import org.op4j.functions.IFunction;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.net.httpserver.HttpServer;

import modelo.contenido.Contenido;
import modelo.servidor.TokenServidor;
import modelo.token.Token;
import util.servidor.ServidorUtil;
import util.servidor.handler.AgregarHandler;
import util.servidor.handler.AltaHandler;
import util.servidor.handler.BajaHandler;
import util.servidor.handler.BuscarHandler;
import util.servidor.handler.EliminarHandler;
import util.token.TokenUtil;

public class ServidorImpl implements TokenServidor {

    private HttpServer httpServer;

    private Map<String, Token> tokens;

    private List<Contenido> contenidos;

    private String nombre;

    public ServidorImpl(String nombre, int puerto) {
        try {
            this.nombre = nombre;
            tokens = Maps.newHashMap();
            contenidos = Lists.newArrayList();

            httpServer = HttpServer.create(new InetSocketAddress(puerto), 0);
            httpServer.createContext("/alta", new AltaHandler(this));
            httpServer.createContext("/baja", new BajaHandler(this));
            httpServer.createContext("/buscar", new BuscarHandler(this));
            httpServer.createContext("/agregar", new AgregarHandler(this));
            httpServer.createContext("/eliminar", new EliminarHandler(this));
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            System.out.println("Error trying to create server: " + e.getMessage());
        }
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }

    @Override
    public String obtenerNombre() {
        return nombre;
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
        this.contenidos.add(contenido);
    }

    @Override
    public void eliminar(Contenido contenido, String token) {
        if (token.equals(TokenUtil.ADMIN_TOKEN)) {
            this.contenidos.remove(contenido);
        }
    }

    @Override
    public List<Contenido> buscar(final String subcadena, String token) {
        List<Contenido> result;

        result = Op.onList(contenidos).removeAllFalse(new IFunction<Contenido, Boolean>() {
            @Override
            public Boolean execute(Contenido input, ExecCtx ctx) throws Exception {
                return StringUtils.containsIgnoreCase(input.obtenerTitulo(), subcadena);
            }
        }).get();

        Token mToken = tokens.get(token);
        if (mToken == null) {
            Contenido publicidad = ServidorUtil.obtenerPublicidad();
            result.add(0, publicidad);

            for (int i = 4; i < result.size(); i += 4) {
                result.add(i, publicidad);
            }
        } else {
            TokenUtil.incrementarContador(mToken);
            if (mToken.getContador() >= 10) {
                tokens.remove(mToken.getValor());
            }
        }

        return result;
    }

    @Override
    public boolean comprobarToken(String token) {
        return tokens.containsKey(token);
    }
}
