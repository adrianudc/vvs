package modelo.contenido;

import java.util.List;

/**
 *
 */

public interface Contenido {

    /**
     * Devuelve el titulo del contenido.
     * @return titulo del contenido
     */
    String obtenerTitulo();

    /**
     * Devuelve la duracion del contenido.
     * @return duracion del contenido
     */

    int obtenerDuracion();

    /**
     * Devuelve la lista de reproduccion del contenido.
     * @return lista de reproducción del contenido
     */

    List<Contenido> obtenerListaReproduccion();

    /**
     * Devuelve un lista de los Contenidos en la lista de reproduccion
     * cuyo titulo contiene la subcadena.
     * @param subcadena contenida en el el titulo de los contenidos de la lista
     * @return lista de contenidos cuyo titulo contiene subcadena
     */

    List<Contenido> buscar(String subcadena);

    /**
     * Inserta un nuevo Contenido en la posicion siguiente al Contenido
     * predecesor si este esta presente en la lista de reproduccion, o bien
     * se inserta al final de la lista si no lo esta.
     * @param contenido contenido a agregar
     * @param predecesor despues del cual se inserta el nuevo contenido
     */

    void agregar(Contenido contenido, Contenido predecesor);

    /**
     * Elimina el contenido de la lista de reproduccion si esta presente.
     * No hace nada en caso contrario.
     * @param contenido a eliminar
     */

    void eliminar(Contenido contenido);

}
