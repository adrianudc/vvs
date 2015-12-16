package mocktests.contenido;

import com.google.common.collect.Lists;
import modelo.contenido.Contenido;
import modelo.contenido.impl.track.Anuncio;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class AnuncioMockTest {

    /**
     *
     */
    private Anuncio mockAnuncio = mock(Anuncio.class);

    /**
     * Comprueba que el metodo obtenerListaReproduccion devuelve correctamente
     * la lista conteniendo al propio elemento.
     */

    @Test
    public void testObtenerListaReproduccion() {

        List<Contenido> listaEsperada = new ArrayList<>();
        when(mockAnuncio.obtenerListaReproduccion()).thenReturn(listaEsperada);
        assertEquals(listaEsperada, mockAnuncio.obtenerListaReproduccion());
    }

    /**
     * Comprueba que el metodo buscar devuelve una lista con el propio elemento si
     * la cadena que se le pasa es correcta y una lista vacia cuando no lo es.
     */

    @Test
    public void testBuscar() {
        List<Contenido> listaEsperada = new ArrayList<>();
        listaEsperada.add(mockAnuncio);
        when(mockAnuncio.buscar("PUBLICIDAD")).thenReturn(listaEsperada);
        assertEquals(listaEsperada, mockAnuncio.buscar("PUBLICIDAD"));
        when(mockAnuncio.buscar("Prueba")).thenReturn(new ArrayList<Contenido>());
        assertEquals(Lists.newArrayList(), mockAnuncio.buscar("Prueba"));
    }
}
