package modelo.contenido.impl;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import modelo.contenido.Contenido;

/**
 * Clase que implementa la interfaz Contenido, contiene los elementos comunes al resto de implementaciones,
 * como los atributos titulo y duracion, asi como los metodos obtenerTitulo y obtenerDuracion.
 */

public class ContenidoImpl implements Contenido {

    /**
     *
     */
    private final String titulo;

    /**
     *
     */
    private int duracion = 0; // segundos

    /**
     *
     * @param titulo -
     * @param duracion -
     */
    @JsonCreator
    public ContenidoImpl(@JsonProperty("titulo")final String titulo, @JsonProperty("duracion")final int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    /**
     *
     * @return -
     */
    @Override
    public String obtenerTitulo() {
        return titulo;
    }

    /**
     *
     * @return-
     */

    @Override
    public int obtenerDuracion() {
        return duracion;
    }

    /**
     * Los metodos obtenerListaReproduccion y buscar devuelven nulo en la implementacion general,
     * dado que esta no contiene el atributo listaReproduccion.
     */

    @Override
    public List<Contenido> obtenerListaReproduccion() {
        return null;
    }

    /**
     *
     * @param subcadena contenida en el el titulo de los contenidos de la lista
     * @return -
     */

    @Override
    public List<Contenido> buscar(final String subcadena) {
        return null;
    }

    /**
     * Los metodos agregar y eliminar no hacen nada en la implementacion general.
     */


    /**
     * @param contenido contenido a agregar
     * @param predecesor despues del cual se inserta el nuevo contenido
     */
    @Override
    public void agregar(final Contenido contenido, final Contenido predecesor) {
    }

    /**
     *
     * @param contenido a eliminar
     */
    @Override
    public void eliminar(final Contenido contenido) {
    }

    /**
     *
     * @param duracion -
     */
    public void setDuracion(final int duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !(o instanceof ContenidoImpl)) {
            return false;
        }

        ContenidoImpl contenido = (ContenidoImpl) o;

        return new EqualsBuilder()
                .append(duracion, contenido.duracion)
                .append(titulo, contenido.titulo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(titulo)
                .append(duracion)
                .toHashCode();
    }
}
