package LowEvents;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author x360g
 */
public class VonszoltTegla extends JFrame {

    public VonszoltTegla() {
        setTitle("Vonszolt tégla");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        add(new Felulet());
        setVisible(true);
    }

    public static void main(String[] args) {
        new VonszoltTegla();
    }

}

class Felulet extends JPanel {

    private Vector teglalapok;
    private Teglalap t;
    private int kezdoX, kezdoY;

    public Felulet() {
        teglalapok = new Vector();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                kezdoX = e.getX();
                kezdoY = e.getY();

                Graphics g = getGraphics();
                g.drawRect(kezdoX, kezdoY, e.getX() - kezdoX, e.getY() - kezdoY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                teglalapok.add(new Teglalap(kezdoX, kezdoY, e.getX() - kezdoX, e.getY() - kezdoY));
                repaint();
            }

        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
//                try {
//                    teglalapok.remove(teglalapok.size());
//                } catch (Exception ex) {
//                }
//                teglalapok.add(new Teglalap(kezdoX, kezdoY, e.getX() - kezdoX, e.getY() - kezdoY));
//                repaint();
//                teglalapok.remove(teglalapok.size());

                Graphics g = getGraphics();
                g.clearRect(0, 0, getWidth(), getHeight());
                repaint();
                g.drawRect(kezdoX, kezdoY, e.getX() - kezdoX, e.getY() - kezdoY);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("teglalapok.size() = " + teglalapok.size());
        for (int i = 0; i < teglalapok.size(); i++) {
            t = (Teglalap) teglalapok.get(i);
            g.drawRect(t.getKezdoX(), t.getKezdoY(), t.getVegX(), t.getVegY());
        }
    }
}

class Teglalap {

    private int kezdoX, kezdoY;
    private int vegX, vegY;

    public Teglalap(int kezdoX, int kezdoY, int vegX, int vegY) {
        this.kezdoX = kezdoX;
        this.kezdoY = kezdoY;
        this.vegX = vegX;
        this.vegY = vegY;
    }

    public int getKezdoX() {
        return kezdoX;
    }

    public int getKezdoY() {
        return kezdoY;
    }

    public int getVegX() {
        return vegX;
    }

    public int getVegY() {
        return vegY;
    }

}
