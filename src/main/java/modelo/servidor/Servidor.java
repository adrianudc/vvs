package modelo.servidor;

import modelo.contenido.Contenido;

import java.util.List;

/**
 *
 */

public interface Servidor {

    /**
     *
     * @return nombre del servidor
     */
    String obtenerNombre();

    /**
     *
     * @return valor del token
     */
    String alta();

    /**
     *
     * @param token que va a ser dado de baja
     */
    void baja(String token);

    /**
     *
     * @param contenido a agregar
     * @param token -
     */
    void agregar(Contenido contenido, String token);

    /**
     *
     * @param contenido a eliminar
     * @param token -
     */
    void eliminar(Contenido contenido, String token);

    /**
     *
     * @param subcadena por la que se busca
     * @param token -
     * @return -
     */
    List<Contenido> buscar(String subcadena, String token);
}
