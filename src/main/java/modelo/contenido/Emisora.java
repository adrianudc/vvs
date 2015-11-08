package modelo.contenido;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Emisora extends ContenidoImpl{

    private List<Contenido> listaReproduccion;

    @JsonCreator
    public Emisora(@JsonProperty("titulo") String titulo){
        super(titulo, 0);
        this.listaReproduccion = Lists.newArrayList();
    }

    @JsonCreator
    public Emisora(@JsonProperty("titulo") String titulo, @JsonProperty("listaReproduccion") List<Contenido> listaReproduccion){
        super(titulo, 0);
        this.listaReproduccion = listaReproduccion;
        for(Contenido contenido: listaReproduccion){
            setDuracion(contenido.obtenerDuracion() + obtenerDuracion());
        }
    }

    @Override
    public List<Contenido> obtenerListaReproduccion(){
        return listaReproduccion;
    }

    @Override
    public List<Contenido> buscar(String cadena){
        List<Contenido> resultados = new ArrayList<Contenido>();
        for (Contenido contenido: listaReproduccion ){
            if(contenido.obtenerTitulo().contains(cadena)){
                resultados.add(contenido);
            }
        }
        return resultados;
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor){
        if(listaReproduccion.contains(predecesor)) {
            listaReproduccion.add(listaReproduccion.indexOf(predecesor) + 1, contenido);
        }
        else listaReproduccion.add(contenido);
        setDuracion(contenido.obtenerDuracion() + obtenerDuracion());
    }

    @Override
    public void eliminar(Contenido contenido){
        if(listaReproduccion.contains(contenido)) {
            listaReproduccion.remove(contenido);
            setDuracion(obtenerDuracion() - contenido.obtenerDuracion());
        }

    }

}
