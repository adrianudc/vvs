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
 **/

public class ContenidoImpl implements Contenido {

    private final String titulo;

    private int duracion; // segundos

    @JsonCreator
    public ContenidoImpl(@JsonProperty("titulo") String titulo, @JsonProperty("duracion") int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    @Override
    public String obtenerTitulo() {
        return titulo;
    }

    @Override
    public int obtenerDuracion() {
        return duracion;
    }

    /**
     * Los metodos obtenerListaReproduccion y buscar devuelven nulo en la implementacion general,
     * dado que esta no contiene el atributo listaReproduccion.
     **/

    @Override
    public List<Contenido> obtenerListaReproduccion() {
        return null;
    }

    @Override
    public List<Contenido> buscar(String subcadena) {
        return null;
    }

    /**
     * Los metodos agregar y eliminar no hacen nada en la implementacion general.
     **/

    @Override
    public void agregar(Contenido contenido, Contenido predecesor) {
    }

    @Override
    public void eliminar(Contenido contenido) {
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(Object o) {
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
