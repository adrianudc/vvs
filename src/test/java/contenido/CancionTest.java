package contenido;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import modelo.contenido.Contenido;
import modelo.contenido.impl.track.Cancion;

import static junit.framework.TestCase.assertEquals;

/**
 * The type Cancion test.
 */
public class CancionTest {

    /**
     *
     */
    private Cancion cancion = new Cancion("CancionPrueba", 180);

    /**
     * Codigo de prueba: PR-UN-003
     * Comprueba que el metodo obtenerListaReproduccion devuelve correctamente
     * la lista conteniendo al propio elemento.
     */
    @Test
    public void testObtenerListaReproduccion() {
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(cancion);
        assertEquals(listaEsperada, cancion.obtenerListaReproduccion());
    }

    /**
     * Codigo de prueba: PR-UN-004
     * Comprueba que el metodo buscar devuelve una lista con el propio elemento si
     * la cadena que se le pasa es correcta y una lista vacia cuando no lo es.
     */
    @Test
    public void testBuscar() {
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(cancion);
        assertEquals(listaEsperada, cancion.buscar("CancionPrueba"));
        assertEquals(Lists.newArrayList(), cancion.buscar("Test"));
    }
}

