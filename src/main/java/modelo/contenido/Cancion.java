package modelo.contenido;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * Primera clase especifica que extiende de ContenidoImpl.
 **/

public class Cancion extends ContenidoImpl{

    /**
     * En esta clase, el atributo listaReproduccion solo contiene a la propia instancia de la clase.
     * Por tanto, los metodos agregar y eliminar siguen sin hacer nada.
     **/

    private List<Contenido> listaReproduccion = Lists.newArrayList();

    @JsonCreator
    public Cancion(@JsonProperty("titulo") String titulo, @JsonProperty("duracion") int duracion) {
        super(titulo, duracion);
        listaReproduccion.add(this);
    }

    @Override
    public List<Contenido> obtenerListaReproduccion(){
        return listaReproduccion;
    }

    /**
     * Devuelve la lista de reproduccion si la cadena esta contenida en el titulo
     * de la cancion. Devuelve una lista vacia en caso contrario.
     **/

    @Override
    public List<Contenido> buscar(String cadena){
        if(cadena.equals(obtenerTitulo())) {
            return listaReproduccion;
        }
        else return Lists.newArrayList();
    }

}
