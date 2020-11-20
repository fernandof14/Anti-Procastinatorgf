import java.util.TimerTask;

public class Temporizador extends TimerTask {

    int temporal = 0;
    int segundos = 0;
    int minutos = 0;
    int tiempo = 5;

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }

    @Override
    public void run() {
        calcularTiempo();

    }

    void calcularTiempo() {
        minutos = tiempo / 60;
        segundos = tiempo % 60;
        System.out.println("Tiempo: " + minutos + ":" + segundos);

        tiempo--;
        if (tiempo == -1) {
            System.out.println("ADIOS");
            System.exit(0);

        }

    }

}
