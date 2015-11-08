package contenido;

import com.google.common.collect.Lists;
import modelo.contenido.Anuncio;
import modelo.contenido.Contenido;
import org.junit.Test;
import java.util.List;
import static junit.framework.TestCase.assertEquals;


public class AnuncioTest {

    Anuncio anuncio = new Anuncio();

    @Test
    public void TestObtenerListaReproduccion(){

        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(anuncio);
        assertEquals(listaEsperada, anuncio.obtenerListaReproduccion());
    }

    @Test
    public void TestBuscar(){
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(anuncio);
        assertEquals(listaEsperada, anuncio.buscar("PUBLICIDAD"));
        assertEquals(Lists.newArrayList(), anuncio.buscar("Prueba"));
    }
}
