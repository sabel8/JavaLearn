package LowEvents;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author x360g
 */
public class EsemenyFigyelo extends JFrame {

    private JTextArea log = new JTextArea();
    private int n = 0;

    public EsemenyFigyelo() {
        setTitle("Del-Ablak törlése, Ins-Új sor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        JScrollPane sp = new JScrollPane(log);
        log.setEditable(false);
        add(sp);
        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                kiir("Aktív lettem!");
            }

            public void windowIconified(WindowEvent e) {
                kiir("Ikonizáltak!");
            }

            public void windowOpened(WindowEvent e) {
                kiir("Megnyitottak!");
            }
        });
        addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                kiir("Áthelyeztek! Helyem: " + getX() + "," + getY());
            }

            public void componentResized(ComponentEvent e) {
                kiir("Átméreteztek! Méretem: " + getWidth() + "*" + getHeight());
            }
        });
        log.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DELETE:
                        log.setText("");
                        break;
                    case KeyEvent.VK_INSERT:
                        log.setText(log.getText() + "\n");
                        break;
                    default:
                        kiir("Megnyomtak egy billentyűt! Kód: " + e.getKeyCode()
                                + " Kar: " + e.getKeyChar() + " Mod: " +
                                InputEvent.getModifiersExText(e.getModifiersEx()));
                        break;
                }
            }
        });
        setVisible(true);
    }

    void kiir(String str) {
        log.setText(log.getText() + n++ + ". " + str + "\n");
    }

    public static void main(String[] args) {
        new EsemenyFigyelo();
    }
}
