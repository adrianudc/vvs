package modelo.servidor.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import org.op4j.Op;
import org.op4j.functions.ExecCtx;
import org.op4j.functions.IFunction;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.net.httpserver.HttpServer;

import modelo.contenido.Contenido;
import modelo.servidor.Servidor;
import modelo.token.Token;
import util.servidor.ServidorUtil;
import util.servidor.handler.AltaHandler;
import util.servidor.handler.BajaHandler;
import util.servidor.handler.BuscarHandler;
import util.token.TokenUtil;

public class ServidorImpl implements Servidor {

    private HttpServer httpServer;

    private Map<String, Token> tokens;

    private List<Contenido> contenidos;

    public ServidorImpl(int puerto) {
        try {
            tokens = Maps.newHashMap();
            contenidos = Lists.newArrayList();

            httpServer = HttpServer.create(new InetSocketAddress(puerto), 0);
            httpServer.createContext("/alta", new AltaHandler(this));
            httpServer.createContext("/baja", new BajaHandler(this));
            httpServer.createContext("/buscar", new BuscarHandler(this));
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
        if (token.equals(TokenUtil.ADMIN_TOKEN)) {
            this.contenidos.add(contenido);
        }
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
                return input.obtenerTitulo().contains(subcadena);
            }
        }).get();

        Token mToken = tokens.get(token);
        if (mToken == null) {
            Contenido publicidad = ServidorUtil.obtenerPublicidad();
            result.add(0, publicidad);
            
            for (int i = 5; i < result.size() - 1; i += 5) {
                result.add(i, publicidad);
            }
        } else {
            TokenUtil.incrementarContador(mToken);
        }

        return result;
    }

    @Override
    public boolean comprobarToken(String token) {
        return tokens.containsKey(token);
    }
}
