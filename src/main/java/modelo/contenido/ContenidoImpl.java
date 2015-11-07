package modelo.contenido;

import java.util.List;

public class ContenidoImpl implements Contenido {

    private String titulo;

    private int duracion;

    @Override
    public String obtenerTitulo() {
        return titulo;
    }

    @Override
    public int obtenerDuracion() {
        return duracion;
    }

    @Override
    public List<Contenido> obtenerListaReproduccion() {
        return null;
    }

    @Override
    public List<Contenido> buscar(String cadena) {
        return null;
    }

    @Override
    public void agregar(Contenido contenido, Contenido predecesor) {

    }

    @Override
    public void eliminar(Contenido contenido) {

    }
}
