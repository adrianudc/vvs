package modelo.contenido;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContenidoImpl implements Contenido {

    private final String titulo;

    private final int duracion;

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

    @Override
    public List<Contenido> obtenerListaReproduccion() {
        return null;
    }

    @Override
    public List<Contenido> buscar(String cadena) {
        return null;
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor) {

    }

    @Override
    public void eliminar(Contenido contenido) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
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
