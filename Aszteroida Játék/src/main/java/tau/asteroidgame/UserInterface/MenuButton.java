package tau.asteroidgame.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A játék során megjelenő gombok osztálya
 * Azért van rá szükség, hogy a gombok egységesen legyenek megjelenítve anélkül,
 * hogy sok kódot kelljen átmásolni.
 */
public class MenuButton extends JButton {
    /**
     * A gombok kerete
     */
    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 4);
    /**
     * A gomb zárolt/lenyomott színe
     */
    Color orangeClickedColor = new Color(170, 68, 0);
    /**
     * A gomb alapértelmezett háttér színe
     */
    Color orangeBackgroundColor = new Color(255, 102, 0);

    public MenuButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        setFont(MainFrame.starGuardianFont.deriveFont(32.f));
        setBackground(orangeBackgroundColor);
        setBorder(blackBorder);
        setForeground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed() || !isEnabled()) {
            g.setColor(orangeClickedColor);
            setForeground(Color.BLACK);
        } else {
            g.setColor(getBackground());
        }

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
