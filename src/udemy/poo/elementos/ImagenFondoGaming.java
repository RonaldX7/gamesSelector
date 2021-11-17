
package udemy.poo.elementos;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import udemy.poo.interfaz.Actions;

/**
 *
 * @author RONALD
 */
public class ImagenFondoGaming implements Actions {
    private Image imagen = null;
    private Component componente;
    private int x = -20;
    private static ImagenFondoGaming objetoUnico;
    private MediaTracker tracker;
    
    public static ImagenFondoGaming imagenFondo() {
        if (objetoUnico == null) {
            objetoUnico = new ImagenFondoGaming();
        }
        return objetoUnico;
    }

    private ImagenFondoGaming() {
    }
    
    public void configuracion(Component componente, String archivo) {
        this.componente = componente;
        tracker = new MediaTracker(componente);
        Toolkit herram = Toolkit.getDefaultToolkit();
        imagen = herram.getImage(getClass().getResource("/udemy/poo/recursos/" + archivo));
        tracker.addImage(imagen, 1);
        
        
        //Espera a que todas las imagenes sean cargadas
        try {
            tracker.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void pintar(Graphics2D g) {
        g.drawImage(imagen, 0, 0, this.componente.getWidth(), this.componente.getHeight(), null);
    }

    @Override
    public void teclado(KeyEvent e) {
        
    }

    @Override
    public void raton(MouseEvent e) {
        
    }

    @Override
    public void calculo() {
        /*if (x > this.componente.getWidth() + 20) {
            x = -20;
        } else {
            x += 4;
        }*/
    }
    
}
