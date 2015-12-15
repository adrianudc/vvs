package modelo.contenido.impl.emisora;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import modelo.contenido.Contenido;
import modelo.contenido.impl.ContenidoImpl;

import java.util.List;

public class Emisora extends ContenidoImpl {

    /**
     * La lista de reproduccion contiene los diferentes Contenidos de la emisora.
     * A diferencia de las clases Cancion y Anuncio, no contiene a su propia
     * instancia de Emisora.
     */

    /**
     * La duracion en la clase Emisora es la suma de las duraciones de los contenidos
     * de su lista de reproduccion.
     */

    private List<Contenido> listaReproduccion;

    /**
     * Emisora tiene dos constructores. El primero recibe unicamente el titulo de la Emisora
     * y crea una lista vacia.
     * El segundo recibe una lista de Contenidos como parametro.
     * Ambos contructores inicializan duracion a 0. El segundo contructor recorre la lista que
     * se la ha pasado y suma las duraciones de sus Contenidos a la duracion de la Emisora.
     */
    @JsonCreator
    public Emisora(@JsonProperty("titulo") String titulo) {
        super(titulo, 0);
        this.listaReproduccion = Lists.newArrayList();
    }

    @JsonCreator
    public Emisora(@JsonProperty("titulo") String titulo,
            @JsonProperty("listaReproduccion") List<Contenido> listaReproduccion) {
        super(titulo, 0);
        this.listaReproduccion = listaReproduccion;
        for (Contenido contenido : listaReproduccion) {
            setDuracion(contenido.obtenerDuracion() + obtenerDuracion());
        }
    }

    @Override
    public List<Contenido> obtenerListaReproduccion() {
        return listaReproduccion;
    }

    /**
     * Devuelve un lista de los Contenidos en la lista de reproduccion
     * cuyo titulo contiene la cadena.
     */

    @Override
    public List<Contenido> buscar(String subcadena) {
        List<Contenido> resultados = Lists.newArrayList();
        for (Contenido contenido : listaReproduccion) {
            if (contenido.obtenerTitulo().contains(subcadena)) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }

    /**
     * Inserta un nuevo Contenido en la posicion siguiente al Contenido
     * predecesor si este esta presente en la lista de reproduccion, o bien
     * se inserta al final de la lista si no lo esta.
     * Despues de insertar, suma la duracion del nuevo Contenido a la de la Emisora.
     */

    @Override
    public void agregar(Contenido contenido, Contenido predecesor) {
        if (listaReproduccion.contains(predecesor)) {
            listaReproduccion.add(listaReproduccion.indexOf(predecesor) + 1, contenido);
        } else {
            listaReproduccion.add(contenido);
        }
        setDuracion(contenido.obtenerDuracion() + obtenerDuracion());
    }

    /**
     * Elimina el contenido de la lista de reproduccion si esta presente y resta su duracion a la
     * de la Emisora.
     * No hace nada si no esta presente.
     */

    @Override
    public void eliminar(Contenido contenido) {
        if (listaReproduccion.contains(contenido)) {
            listaReproduccion.remove(contenido);
            setDuracion(obtenerDuracion() - contenido.obtenerDuracion());
        }

    }
}
