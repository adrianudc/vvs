package jetm.contenido;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import modelo.contenido.Contenido;
import modelo.contenido.impl.track.Anuncio;

import static junit.framework.TestCase.assertEquals;

/**
 * The type Anuncio test.
 */
public class AnuncioTest {

    /**
     * The Anuncio.
     */
    private Anuncio anuncio = new Anuncio();

    /**
     * JETM Monitor to check performance.
     */
    private final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

    /**
     * Comprueba que el metodo obtenerListaReproduccion devuelve correctamente
     * la lista conteniendo al propio elemento.
     */
    @Test
    public void testObtenerListaReproduccion() {
        EtmPoint point = etmMonitor
                .createPoint("AnuncioTest:testObtenerListaReproduccion");

        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(anuncio);

        point.collect();

        assertEquals(listaEsperada, anuncio.obtenerListaReproduccion());
    }

    /**
     * Comprueba que el metodo buscar devuelve una lista con el propio elemento si
     * la cadena que se le pasa es correcta y una lista vacia cuando no lo es.
     */
    @Test
    public void testBuscar() {
        EtmPoint point = etmMonitor
                .createPoint("AnuncioTest:testBuscar");

        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(anuncio);

        point.collect();

        assertEquals(listaEsperada, anuncio.buscar("PUBLICIDAD"));
        assertEquals(Lists.newArrayList(), anuncio.buscar("Prueba"));
    }
}
