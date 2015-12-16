package modelo.servidor;

/**
 *
 */
public interface TokenServidor {

    /**
     *
     * @param token a comprobar
     * @return true si es valido, false si no lo es
     */
    boolean comprobarToken(String token);

}
