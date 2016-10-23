package app;

import modelo.servidor.Servidor;
import modelo.servidor.impl.ServidorImpl;

/**
 *
 */
public abstract class App {

    /**
     *
     * @param args -
     */
    public static void main(final String[] args) {
        Servidor servidor = new ServidorImpl("Servidor", 8080);
    }
}
