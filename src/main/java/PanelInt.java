import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;

public class PanelInt extends JPanel implements ActionListener{
    JTextField tareaField;
    JTextField minutos;
    JTextArea log;
    String textoLabelTarea = "Configurando programa.";
    int contadorTareas = 0;
    Timer t;
    String textoTiempo="00:00";
    String tareaActual = ("Nada");
    ArrayList<String> nombreTareas = new ArrayList<>();
    JComboBox listaTareas = new JComboBox(nombreTareas.toArray());

    public PanelInt(){
        setBackground(Color.pink);
        setLayout(null);
        setLabelTitulo();
        setFieldAdd();
        setButtonAdd();
        setBotonesDeTiempo();
        setButtonSalir();
        setMainArea();
        setLabelTiempo();
        setLabelMinutos();
        setFieldMinutos();
        setLabelCurrent();
        setListaTareas();
        setButtonRemove();
        
    }
    
    private void setLabelTitulo(){
        JLabel tag = new JLabel("Anti-Procrastinator");
        tag.setBounds(30,15,280,50);
        tag.setFont(new Font("arial", Font.BOLD,30));
        add(tag);
    }

    private void setFieldAdd(){
        tareaField = new JTextField("Introduzca una tarea...");
        tareaField.setBounds(30, 540, 520, 25);
        tareaField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                tareaField.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        add(tareaField);
    }

