package contenido;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.Before;
import java.util.List;

import modelo.contenido.Cancion;
import modelo.contenido.Anuncio;
import modelo.contenido.Emisora;
import modelo.contenido.Contenido;
import static junit.framework.TestCase.assertEquals;

public class EmisoraTest {

        Emisora emisora;
        Cancion cancion;
        Cancion cancion2;
        Anuncio anuncio;
        List<Contenido> resultadosBuscados;

        @Before
        public void setUP(){
            emisora = new Emisora("EmisoraPrueba");
            cancion = new Cancion("CancionPrueba", 120);
            cancion2 = new Cancion("CancionPrueba2", 180);
            anuncio = new Anuncio();
            resultadosBuscados = Lists.newArrayList();
        }


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
