package modelo.contenido;

import java.util.ArrayList;
import java.util.List;

public class Emisora implements Contenido{

    private String titulo;

    private int duracion = 0;

    private ArrayList<Contenido> listaReproduccion;

    public Emisora(String titulo, ArrayList<Contenido> listaReproduccion){
        this.titulo = titulo;
        this.listaReproduccion = listaReproduccion;
        for(Contenido contenido: listaReproduccion){
            duracion += contenido.obtenerDuracion();
        }
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
        int indice = 0;
        boolean anhadido = false;
        for(Contenido contenidoemisora : listaReproduccion) {
            if (contenidoemisora.equals(predecesor)) {
                listaReproduccion.add(indice + 1, contenido);
                duracion += contenido.obtenerDuracion();
                anhadido = true;
                break;
            }
            indice++;
        }
        if(!anhadido){
            listaReproduccion.add(indice, contenido);
            duracion += contenido.obtenerDuracion();
        }
    }

    @Override
    public void eliminar(Contenido contenido){
        for(Contenido contenidoemisora : listaReproduccion){
            if (contenidoemisora.equals(contenido)){
                listaReproduccion.remove(contenidoemisora);
                break;
            }
        }
    }

}
