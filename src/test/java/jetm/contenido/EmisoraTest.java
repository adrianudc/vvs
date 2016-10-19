package jetm.contenido;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
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
     * The Emisora.
     */
    Emisora emisora;

    /**
     * The Cancion.
     */
    Cancion cancion;

    /**
     * The Cancion 2.
     */
    Cancion cancion2;

    /**
     * The Anuncio.
     */
    Anuncio anuncio;

    /**
     * The Resultados buscados.
     */
    List<Contenido> resultadosBuscados;

    /**
     * JETM Monitor to check performance.
     */
    private final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

    /**
     * Inicializa los elementos comunes entre tests.
     */
    @Before
    public void setUp() {
        emisora = new Emisora("EmisoraPrueba");
        cancion = new Cancion("CancionPrueba", 120);
        cancion2 = new Cancion("CancionPrueba2", 180);
        anuncio = new Anuncio();
        resultadosBuscados = Lists.newArrayList();
    }

    /**
     * Prueba el metodo de agregar contenido, comprobando que la duracion
     * se suma correctamente y que los elementos se insertan en las posiciones
     * adecuadas.
     */
    @Test
    public void testAgregarContenido() {
        EtmPoint point = etmMonitor
                .createPoint("EmisoraTest:testAgregarContenido");

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

        point.collect();
    }

    /**
     * Prueba el metodo buscar, comprobando que devuelve todos los Contenidos cuyo titulo contiene
     * la cadena que se pasa o lista vacia si ninguno la contiene.
     */
    @Test
    public void testBuscarContenido() {
        EtmPoint point = etmMonitor
                .createPoint("EmisoraTest:testBuscarContenido");

        emisora.agregar(cancion, null);
        emisora.agregar(cancion2, cancion);
        emisora.agregar(anuncio, cancion);
        resultadosBuscados.add(cancion);
        resultadosBuscados.add(cancion2);

        point.collect();

        assertEquals(resultadosBuscados, emisora.buscar("CancionPrueba"));
        assertEquals(Lists.newArrayList(), emisora.buscar("Test"));
    }

    /**
     * Prueba el metodo eliminar, comprobando que se resta correctamente la duracion
     * de la Emisora y que los elementos se eliminan adecuadamente.
     */
    @Test
    public void testEliminarContenido() {
        EtmPoint point = etmMonitor
                .createPoint("EmisoraTest:testEliminarContenido");

        emisora.agregar(cancion, null);
        emisora.agregar(cancion2, cancion);
        emisora.eliminar(cancion);
        resultadosBuscados.add(cancion2);

        point.collect();

        assertEquals(resultadosBuscados, emisora.obtenerListaReproduccion());
        assertEquals(180, emisora.obtenerDuracion());
    }

}
