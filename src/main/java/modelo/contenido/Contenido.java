package modelo.contenido;

import java.util.List;

public interface Contenido {

    String obtenerTitulo();

    int obtenerDuracion();

    List<Contenido> obtenerListaReproduccion();

    List<Contenido> buscar(String cadena);

    void agregar(Contenido contenido, Contenido predecesor);

    void eliminar(Contenido contenido);

}
