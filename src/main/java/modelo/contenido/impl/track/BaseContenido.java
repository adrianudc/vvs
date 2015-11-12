package modelo.contenido.impl.track;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import modelo.contenido.Contenido;
import modelo.contenido.impl.ContenidoImpl;

public class BaseContenido extends ContenidoImpl {

    public BaseContenido(@JsonProperty("titulo") String titulo, @JsonProperty("duracion") int duracion) {
        super(titulo, duracion);
    }

    @Override
    public List<Contenido> obtenerListaReproduccion() {
        return Lists.<Contenido>newArrayList(this);
    }

    /**
     * Devuelve la lista de reproduccion si la cadena esta contenida en el titulo
     * de la cancion. Devuelve una lista vacia en caso contrario.
     */

    @Override
    public List<Contenido> buscar(String subcadena) {
        return StringUtils.containsIgnoreCase(obtenerTitulo(), subcadena) ?
                Lists.<Contenido>newArrayList(this) :
                Lists.<Contenido>newArrayList();
    }
}
