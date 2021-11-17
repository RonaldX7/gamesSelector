package udemy.poo.elementos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import udemy.poo.interfaz.Actions;
import udemy.poo.modelo.Burbuja;

/**
 *
 * @author RONALD
 */
public class Burbujas implements Actions {

    private ArrayList<Burbuja> elementos = new ArrayList<>();
    private Component componente;
    private int desplazamiento = 2;
    private int diametro = 40;
    private int radio = 20;
    private Image imagen = null;
    private MediaTracker tracker;
    private static Burbujas objetoUnico;

    public static Burbujas getBurbujas() {
        if (objetoUnico == null) {
            objetoUnico = new Burbujas();
        }

        return objetoUnico;
    }

    private Burbujas() {
    }

    public ArrayList<Burbuja> getElementos() {
        return elementos;
    }

    public void configurar(Component componente, String archivo) {
        this.componente = componente;
        tracker = new MediaTracker(componente);
        Random rdUno = new Random();

        int x = 0;
        int y = 0;

        for (int i = 0; i < 10; i++) {
            y = rdUno.nextInt(250);
            elementos.add(new Burbuja(x, y, x + radio, y + radio, diametro));
            x += diametro;
        }

        Toolkit herr = Toolkit.getDefaultToolkit();
        imagen = herr.getImage(getClass().getResource("/udemy/poo/recursos/" + archivo));
        tracker.addImage(imagen, 1);
        try {
            tracker.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void pintar(Graphics2D g) {
        for (int i = 0; i < elementos.size(); i++) {
            g.drawOval(elementos.get(i).getXx(), elementos.get(i).getYy(),
                    elementos.get(i).getRadio(), elementos.get(i).getRadio());
            // Imagen
            g.drawImage(imagen, elementos.get(i).getXx(), elementos.get(i).getYy(), 
                    elementos.get(i).getRadio(), elementos.get(i).getRadio(), null);

            if (i == 0) {
                g.setColor(new Color(255, 255, 255));
                g.drawOval(elementos.get(i).getXx(), elementos.get(i).getYy(),
                        elementos.get(i).getRadio(), elementos.get(i).getRadio());
                g.drawLine(elementos.get(i).getCx(), elementos.get(i).getCy(),
                        elementos.get(elementos.size() - 1).getCx(),
                        elementos.get(elementos.size() - 1).getCy());
                g.setColor(new Color(0, 0, 0));
            } else if (i < elementos.size() - 1) {
                g.drawLine(elementos.get(i).getCx(), elementos.get(i).getCy(),
                        elementos.get(i + 1).getCx(),
                        elementos.get(i + 1).getCy());
            }
        }
    }

    @Override
    public void teclado(KeyEvent e) {

    }

    @Override
    public void raton(MouseEvent e) {

    }

    @Override
    public void calculo() {
        for (Burbuja elemento : elementos) {
            if (elemento.isAbajo()) {
                if (elemento.getYy() >= this.componente.getHeight() - diametro) {
                    elemento.setArriba(true);
                    elemento.setAbajo(false);
                } else {
                    elemento.setYy(elemento.getYy() + desplazamiento);
                    elemento.setCy(elemento.getYy() + radio);
                }
            }

            if (elemento.isArriba()) {
                if (elemento.getYy() <= 0) {
                    elemento.setAbajo(true);
                    elemento.setArriba(false);
                } else {
                    elemento.setYy(elemento.getYy() - desplazamiento);
                    elemento.setCy(elemento.getYy() + radio);
                }
            }

            if (elemento.isIzquierda()) {
                if (elemento.getXx() <= 0) {
                    elemento.setDerecha(true);
                    elemento.setIzquierda(false);
                } else {
                    elemento.setYy(elemento.getXx() - desplazamiento);
                    elemento.setCy(elemento.getXx() + radio);
                }
            }

            if (elemento.isDerecha()) {
                if (elemento.getXx() >= this.componente.getWidth() - diametro) {
                    elemento.setIzquierda(true);
                    elemento.setDerecha(false);
                } else {
                    elemento.setYy(elemento.getXx() + desplazamiento);
                    elemento.setCy(elemento.getXx() + radio);
                }
            }
        }

            for (int i = 0; i < elementos.size(); i++) {
                Burbuja actual = elementos.get(i);

                for (int j = 0; j < elementos.size(); j++) {
                    Burbuja objetivo = elementos.get(j);

                    int xUnoxDos = (int) Math.pow((actual.getCx() - actual.getCx()), 2);
                    int yUnoyDos = (int) Math.pow((actual.getCy() - actual.getCy()), 2);
                    int distancia = (int) Math.sqrt(xUnoxDos + yUnoyDos);

                    if (distancia <= diametro) {
                        if (objetivo.isAbajo()) {
                            objetivo.setArriba(true);
                            objetivo.setAbajo(false);
                        } else if (objetivo.isArriba()) {
                            objetivo.setArriba(false);
                            objetivo.setAbajo(true);
                        } else if (objetivo.isDerecha()) {
                            objetivo.setIzquierda(true);
                            objetivo.setDerecha(false);
                        } else if (objetivo.isIzquierda()) {
                            objetivo.setDerecha(true);
                            objetivo.setIzquierda(false);
                        }
                    }

                    if (actual.isAbajo()) {
                        actual.setArriba(true);
                        actual.setAbajo(false);
                    } else if (actual.isArriba()) {
                        actual.setArriba(false);
                        actual.setAbajo(true);
                    } else if (actual.isDerecha()) {
                        actual.setIzquierda(true);
                        actual.setDerecha(false);
                    } else if (actual.isIzquierda()) {
                        actual.setDerecha(true);
                        actual.setIzquierda(false);
                    }
                    
                    if (i == 0) {
                        objetivo.setEliminar(true);
                        objetivo.setWhoDeleted("orbe");
                        // Efecto de sonido
                    }
                }
            }
        }
    }
