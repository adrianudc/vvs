package modelo.servidor;

import java.util.List;

import modelo.contenido.Contenido;

public interface Servidor {

    String obtenerNombre();

    String alta();

    void baja(String token);

    void agregar(Contenido contenido, String token);

    void eliminar(Contenido contenido, String token);

    List<Contenido> buscar(String subcadena, String token);
}
