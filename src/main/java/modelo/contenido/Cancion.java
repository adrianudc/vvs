package modelo.contenido;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Cancion extends ContenidoImpl{

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

    @Override
    public List<Contenido> buscar(String cadena){
        if(cadena.equals(obtenerTitulo())) {
            return listaReproduccion;
        }
        else return new ArrayList<Contenido>();
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor){};

    @Override
    public void eliminar(Contenido contenido){};

}
