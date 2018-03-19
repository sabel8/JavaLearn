package LowEvents;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author x360g
 */
public class HercegFokusz extends JFrame implements FocusListener {

    JWindow fut;
    JLabel img = new JLabel(new ImageIcon("icons/duke_running.gif"));
    Timer t = new Timer(1000, (ActionEvent e) -> {
        fut.setVisible(false);
    });

    public HercegFokusz() {
        setTitle("Hangos fókusz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        addFocusListener(this);
        JButton b = new JButton("Fókusz itt");
        c.add(b, "North");
        b.addFocusListener(this);
        JTextField tf = new JTextField(10);
        c.add(tf, "South");
        tf.addFocusListener(this);
        fut = new JWindow();
        fut.add(img);
        fut.pack();
        t.setRepeats(false);
        setVisible(true);

        fut.setVisible(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
        fut.setVisible(true);
        t.start();
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    public static void main(String[] args) {
        new HercegFokusz();
    }
}
