package LowEvents;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author x360g
 */
public class EgerKoord2 extends JFrame {

    public EgerKoord2() {
        setTitle("Egér koordináta 1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        add(new EgerPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        new EgerKoord2();
    }
}

class EgerPanel extends JPanel implements MouseListener {

    private Vector koordianatak = new Vector();

    public EgerPanel() {
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        for (int i = 0; i < koordianatak.size(); i++) {
            Point e = (Point) koordianatak.get(i);
            int x = (int) e.getX();
            int y = (int) e.getY();
            g.drawString(".(" + x + "," + y + ")", x - 1, y + 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            Graphics g = getGraphics();
            g.setFont(new Font("Arial", Font.ITALIC, 20));
            g.drawString(".(" + e.getX() + "," + e.getY() + ")",
                    e.getX() - 1, e.getY() + 1);
            koordianatak.add(new Point(e.getX(), e.getY()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

//class Pont{
//    private int x,y;
//
//    public Pont(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}
