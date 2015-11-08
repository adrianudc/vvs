package contenido;


import com.google.common.collect.Lists;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import org.junit.Test;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CancionTest {

    public Cancion cancion = new Cancion("CancionPrueba", 180);

    @Test
    public void TestObtenerListaReproduccion(){
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(cancion);
        assertEquals(listaEsperada, cancion.obtenerListaReproduccion());
    }

    @Test
    public void TestBuscar(){
        List<Contenido> listaEsperada = Lists.newArrayList();
        listaEsperada.add(cancion);
        assertEquals(listaEsperada, cancion.buscar("CancionPrueba"));
        assertEquals(Lists.newArrayList(), cancion.buscar("Test"));
    }
}

