package app;

import modelo.servidor.Servidor;
import modelo.servidor.impl.ServidorImpl;

public class App {

    public static void main(String[] args) {
        Servidor servidor = new ServidorImpl(8080);
    }
}
