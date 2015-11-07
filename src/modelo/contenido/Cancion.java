package modelo.contenido;


import java.util.Arrays;
import java.util.List;

public class Cancion implements Contenido{

    private String titulo;

    private int duracion;

    public Cancion(String titulo, int duracion){
        this.titulo = titulo;
        this.duracion = duracion;
    }

    @Override
    public String obtenerTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int obtenerDuracion(){
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
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
