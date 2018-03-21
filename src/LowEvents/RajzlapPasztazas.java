package LowEvents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author abel
 */
public class RajzlapPasztazas extends JFrame implements AdjustmentListener {

    public RajzlapPasztazas() {
        setTitle("Pásztázás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JScrollPane sc;
        add(sc = new JScrollPane(new Mestermu()));
        sc.getHorizontalScrollBar().addAdjustmentListener(this);
        sc.getVerticalScrollBar().addAdjustmentListener(this);
        setSize(500, 300);
        setVisible(true);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent ae) {
        repaint();
    }

    public static void main(String[] args) {
        new RajzlapPasztazas();
    }
}

class Mestermu extends JPanel {

    public Mestermu() {
        setPreferredSize(new Dimension(2000, 1000));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawOval(0, 0, 2000, 1000);
        g.drawLine(0, 0, 2000, 1000);
        g.drawLine(0, 1000, 2000, 0);
    }
}
