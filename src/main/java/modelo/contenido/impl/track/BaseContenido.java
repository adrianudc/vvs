package modelo.contenido.impl.track;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import modelo.contenido.Contenido;
import modelo.contenido.impl.ContenidoImpl;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 *
 */
public class BaseContenido extends ContenidoImpl {

    /**
     *
     * @param titulo -
     * @param duracion -
     */
    public BaseContenido(@JsonProperty("titulo")final String titulo, @JsonProperty("duracion")final int duracion) {
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
    public List<Contenido> buscar(final String subcadena) {
        return StringUtils.containsIgnoreCase(obtenerTitulo(), subcadena)
                ?
                Lists.<Contenido>newArrayList(this)
                :
                Lists.<Contenido>newArrayList();
    }
}
