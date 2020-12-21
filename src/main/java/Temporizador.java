import javax.swing.*;
import java.util.TimerTask;
import javax.swing.JOptionPane;
public class Temporizador extends TimerTask {

    int temporal = 0;
    int segundos = 0;
    int minutos = 0;
    int tiempo = 5;



    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }



    @Override
    public void run() {
        System.out.println(calcularTiempo());
    }

    String calcularTiempo() {
        minutos = tiempo / 60;
        segundos = tiempo % 60;
        tiempo--;
        if (tiempo == -1) {
            System.out.println("RING!!!!!");
            System.exit(0);

        }
        return "Tiempo: " + minutos + ":" + segundos;
    }

    public int getSegundos() {
        return segundos;
    }

    public int getMinutos() {
        return minutos;
    }


}
