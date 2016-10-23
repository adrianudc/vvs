package contenido;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import modelo.contenido.Contenido;
import modelo.contenido.impl.emisora.Emisora;
import modelo.contenido.impl.track.Anuncio;
import modelo.contenido.impl.track.Cancion;

import static junit.framework.TestCase.assertEquals;

/**
 * The type Emisora test.
 */
public class EmisoraTest {

    /**
     *
     */
    private Emisora emisora;

    /**
     *
     */
    private Cancion cancion;

    /**
     *
     */
    private Cancion cancion2;

    /**
     *
     */
    private Anuncio anuncio;

    /**
     *
     */
    private List<Contenido> resultadosBuscados;

    /**
     * Inicializa los elementos comunes entre tests.
     */
    @Before
    public void setUP() {
        emisora = new Emisora("EmisoraPrueba");
        cancion = new Cancion("CancionPrueba", 120);
        cancion2 = new Cancion("CancionPrueba2", 180);
        anuncio = new Anuncio();
        resultadosBuscados = Lists.newArrayList();

    }

    /**
     * Codigo de prueba: PR-UN-005
     * Prueba el metodo de agregar contenido, comprobando que la duracion
     * se suma correctamente y que los elementos se insertan en las posiciones
     * adecuadas.
     */
    @Test
    public void testAgregarContenido() {
        assertEquals(0, emisora.obtenerDuracion());
        assertEquals(Lists.newArrayList(), emisora.buscar("Test"));
        emisora.agregar(cancion, null);
        assertEquals(120, emisora.obtenerDuracion());
        assertEquals(cancion, emisora.obtenerListaReproduccion().get(0));
        emisora.agregar(cancion2, cancion);
        assertEquals(300, emisora.obtenerDuracion());
        assertEquals(cancion2, emisora.obtenerListaReproduccion().get(1));
        emisora.agregar(anuncio, cancion);
        assertEquals(305, emisora.obtenerDuracion());
        assertEquals(anuncio, emisora.obtenerListaReproduccion().get(1));
        List<Contenido> constructorlist = Lists.newArrayList();
        constructorlist.add(cancion);
        constructorlist.add(cancion2);
        Emisora emisoraconstructor2 = new Emisora("test", constructorlist);
        assertEquals(300, emisoraconstructor2.obtenerDuracion());
        assertEquals(cancion2, emisoraconstructor2.obtenerListaReproduccion().get(1));
    }

    /**
     * Codigo de prueba: PR-UN-006
     * Prueba el metodo buscar, comprobando que devuelve todos los Contenidos cuyo titulo contiene
     * la cadena que se pasa o lista vacia si ninguno la contiene.
     */
    @Test
    public void testBuscarContenido() {
        emisora.agregar(cancion, null);
        emisora.agregar(cancion2, cancion);
        emisora.agregar(anuncio, cancion);
        resultadosBuscados.add(cancion);
        resultadosBuscados.add(cancion2);
        assertEquals(resultadosBuscados, emisora.buscar("CancionPrueba"));
        assertEquals(Lists.newArrayList(), emisora.buscar("Test"));
    }

    /**
     * Codigo de prueba: PR-UN-007
     * Prueba el metodo eliminar, comprobando que se resta correctamente la duracion
     * de la Emisora y que los elementos se eliminan adecuadamente.
     */
    @Test
    public void testEliminarContenido() {
        emisora.agregar(cancion, null);
        emisora.agregar(cancion2, cancion);
        emisora.eliminar(cancion);
        resultadosBuscados.add(cancion2);
        assertEquals(resultadosBuscados, emisora.obtenerListaReproduccion());
        assertEquals(180, emisora.obtenerDuracion());
        emisora.eliminar(cancion);
        assertEquals(resultadosBuscados, emisora.obtenerListaReproduccion());
        assertEquals(180, emisora.obtenerDuracion());
    }

}
