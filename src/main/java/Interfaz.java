


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class Interfaz extends JFrame {

        public Interfaz() {
            this.setSize(700, 700);
            setTitle("Anti-Procrastinator v1.0");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            PanelInt lamina = new PanelInt();
            add(lamina);
        }


    }
