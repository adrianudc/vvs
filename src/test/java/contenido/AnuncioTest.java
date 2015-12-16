package contenido;

import com.google.common.collect.Lists;
import modelo.contenido.Contenido;
import modelo.contenido.impl.track.Anuncio;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 *
 */
public class AnuncioTest {

    /**
     *
     */
    private Anuncio anuncio = new Anuncio();

    /**
     * Comprueba que el metodo obtenerListaReproduccion devuelve correctamente
     * la lista conteniendo al propio elemento.
     */

    @Test
    public void testObtenerListaReproduccion() {

        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(anuncio);
        assertEquals(listaEsperada, anuncio.obtenerListaReproduccion());
    }

    /**
     * Comprueba que el metodo buscar devuelve una lista con el propio elemento si
     * la cadena que se le pasa es correcta y una lista vacia cuando no lo es.
     */

    @Test
    public void testBuscar() {
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(anuncio);
        assertEquals(listaEsperada, anuncio.buscar("PUBLICIDAD"));
        assertEquals(Lists.newArrayList(), anuncio.buscar("Prueba"));
    }
}
