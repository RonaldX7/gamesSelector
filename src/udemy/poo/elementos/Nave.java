
package udemy.poo.elementos;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import udemy.poo.interfaz.Actions;
import udemy.poo.modelo.Burbuja;

/**
 *
 * @author RONALD
 */
public class Nave implements Actions {
    private int x = 0;
    private int y = 0;
    private int puntaX = 0;
    private int puntaY = 0;
    private int desplazamiento = 8;
    private Image imagen = null;
    private MediaTracker tracker;
    private Component componente;
    private static Nave objetoUnico;
    private boolean arriba = false;
    private boolean abajo = false;
    private boolean izquierda = false;
    private boolean derecha = false;
    
    public static Nave getNave() {
        if (objetoUnico == null) {
            objetoUnico = new Nave();
        }
        return objetoUnico;
    }

    private Nave() {
    }

    public void configurar(Component componente, String archivo){
        this.componente = componente;
        tracker = new MediaTracker(componente);
        Toolkit herr = Toolkit.getDefaultToolkit();
        imagen = herr.getImage(getClass().getResource("/udemy/poo/recursos/" + archivo));
        tracker.addImage(imagen, 1);
        
        try {
            tracker.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        x = this.componente.getWidth() / 2;
        y = this.componente.getHeight() - 50;
        
        puntaX = x + (imagen.getWidth(componente) / 2);
        puntaY = y;
    }
    
    @Override
    public void pintar(Graphics2D g) {
        g.drawImage(imagen, x, y, null);
        g.drawRect(x, y, imagen.getWidth(componente), imagen.getHeight(componente));
        g.drawOval(puntaX, puntaY, 4, 4);
    }

    @Override
    public void teclado(KeyEvent e) {
        int codigoTecla = e.getKeyCode();
        
        switch(codigoTecla) {
            case KeyEvent.VK_W:
                this.arriba = true;
                this.abajo = false;
                this.izquierda = false;
                this.derecha = false;
                break;
            case KeyEvent.VK_S:
                this.arriba = false;
                this.abajo = true;
                this.izquierda = false;
                this.derecha = false;
                break;
            case KeyEvent.VK_A:
                this.arriba = false;
                this.abajo = false;
                this.izquierda = true;
                this.derecha = false;
                break;
            case KeyEvent.VK_D:
                this.arriba = false;
                this.abajo = false;
                this.izquierda = false;
                this.derecha = true;
                break;
        }  
    }

    @Override
    public void raton(MouseEvent e) {
        
    }

    @Override
    public void calculo() {
        if (arriba) {
            if (y > 0) {
                y -= desplazamiento;
            }
        }
        if (abajo) {
            if (y < this.componente.getHeight() - 60) {
                y += desplazamiento;
            }
        }
        if (izquierda) {
            if (x > 0) {
                x -= desplazamiento;
            }
        }
        if (derecha) {
            if (x < this.componente.getWidth() - 60) {
                x += desplazamiento;
            }
        }
        puntaX = x + (imagen.getWidth(componente) / 2);
        puntaY = y;
        
        // Colisiones
        Burbujas burbujas = Burbujas.getBurbujas();
        
        for (Burbuja elemento: burbujas.getElementos()) {
            int xUnoxDos = (int) Math.pow((puntaX - elemento.getCx()), 2);
            int yUnoyDos = (int) Math.pow((puntaY - elemento.getCy()), 2);
            int distancia = (int) Math.sqrt(xUnoxDos + yUnoyDos);
            
            if (distancia <= 20) {
                elemento.setEliminar(true);
                elemento.setWhoDeleted("nave");
                //Efetos de Música
                
            }
        }
    }
    
}
