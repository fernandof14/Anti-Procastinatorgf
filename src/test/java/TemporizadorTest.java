import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemporizadorTest {
    static Temporizador prueba;
    @BeforeClass
    public static void beforeClass() {
        prueba=new Temporizador();
    }
    @Test
    public void tiempos(){
        prueba.setTiempo(110);
        prueba.calcularTiempo();

        int minutos=prueba.getMinutos();
      int segundos= prueba.getSegundos();
      int minutos_esperado=1;
      int segundos_esperados=50;
        assertEquals(minutos_esperado,minutos);
        assertEquals(segundos_esperados,segundos);

    }
}