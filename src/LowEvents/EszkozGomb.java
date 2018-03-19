package LowEvents;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JToggleButton;

/**
 * Egy rajzolóeszköz kiválasztásához használatos gomb.
 * @author x360g
 */
public class EszkozGomb extends JToggleButton {

    public static final int VONAL = 0;
    public static final int PONT = 1;
    public static final int TEGLALAP = 2;
    public static final int OVAL = 3;
    public static final int R_TEGLALAP = 4;
    static int c = 0;

    private int state;

    public EszkozGomb(int state) {
        setPreferredSize(new Dimension(50, 20));
        this.state = state;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rajz(g);
    }
    
    /**
     * Alakzat alapján rajzol.
     * @param g Grafikus interfész.
     */
    public void rajz(Graphics g) {
        switch (state) {
            case VONAL:
                g.drawLine(0, 0, getWidth(), getHeight());
                break;
            case PONT:
                g.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2);
                break;
            case TEGLALAP:
                g.drawRect(2, 2, getWidth() - 5, getHeight() - 5);
                break;
            case OVAL:
                g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
                break;
            case R_TEGLALAP:
                g.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, 10, 10);
                break;
        }
    }

    public int getState() {
        return state;
    }

}