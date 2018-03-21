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

    private int gridStepX = 10, gridStepY = 10;
    private Vector teglalapok;
    private Teglalap aktT;
    private Teglalap t;
    private int kezdoX, kezdoY, x, y, width, height;

    public Felulet() {
        teglalapok = new Vector();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                t = new Teglalap(kezdoX, kezdoY);
                kezdoX = e.getX();
                kezdoY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                teglalapok.add(t);
                repaint();
            }

        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Graphics g = getGraphics();
                g.setXORMode(getBackground());
                t.draw(g);
                x = Integer.min(kezdoX, e.getX());
                y = Integer.min(kezdoY, e.getY());
                width = Math.abs(kezdoX - e.getX());
                height = Math.abs(kezdoY - e.getY());
                t = new Teglalap(x, y, width, height);
                t.draw(g);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("teglalapok.size() = " + teglalapok.size());
        for (int i = 0; i < teglalapok.size(); i++) {
            aktT = (Teglalap) teglalapok.get(i);
            g.drawRect(aktT.getKezdoX(), aktT.getKezdoY(),
                    aktT.getWidth(), aktT.getHeight());
        }
        for (int ix = 0; ix < getWidth(); ix += gridStepX) {
            for (int iy = 0; iy < getHeight(); iy += gridStepY) {
                g.fillOval(ix, iy, 2, 2);
            }
        }
        for (int iy = 0; iy < getHeight(); iy += gridStepY) {
            for (int ix = 0; ix < getWidth(); ix += gridStepX) {
                g.fillOval(ix, iy, 2, 2);
            }
        }
    }
}

class Teglalap {

    private int kezdoX, kezdoY;
    private int width, height;

    public Teglalap(int kezdoX, int kezdoY, int width, int height) {
        this.kezdoX = kezdoX;
        this.kezdoY = kezdoY;
        this.width = width;
        this.height = height;
    }

    public Teglalap(int kezdoX, int kezdoY) {
        this.kezdoX = kezdoX;
        this.kezdoY = kezdoY;
        this.width = kezdoX;
        this.height = kezdoY;
    }

    void draw(Graphics g) {
        if (width != kezdoX) {
            g.drawRect(kezdoX, kezdoY, width, height);
        }
    }

    public int getKezdoX() {
        return kezdoX;
    }

    public int getKezdoY() {
        return kezdoY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setKezdo(int kezdoX, int kezdoY) {
        this.kezdoX = kezdoX;
        this.kezdoY = kezdoY;
    }

    public void setVeg(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
