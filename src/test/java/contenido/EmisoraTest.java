package contenido;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import modelo.contenido.Anuncio;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Emisora;

import static junit.framework.TestCase.assertEquals;

public class EmisoraTest {

        Emisora emisora;
        Cancion cancion;
        Cancion cancion2;
        Anuncio anuncio;
        List<Contenido> resultadosBuscados;

        /**
         * Inicializa los elementos comunes entre tests.
         **/

        @Before
        public void setUP(){
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
         **/

        @Test
        public void TestAgregarContenido(){
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
        }

        /**
         * Prueba el metodo buscar, comprobando que devuelve todos los Contenidos cuyo titulo contiene
         * la cadena que se pasa o lista vacia si ninguno la contiene.
         **/

        @Test
        public void TestBuscarContenido(){
            emisora.agregar(cancion, null);
            emisora.agregar(cancion2, cancion);
            emisora.agregar(anuncio, cancion);
            resultadosBuscados.add(cancion);
            resultadosBuscados.add(cancion2);
            assertEquals(resultadosBuscados, emisora.buscar("CancionPrueba"));
            assertEquals(Lists.newArrayList(), emisora.buscar("Test"));
        }

        /**
         * Prueba el metodo eliminar, comprobando que se resta correctamente la duracion
         * de la Emisora y que los elementos se eliminan adecuadamente.
         **/

        @Test
        public void TestEliminarContenido(){
            emisora.agregar(cancion, null);
            emisora.agregar(cancion2, cancion);
            emisora.eliminar(cancion);
            resultadosBuscados.add(cancion2);
            assertEquals(resultadosBuscados, emisora.obtenerListaReproduccion());
            assertEquals(180, emisora.obtenerDuracion());
        }

}
