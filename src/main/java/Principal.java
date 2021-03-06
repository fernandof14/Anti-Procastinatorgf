import java.util.Scanner;
import java.util.Timer;

public class Principal {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        Principal reloj=new Principal();
        reloj.menu();
    }

    void menu() {
        int opcion=0;
        int segundos = 0;
        int personalizado=0;
        String actividad="nulo?";

        System.out.println("******MENU*******");


            System.out.println("I.1 Minuto\nII.2 Minutos\nIII.Personalizado\nIV.Salir");
            opcion = teclado.nextInt();
            teclado.skip("\n");// estupido scanner
            if (opcion == 3) {
                System.out.println("Ingresar minutos: ");
                personalizado = teclado.nextInt();
            }
            switch (opcion) {
                case 1:
                    segundos = 1 * 60;
                    break;
                case 2:
                    segundos = 2 * 60;
                    break;
                case 3:
                    segundos = personalizado * 60;
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
            }
            System.out.println("Ingresar nombre de la actividad: ");
            actividad = teclado.nextLine();
            partirTimer(segundos, actividad);

    }

    void partirTimer(int segundos, String actividad) {
        Timer timer = new Timer();
        Temporizador tarea = new Temporizador();
        tarea.setTiempo(segundos);
        timer.schedule(tarea, 0, 1000);
        salir(actividad);
    }
    void salir(String actividad){
        Boton ventana = new Boton(actividad);
        ventana.setBounds(500, 250, 250, 250);
        ventana.setVisible(true);
        ventana.setResizable(false);
    }
}