    private void setButtonAdd(){
        JButton add = new JButton("Agregar");
        add.setBounds(560, 540, 90, 25);
        add(add);
        ActionListener clock = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tareaField.getText().isEmpty()) {
                    tareaActual=("Tarea n°: " + (contadorTareas+1));
                }else{
                    tareaActual=(tareaField.getText());
                }
                nombreTareas.add(tareaActual);
                listaTareas.addItem(tareaActual);
                log.setText(">"+nombreTareas.get(contadorTareas) + " agregada." +"\n" + log.getText());
                contadorTareas++;
            }
        };
        add.addActionListener(clock);
    }

    private void setButtonRemove(){
        JButton remove = new JButton("Eliminar");
        remove.setBounds(560, 30, 90, 25);
        add(remove);
        ActionListener clock = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = (listaTareas.getSelectedIndex());
                log.setText(">"+listaTareas.getItemAt(index) + " eliminada." + "\n" + log.getText());
                nombreTareas.remove(index);
                listaTareas.removeItemAt(index);
                listaTareas.updateUI();
                contadorTareas--;
            }
        };
        remove.addActionListener(clock);
    }

    int segundosReloj =0;
    int bloqueo =0;
    boolean enDescanso=false;

    void creadorTiempo(int valor){
      
       int tiempo_total=valor;
       int minutoss=tiempo_total/60;
       int segundos=tiempo_total%60;
        
        System.out.println("valor i: "+valor);
       if(segundos<10 && minutoss>10){
           textoTiempo=""+minutoss+":0"+segundos;
       }else if(segundos<10 && minutoss<10){
           textoTiempo="0"+minutoss+":0"+segundos;
       }else if(minutoss<10 && segundos>10){
           textoTiempo="0"+minutoss+":"+segundos;
       }else if(minutoss<10 && segundos==10){
           textoTiempo="0"+minutoss+":"+segundos;
       }
       else{
       textoTiempo=minutoss+":"+segundos;
       }
    //return "Tiempo "+minutoss+":"+segundos;
    }
    
    private void setBotonesDeTiempo(){
        JButton start = new JButton("Iniciar");
        JButton pausa = new JButton("Pausa");
        JButton seguir = new JButton("Seguir");
        JButton reiniciar = new JButton("Reiniciar");
        
        start.setBounds(30, 600, 110, 45);
        seguir.setBounds(150, 600, 110, 45);
        pausa.setBounds(270, 600, 110, 45);
        reiniciar.setBounds(390, 600,110, 45);
        
        add(start);
        add(pausa);
        add(seguir);
        add(reiniciar);
        seguir.setEnabled(false);
        ManejoArchivos sonido=new ManejoArchivos();
        ActionListener click = new ActionListener() {
            
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                if(bloqueo ==0){//si el flag j es 0; toma los minutos del JTextField y los guarda en segundos en el contador
                segundosReloj =12;//60*parseInt(minutos.getText());
                    System.out.println("hola 1");
                   // System.out.println("taraweno? "+60*parseInt(minutos.getText()));
                }
                bloqueo++;
                Object botonPulsado=e.getSource(); //guarda el boton que ActionEvent detecta que se pulso
                
                
             /*   if(tareas.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor agregue elementos a la lista de tareas.");
                }else*/ 
             
             if(botonPulsado==start){
                 
                   t=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //el metodo creadorTiempo; ajusta el Texto cada 1000 ms
                creadorTiempo(segundosReloj);
                System.out.println("i: "+ segundosReloj);
                segundosReloj--;
                System.out.println(textoTiempo);
                textoLabelTarea = (nombreTareas.get(0));
                if(segundosReloj ==-1 && nombreTareas.size()>0 && enDescanso==false){// si los segundos son -1, el timer para. si fuese 0, pararia en 1 segundo

                    sonido.ReproducirSonido();
                    t.stop();
                    segundosReloj =10;
                    t.start();
                    nombreTareas.remove(0);
                    listaTareas.removeItemAt(0);
                    enDescanso=true;
                    textoLabelTarea = ("Descanso");
                
                }else if(segundosReloj ==-1 && nombreTareas.size()>0 && enDescanso==true){
                    segundosReloj =12;//60*parseInt(minutos.getText());
                    sonido.ReproducirSonido();
                    t.stop();
                    t.start();
                    enDescanso=false;
                    //termino descanso
                    
                }else if(segundosReloj ==-1 && nombreTareas.size()==0){
                    sonido.ReproducirSonido();
                    t.stop();
                    segundosReloj =0;
                    start.setEnabled(true);
                    minutos.setEnabled(true);
                    bloqueo =0;
                    textoLabelTarea = ("¡Terminado!");
                }
                else if(/*i==-1 && */60*parseInt(minutos.getText())==0){//si los minutos son 0, el programa no arranca
                    log.setText(">Tiempo invalido." +"\n" + log.getText());
                    t.stop();
                    segundosReloj =0;
                    start.setEnabled(true);
                    minutos.setEnabled(true);
                    bloqueo =0;
                    //tiempo invalido
                }
            }
        });
                    log.setText(">Iniciando..." +"\n" + log.getText());
                    t.start();
                    start.setEnabled(false);
                    minutos.setEnabled(false);
                    
                }else if(botonPulsado==pausa){
                    t.stop();
                    seguir.setEnabled(true);
                    pausa.setEnabled(false);
                    
                }else if(botonPulsado==seguir){
                    t.restart();
                    pausa.setEnabled(true);
                    seguir.setEnabled(false);
                
                }else if(botonPulsado==reiniciar){
                    t.stop();
                    minutos.setEnabled(true);
                    segundosReloj =0;
                    bloqueo =0;
                    start.setEnabled(true);
                    textoTiempo="00:00";
                    
                }
            }
        };
        start.addActionListener(click);
        pausa.addActionListener(click);
        seguir.addActionListener(click);
        reiniciar.addActionListener(click);
        
    }
   
     JLabel time = new JLabel(textoTiempo);
     JLabel tActual = new JLabel(textoLabelTarea);
    
    public void paintComponent(Graphics g){//se encarga de "dibujar" cosas en el panel. En este caso, se usa para
                                          //actualizar el texto con el tiempo
        super.paintComponent(g);
        time.setBounds(280,100, 120, 40 );
        time.setFont(new Font("arial", Font.PLAIN, 40));
        add(time);
        time.setText(textoTiempo);
        tActual.setBounds(230,180, 500, 40 );
        tActual.setFont(new Font("arial", Font.PLAIN, 25));
        add(tActual);
        tActual.setText(textoLabelTarea);

        updateUI();//al parecer no se necesita este metodo
        }

    private void setButtonSalir(){
        JButton exit = new JButton("Salir");
        exit.setBounds(540, 600, 110, 45);
        add(exit);
        ActionListener click = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        exit.addActionListener(click);
    }

    private void setMainArea(){
        log = new JTextArea("Bienvenido.");
        log.setBounds(30, 300, 620,200);
        log.setEditable(false);
        add(log);
    }
    //TIEMPO
    private void setLabelTiempo() {
        JLabel tiempo = new JLabel("Tiempo:");
        tiempo.setBounds(30,240, 120, 40 );
        tiempo.setFont(new Font("arial", Font.PLAIN, 25));
        add(tiempo);
    }

    private void setLabelMinutos() {
        JLabel time = new JLabel("Duracion pomodoros (minutos):");
        time.setBounds(280,240, 200, 40 );
        add(time);
    }

    private void setFieldMinutos(){
        minutos = new JTextField("25");
        minutos.setBounds(465, 252, 25, 20);
        add(minutos);
        
        
    }

    private void setLabelCurrent(){
        JLabel actual = new JLabel("Actividad actual: ");
        actual.setBounds(30,180, 240, 40 );
        actual.setFont(new Font("arial", Font.PLAIN, 25));
        add(actual);
    }

    private void setListaTareas(){
        listaTareas.setBounds(350, 30, 200, 25);
        listaTareas.setEditable(false);
        add(listaTareas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        
    }
}
