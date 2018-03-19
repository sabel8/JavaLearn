package LowEvents;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author x360g
 */
public class KepMozgatas extends JFrame {

    Image pic, kisPic;
    KepPanel kp;
    int x, y;

    public KepMozgatas() {
        setTitle("Képmozgatás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        MediaTracker m = new MediaTracker(this);
        try {
            pic = Toolkit.getDefaultToolkit().createImage(new URL("file:///C:/Users/x360g/Pictures/hatter/2.png"));
            m.addImage(pic, 0);
            m.waitForAll();
            kisPic = pic.getScaledInstance(pic.getWidth(this) / 10, -1, Image.SCALE_DEFAULT);
        } catch (Exception ex) {
        }
        add(kp = new KepPanel(kisPic));
        setSize(500, 300);
        kp.requestFocus();
        setVisible(true);
    }

    public static void main(String[] args) {
        new KepMozgatas();
    }
}

class KepPanel extends JPanel implements KeyListener, ComponentListener {

    private Image pic;
    private int x, y;
    private boolean drawn;
    private int prevWidth = 0, prevHeight = 0;

    public KepPanel(Image pic) {
        this.pic = pic;
        x = 100;
        y = 100;
        drawn = true;
        addKeyListener(this);
        addComponentListener(this);
    }

    public void showOrNot() {
        drawn = drawn == true ? false : true;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (!(x <= 0)) {
                    x -= 10;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!(x >= getWidth() - pic.getWidth(this))) {
                    x += 10;
                }
                break;
            case KeyEvent.VK_UP:
                if (!(y <= 0)) {
                    y -= 10;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!(y >= getHeight() - pic.getHeight(this))) {
                    y += 10;
                }
                break;
            case KeyEvent.VK_F1:
                showOrNot();
            default:
                break;
        }
        setPosition(x, y);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocus();
        if (drawn) {
            g.drawImage(pic, x, y, this);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if (prevWidth != 0) {
            x = (int) (1.0 * getWidth() * x / prevWidth);
        }
        if (prevHeight != 0) {
            y = (int) (1.0 * getHeight() * y / prevHeight);
        }
        prevWidth = getWidth();
        prevHeight = getHeight();
        System.out.println(prevWidth + "," + prevHeight);
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

