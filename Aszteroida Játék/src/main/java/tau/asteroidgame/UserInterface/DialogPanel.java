package tau.asteroidgame.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Felugró üzenetek megjelenítésére használt panel
 */
public class DialogPanel extends JPanel {
    /**
     * Az üzenet megjelenítésére használt szövegdoboz.
     */
    private JTextArea text;
    public DialogPanel(MainFrame mainFrame){
        super();

        setOpaque(false);
        setAlignmentY(CENTER_ALIGNMENT);
        JPanel popupPanel = new PopupPanel(mainFrame);
        text = new JTextArea("Sample Text");
        text.setFont(MainFrame.starGuardianFont.deriveFont(32.f));
        // Ha egy szó túl hosszú, kerüljön új sorba!
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        // Nem kell háttér
        text.setOpaque(false);
        // Ne legyen szerkeszthető
        text.setEditable(false);
        text.setFocusable(false);
        popupPanel.add(text);
        popupPanel.setPreferredSize(new Dimension(300,200));


        add(popupPanel);
    }

    /**
     * Megjelenítendő üzenet beállítása
     * @param s A megjelenítendő üzenet
     */
    public void setMessage(String s) {
        text.setText(s);
        updateUI();
    }

    @Override
    public String getName() {
        return "Dialog panel";
    }

    /**
     * Jelenleg megjelenített szöveg lekérdezése
     * @return A jelenleg megjelenített szöveg
     */
    public String getText() {return text.getText();}
}
