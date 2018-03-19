package LowEvents;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author x360g
 */
public class HangosFokusz extends JFrame implements FocusListener {

    public HangosFokusz() {
        setTitle("Hangos fókusz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        addFocusListener(this);
        JButton b = new JButton("Fókusz itt");
        c.add(b,"North");
        b.addFocusListener(this);
        JTextField tf = new JTextField(10);
        c.add(tf,"South");
        tf.addFocusListener(this);
        setVisible(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("fókusz itt: " + e.getComponent().getClass());
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    public static void main(String[] args) {
        new HangosFokusz();
    }
}