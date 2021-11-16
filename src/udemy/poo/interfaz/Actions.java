
package udemy.poo.interfaz;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author RONALD
 */
public interface Actions {
    
    public void pintar(Graphics2D g);
    
    public void teclado(KeyEvent e);
    
    public void raton(MouseEvent e);
    
    public void calculo();
    
    
}
