package udemy.poo.game;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import udemy.poo.elementos.Burbujas;
import udemy.poo.elementos.ImagenFondoGameN;
import udemy.poo.elementos.ImagenFondoGaming;
import udemy.poo.elementos.Nave;
import udemy.poo.elementos.Puntuacion;
import udemy.poo.inicio.Inicio;
import udemy.poo.pantalla.Pantalla;
import udemy.poo.sonido.EfectosDeMusica;
import udemy.poo.sonido.Musica;

/**
 *
 * @author RONALD
 */
public class GameModelNave extends javax.swing.JDialog {

    private int fps = 30;
    private Timer tiempo;
    private Musica music = new Musica("Naruto.mp3", "LargestPiano.mp3");
    private Thread hilo = new Thread(music);

    /**
     * Creates new form GameModelNave
     */
    public GameModelNave(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        ActionListener accion = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        };
        this.tiempo = new Timer(10, accion);
        this.tiempo.start();
        setTitle("Game Nave");
        initComponents();

        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.jPanel2.requestFocusInWindow();

        ImagenFondoGameN fondoImagen = ImagenFondoGameN.imagenFondo();
        fondoImagen.configuracion(this.jPanel1, "AvanceComplete.gif", "SecondPrincess.gif");

        ImagenFondoGaming fondoGame = ImagenFondoGaming.imagenFondo();
        fondoGame.configuracion(this.jPanel2, "anime2.gif");

        Burbujas burbujas = Burbujas.getBurbujas();
        burbujas.configurar(this.jPanel2, "orbe.png");

        Nave nave = Nave.getNave();
        nave.configurar(this.jPanel2, "nave.png");

        Puntuacion puntuacion = new Puntuacion(jPanel2);

        //Añadiendo componentes a lo paneles
        ((Pantalla) this.jPanel1).getComponente().add(fondoImagen);
        ((Pantalla) this.jPanel2).getComponente().add(fondoGame);
        ((Pantalla) this.jPanel2).getComponente().add(burbujas);
        ((Pantalla) this.jPanel2).getComponente().add(nave);
        ((Pantalla) this.jPanel2).getComponente().add(puntuacion);
        hilo.start();
    }

    private void refresh() {
        long inicio;
        long transcurrido;
        long espera;
        try {
            inicio = System.nanoTime();
            this.jPanel1.repaint();
            transcurrido = System.nanoTime();
            espera = 1000 / fps - (transcurrido - inicio) / 1000000;
            Thread.sleep(espera);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new udemy.poo.pantalla.Pantalla(this.tiempo);
        jPanel2 = new udemy.poo.pantalla.Pantalla(this.tiempo);
        jMenuBar1 = new javax.swing.JMenuBar();
        backHomeButton = new javax.swing.JMenu();
        infoButton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 255, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        backHomeButton.setText("Home");
        backHomeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backHomeButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(backHomeButton);

        infoButton.setText("Información");
        infoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(infoButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backHomeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backHomeButtonMouseClicked
        // TODO add your handling code here:
        EfectosDeMusica sonido = new EfectosDeMusica("Zaz.mp3");
        Thread hiloDos = new Thread(sonido);
        hiloDos.start();

        Image imagen = Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/udemy/poo/recursos/orbe.png"));
        int answer = JOptionPane.showConfirmDialog(rootPane,
                "¿Seguro que deseas salir del juego?",
                "Saliendo del juego", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(rootPane,
                    "Saliendo del juego... y volviendo al menú principal",
                    "Information code", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(imagen));
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Inicio().setVisible(true);
                }
            });
            this.dispose();
            hilo.stop();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sigue adelante, no decaigas!", 
                    "information code", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(imagen));
        }
    }//GEN-LAST:event_backHomeButtonMouseClicked

    private void infoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoButtonMouseClicked
        // TODO add your handling code here:
        EfectosDeMusica sonido = new EfectosDeMusica("Zaz.mp3");
        Thread hiloDos = new Thread(sonido);
        hiloDos.start();
        
        String datos;
        datos = "Usa las teclas W, A, S, D \n" + "\n"
                + "W - Dirección arriba" + "\n"
                + "A - Dirección izquierda" + "\n"
                + "S - Dirección abajo" + "\n"
                + "D - Direccidn derecha \n" + "\n"
                + "¡Gánale al orbe blanco!";
        
        JOptionPane.showMessageDialog(rootPane, datos, "Información del juego", 
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/udemy/poo/recursos/orbe.png"));
    }//GEN-LAST:event_infoButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameModelNave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameModelNave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameModelNave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameModelNave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameModelNave dialog = new GameModelNave(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu backHomeButton;
    private javax.swing.JMenu infoButton;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
