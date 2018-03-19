package LowEvents;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author x360g
 */
public class AlakRajz extends JFrame {

    Color hatter, szin;
    EszkozGomb[] eszkozok = new EszkozGomb[5];
    ButtonGroup eszkozCsoport;
    JButton hatterGomb, szinGomb;
    JButton undo, redo;
    JPanel menuSor;
    Vaszon rajzFelulet;
    Vector history;
    int undoCounter = 0;

    public AlakRajz() {
        this.hatter = Color.WHITE;
        this.szin = Color.BLACK;
        hatterGomb = new JButton("Háttér");
        szinGomb = new JButton("Szín");
        rajzFelulet = new Vaszon();
        hatterGomb.addActionListener((ActionEvent e) -> {
            setHatter(JColorChooser.showDialog(this, "Válasszon hátteret", Color.WHITE));
        });
        szinGomb.addActionListener((ActionEvent e) -> {
            this.szin = JColorChooser.showDialog(this, "Válasszon színt", this.szin);
            szinGomb.setBackground(this.szin);
        });
        menuSor = new JPanel(new FlowLayout());
        history = new Vector();
        eszkozCsoport = new ButtonGroup();
        for (int i = 0; i < eszkozok.length; i++) {
            eszkozok[i] = new EszkozGomb(i);
            menuSor.add(eszkozok[i]);
            eszkozCsoport.add(eszkozok[i]);
        }
        rajzFelulet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (getChosenToolState() == -1) {
                    JOptionPane.showMessageDialog(rajzFelulet,
                            "Válasszon egy eszközt mielőtt rajzolni szeretne!",
                            "Nincs eszköz", JOptionPane.ERROR_MESSAGE);
                }
                Alakzat a = new Alakzat(getChosenToolState(), e.getX(), e.getY());
                history.add(a);
                rajzFelulet.addAlakzat(a);
                redo.setEnabled(false);
                undo.setEnabled(true);
                undoCounter = 0;
                rajzFelulet.repaint();
            }
        });

        undo = new JButton("UNDO");
        redo = new JButton("REDO");
        menuSor.add(undo);
        menuSor.add(redo);
        undo.addActionListener((ActionEvent e) -> {
            switch (rajzFelulet.getAlakzatCount()) {
                case 1:
//                    history = new Vector();
                    undo.setEnabled(false);
                default:
                    rajzFelulet.undo();
            }
            undoCounter++;
            redo.setEnabled(true);
        });
        redo.addActionListener((ActionEvent e) -> {
            undo.setEnabled(true);
            rajzFelulet.addAlakzat((Alakzat) history.get(history.size() - undoCounter));
            undoCounter--;
            if (undoCounter == 0) {
                redo.setEnabled(false);
            }
        });
        undo.setEnabled(false);
        redo.setEnabled(false);
        menuSor.add(hatterGomb);
        menuSor.add(szinGomb);
        add(menuSor, "North");
        add(rajzFelulet);
        setTitle("Alakzat Rajzolás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
        setHatter(Color.WHITE);
    }

    int getChosenToolState() {
        for (int i = 0; i < eszkozCsoport.getButtonCount(); i++) {
            if (eszkozok[i].isSelected()) {
                return eszkozok[i].getState();
            }
        }
        return -1;
    }

    public void setHatter(Color hatter) {
        this.hatter = hatter;
        hatterGomb.setBackground(hatter);
        rajzFelulet.setBackground(hatter);
    }

    public static void main(String[] args) {
        new AlakRajz();
    }
}

/**
 * Egy alakzatot tárol annnak későbbi kirajzolása érdekében. Tudja a helyet és
 * alakot. Kiterjeszti a <code>Point</code> osztályt.
 */
class Alakzat extends Point {

    int state;

    public Alakzat(int state, int x, int y) {
        super(x, y);
        this.state = state;
    }

    public int getState() {
        return state;
    }

}

class Vaszon extends JPanel {

    private Vector alakzatok;

    public Vaszon() {
        alakzatok = new Vector();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < alakzatok.size(); i++) {
            alakRajz((Alakzat) alakzatok.get(i), g);
        }
    }

    void addAlakzat(Alakzat a) {
        alakzatok.add(a);
        repaint();
    }

    int getAlakzatCount() {
        return alakzatok.size();
    }

    void undo() {
        alakzatok.remove(alakzatok.size() - 1);
        repaint();
    }

    /**
     * Megrajzol egy alakzatot.
     *
     * @param a Az alakzat.
     * @param g Grafikus interfész.
     */
    private void alakRajz(Alakzat a, Graphics g) {
        int x = (int) a.getX();
        int y = (int) a.getY();
        switch (a.getState()) {
            case EszkozGomb.VONAL:
                g.drawLine(x, y, x + 50, y + 20);
                break;
            case EszkozGomb.PONT:
                g.drawLine(x, y, x, y);
                break;
            case EszkozGomb.TEGLALAP:
                g.drawRect(x, y, 50, 20);
                break;
            case EszkozGomb.OVAL:
                g.drawOval(x, y, 50, 20);
                break;
            case EszkozGomb.R_TEGLALAP:
                g.drawRoundRect(x, y, 50, 20, 10, 10);
                break;
        }
    }

}
