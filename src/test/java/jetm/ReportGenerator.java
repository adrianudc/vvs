package jetm;

import java.io.IOException;

import org.junit.Test;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.renderer.SimpleTextRenderer;
import jetm.contenido.AnuncioTest;
import jetm.contenido.CancionTest;
import jetm.contenido.EmisoraTest;
import jetm.servidor.ServidorTest;

/**
 * The type Report generator.
 */
public class ReportGenerator {

    /**
     * The monitor of JETM.
     */
    private EtmMonitor monitor;

    /**
     * Jetm report generation.
     */
    @Test
    public void jetmReportGeneration() {
        // configure measurement framework
        setup();

        try {
            executeServidorTests();
            executeCancionTests();
            executeAnuncioTests();
            executeEmisoraTests();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // visualize results
        monitor.render(new SimpleTextRenderer());

        // shutdown measurement framework
        tearDown();
    }

    /**
     * Initializates JETM.
     */
    private void setup() {
        BasicEtmConfigurator.configure();
        monitor = EtmManager.getEtmMonitor();
        monitor.start();
    }

    /**
     * Cleans JETM.
     */
    private void tearDown() {
        monitor.stop();
    }

    /**
     * Executes emisora tests.
     */
    private void executeEmisoraTests() {
        EmisoraTest emisoraTest = new EmisoraTest();

        emisoraTest.testAgregarContenido();
        emisoraTest.testBuscarContenido();
        emisoraTest.testEliminarContenido();
    }

    /**
     * Executes anuncios tests.
     */
    private void executeAnuncioTests() {
        AnuncioTest anuncioTest = new AnuncioTest();

        anuncioTest.testObtenerListaReproduccion();
        anuncioTest.testBuscar();
    }

    /**
     * Executes cancion tests.
     */
    private void executeCancionTests() {
        CancionTest cancionTest = new CancionTest();

        cancionTest.testBuscar();
        cancionTest.testObtenerListaReproduccion();
    }

    /**
     * Executes servidor tests.
     *
     * @throws IOException -
     */
    private void executeServidorTests() throws IOException {
        ServidorTest servidorTest = new ServidorTest();

        servidorTest.setUp();
        servidorTest.servidorAgregarContenidoTestForbidden();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorAgregarContenidoTestResultOK();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorAgregarYBuscarTestResultOK();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorAltaTestResponseOK();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorBackupAgregarContenidoTestResultOK();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorBajaTestResponseBadRequest();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorBajaTestResponseOK();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorBuscarConCaducidadDeToken();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorTestResponseNotFound();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorEliminarYBuscarTestResultOK();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorBuscarTestPublicidadResult();
        servidorTest.cleanUp();

        servidorTest.setUp();
        servidorTest.servidorBuscarTestSinPublicidad();
        servidorTest.cleanUp();
    }

}
