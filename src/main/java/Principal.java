import java.util.Scanner;
import java.util.Timer;

public class Principal {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        Principal omo=new Principal();
        omo.menu();
    }

    void menu() {
        int opcion=0;
        int segundos = 0;
        int personalizado=0;

        System.out.println("******MENU*******");
        System.out.println("I.1 minuto\nII.2 minutos\nIII.Personalizado");
        opcion = teclado.nextInt();
        if(opcion==3){
            System.out.println("Ingresar minutos: ");
            personalizado=teclado.nextInt();
        }
        switch(opcion){
            case 1:
                segundos=1*60;
                break;
            case 2:
                segundos=2*60;
                break;
            case 3:
                segundos=personalizado*60;
                break;
        }
        partirTimer(segundos);
    }

    void partirTimer(int segundos) {
        Timer timer = new Timer();
        Temporizador tarea = new Temporizador();
        tarea.setTiempo(segundos);
        timer.schedule(tarea, 0, 1000);
        salir();
    }
    void salir(){
        Boton ventana = new Boton();
        ventana.setBounds(500, 250, 250, 250);
        ventana.setVisible(true);
        ventana.setResizable(false);
    }
}
