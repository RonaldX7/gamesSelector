
package udemy.poo.sonido;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


/**
 *
 * @author RONALD
 */
public class Musica implements Runnable {
    private BufferedInputStream buffer = null;
    private Player player = null;
    private String archivo;
    private String archivoDos;

    public Musica(String archivo, String archivoDos) {
        this.archivo = archivo;
        this.archivoDos = archivoDos;
    }

    @Override
    public void run() {
        try {
            FileInputStream archivo = new FileInputStream(
                    this.getClass().getResource("/udemy/poo/musica/" 
                            + this.archivo).toURI().getPath());
            buffer = new BufferedInputStream(archivo);
            player = new Player(buffer);
            player.play();
            
            while (true) {                
                if (player.isComplete()) {
                    archivo.close();
                    
                    try {
                    archivo = new FileInputStream(this.getClass().getResource(
                            "/udemy/poo/musica/" + this.archivoDos).toURI().getPath());
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                    
                    buffer = new BufferedInputStream(archivo);
                    player = new Player(buffer);
                    player.play();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
