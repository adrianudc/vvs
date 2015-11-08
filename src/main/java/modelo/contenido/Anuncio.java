package modelo.contenido;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anuncio implements Contenido{

    private final String titulo = "PUBLICIDAD";

    private final int duracion = 5;

    private List<Contenido> listaReproduccion = Lists.newArrayList();

    public Anuncio(){
        listaReproduccion.add(this);
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
    public List<Contenido> obtenerListaReproduccion(){

        return listaReproduccion;
    }

    @Override
    public List<Contenido> buscar(String cadena){
        if(cadena.equals(titulo)) {
            return listaReproduccion;
        }
        else return new ArrayList<Contenido>();
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor){};

    @Override
    public void eliminar(Contenido contenido){};
}
