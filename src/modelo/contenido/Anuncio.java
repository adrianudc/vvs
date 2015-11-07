package modelo.contenido;

import java.util.Arrays;
import java.util.List;

public class Anuncio implements Contenido{

    private final String titulo = "PUBLICIDAD";

    private final int duracion = 5;

    public Anuncio(){

    };

    @Override
    public String obtenerTitulo(){
        return titulo;
    }

    @Override
    public int obtenerDuracion(){
        return duracion;
    }

    @Override
    public List<Contenido> obtenerListaReproduccion(){
        return Arrays.asList(this);
    }

    @Override
    public List<Contenido> buscar(String cadena){
        return Arrays.asList(this);
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor){};

    @Override
    public void eliminar(Contenido contenido){};

}
