package modelo.contenido;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anuncio extends ContenidoImpl{

    private List<Contenido> listaReproduccion = Lists.newArrayList();

    public Anuncio(){
        super("PUBLICIDAD", 5);
        listaReproduccion.add(this);
    }

    @Override
    public List<Contenido> obtenerListaReproduccion(){

        return listaReproduccion;
    }

    @Override
    public List<Contenido> buscar(String cadena){
        if(cadena.equals(obtenerTitulo())) {
            return listaReproduccion;
        }
        else return Lists.newArrayList();
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor){};

    @Override
    public void eliminar(Contenido contenido){};
}
