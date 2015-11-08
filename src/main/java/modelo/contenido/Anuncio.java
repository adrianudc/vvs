package modelo.contenido;

import java.util.List;

import com.google.common.collect.Lists;


/**
 * Segunda implementacion especifica que extiende la clase ContenidoImpl
 **/
public class Anuncio extends ContenidoImpl {

    /**
     * En esta clase, al igual que en la clase Cancion, el atributo listaReproduccion solo contiene
     * a la propia instancia de la clase.
     * Por tanto, los metodos agregar y eliminar siguen sin hacer nada.
     **/

    private List<Contenido> listaReproduccion = Lists.newArrayList();

    /**
     * El titulo de la clase Anuncio es siempre "PUBLICIDAD" y su duracion es siempre 5 segundos, por lo
     * que su constructor no recibe parametros.
     **/
    public Anuncio(){
        super("PUBLICIDAD", 5);
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
