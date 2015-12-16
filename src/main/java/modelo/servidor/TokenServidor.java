package modelo.servidor;

public interface TokenServidor extends Servidor {

    boolean comprobarToken(String token);

}
