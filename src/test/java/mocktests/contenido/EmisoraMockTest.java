package mocktests.contenido;

import com.google.common.collect.Lists;
import modelo.contenido.Contenido;
import modelo.contenido.impl.emisora.Emisora;
import modelo.contenido.impl.track.Anuncio;
import modelo.contenido.impl.track.Cancion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Emisora mock test.
 */
public class EmisoraMockTest {

    /**
     *
     */
    private Emisora mockEmisora;

    /**
     *
     */
    private Cancion mockCancion;

    /**
     *
     */
    private Cancion mockCancion2;

    /**
     *
     */
    private Anuncio mockAnuncio;

    /**
     *
     */
    private List<Contenido> resultadosBuscados;

    /**
     * Inicializa los elementos comunes entre tests.
     */
    @Before
    public void setUP() {
        mockEmisora = mock(Emisora.class);
        mockCancion = mock(Cancion.class);
        mockCancion2 = mock(Cancion.class);
        mockAnuncio = mock(Anuncio.class);
        resultadosBuscados = new ArrayList<>();

    }

    /**
     * Prueba el metodo de agregar contenido, comprobando que la duracion
     * se suma correctamente y que los elementos se insertan en las posiciones
     * adecuadas.
     */
    @Test
    public void testAgregarContenido() {
        when(mockEmisora.obtenerListaReproduccion()).thenReturn(new ArrayList<Contenido>());
        assertEquals(Lists.newArrayList(), mockEmisora.obtenerListaReproduccion());
        when(mockEmisora.obtenerDuracion()).thenReturn(0);
        assertEquals(0, mockEmisora.obtenerDuracion());
        mockEmisora.agregar(mockCancion, null);
        when(mockEmisora.obtenerDuracion()).thenReturn(120);
        assertEquals(120, mockEmisora.obtenerDuracion());
        mockEmisora.agregar(mockCancion2, mockCancion);
        when(mockEmisora.obtenerDuracion()).thenReturn(300);
        assertEquals(300, mockEmisora.obtenerDuracion());
    }

    /**
     * Prueba el metodo buscar, comprobando que devuelve todos los Contenidos cuyo titulo contiene
     * la cadena que se pasa o lista vacia si ninguno la contiene.
     */
    @Test
    public void testBuscarContenido() {
        mockEmisora.agregar(mockCancion, null);
        mockEmisora.agregar(mockCancion2, mockCancion);
        mockEmisora.agregar(mockAnuncio, mockCancion);
        resultadosBuscados.add(mockCancion);
        resultadosBuscados.add(mockCancion2);
        when(mockEmisora.buscar("CancionPrueba")).thenReturn(resultadosBuscados);
        assertEquals(resultadosBuscados, mockEmisora.buscar("CancionPrueba"));
        when(mockEmisora.buscar("Test")).thenReturn(new ArrayList<Contenido>());
        assertEquals(Lists.newArrayList(), mockEmisora.buscar("Test"));
    }

    /**
     * Prueba el metodo eliminar, comprobando que se resta correctamente la duracion
     * de la Emisora y que los elementos se eliminan adecuadamente.
     */
    @Test
    public void testEliminarContenido() {
        mockEmisora.agregar(mockCancion, null);
        mockEmisora.agregar(mockCancion2, mockCancion);
        mockEmisora.eliminar(mockCancion);
        resultadosBuscados.add(mockCancion2);
        when(mockEmisora.obtenerListaReproduccion()).thenReturn(resultadosBuscados);
        assertEquals(resultadosBuscados, mockEmisora.obtenerListaReproduccion());
        when(mockEmisora.obtenerDuracion()).thenReturn(180);
        assertEquals(180, mockEmisora.obtenerDuracion());
    }

}
