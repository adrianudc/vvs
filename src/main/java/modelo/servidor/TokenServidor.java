package modelo.servidor;

/**
 *
 */
public interface TokenServidor extends Servidor {

    /**
     *
     * @param token a comprobar
     * @return true si es valido, false si no lo es
     */
    boolean comprobarToken(String token);

}
