package mocktests.contenido;

import com.google.common.collect.Lists;
import modelo.contenido.Contenido;
import modelo.contenido.impl.track.Cancion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Cancion mock test.
 */
public class CancionMockTest {

    /**
     *
     */
    private Cancion mockCancion = mock(Cancion.class);

    /**
     * Comprueba que el metodo obtenerListaReproduccion devuelve correctamente
     * la lista conteniendo al propio elemento.
     */
    @Test
    public void testObtenerListaReproduccion() {
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(mockCancion);
        when(mockCancion.obtenerListaReproduccion()).thenReturn(listaEsperada);
        assertEquals(listaEsperada, mockCancion.obtenerListaReproduccion());
    }

    /**
     * Comprueba que el metodo buscar devuelve una lista con el propio elemento si
     * la cadena que se le pasa es correcta y una lista vacia cuando no lo es.
     */
    @Test
    public void testBuscar() {
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(mockCancion);
        when(mockCancion.buscar("CancionPrueba")).thenReturn(listaEsperada);
        assertEquals(listaEsperada, mockCancion.buscar("CancionPrueba"));
        when(mockCancion.buscar("Test")).thenReturn(new ArrayList<Contenido>());
        assertEquals(Lists.newArrayList(), mockCancion.buscar("Test"));
    }
}

