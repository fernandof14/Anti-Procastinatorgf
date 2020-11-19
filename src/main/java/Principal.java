import java.util.Timer;

public class Principal {
    public static void main(String[] args) {
        Timer tiempo = new Timer();
        Temporizador tarea = new Temporizador();
        System.out.println("Ingresar tiempo a estudiar:\n1. 25 minutos\n2. 10 segundos");
        tarea.setTiempo(360);
        tiempo.schedule(tarea, 0,1000);
        //tiempo.cancel();


    }
}
