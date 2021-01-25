
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ManejoArchivos {
     
    public void ReproducirSonido(){
       String nombreSonido="alarma3.wav";
        try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
       }
     }
     Image leerImagen(){
   Image imagen=null;
        File miimagen=new File("reloj.png");
        try {
             imagen=ImageIO.read(miimagen);
             
        } catch (IOException ex) {
            System.out.println("No se encuentra la imagen");
        }
    return imagen;
    } 
    
}
