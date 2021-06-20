package tau.asteroidgame.UserInterface;

import tau.asteroidgame.Settler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Az osztály felelőssége, egy ablaktábla biztosítása a játékos számára.
 * Itt jelenik meg a telepes leltárában található nyersanyagok, teleport kapuk,
 * valamit azok a gombok, amivel a telepes irányítható.
 */
public class InGamePanel extends JPanel implements ActionListener{

    MenuButton menuButton = new MenuButton("Menu");

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    private Settler currentSettler;
    private AsteroidFieldPanel afp = new AsteroidFieldPanel();
    private SettlerInfoPanel sip = new SettlerInfoPanel();
    private MainFrame mainFrame;

    /**
     * Frissíti a panelt a paraméterül kapott telepes adatai alapján.
     * @param s
     */
    public void update(Settler s){
        currentSettler = s;
        afp.update(s);
        sip.update(s);

    }

    /**
     * Az InGamePanel konstruktora.
     * @param mainFrame
     */
    public InGamePanel(MainFrame mainFrame) {
        super();
        mainFrame = mainFrame;
        setOpaque(false);
        setLayout(new BorderLayout(0,0));
        menuButton.setPreferredSize(new Dimension(130, 39));
        MainFrame finalMainFrame = mainFrame;
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalMainFrame.switchPanel(MainFrame.Panels.MAINMENU);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(menuButton);
        buttonPanel.setOpaque(false);
        JPanel bg = new BackgroundPanel();
        bg.setLayout(new BorderLayout(0,0));
        bg.add(buttonPanel, BorderLayout.NORTH);
        bg.add(afp, BorderLayout.CENTER);
        bg.add(sip, BorderLayout.SOUTH);
        bg.setVisible(true);
        add(bg);
        setVisible(true);
    }

    /**
     * A háttérpanel, mely a háttérképet ábrázolja.
     */
    private static class BackgroundPanel extends JPanel {
        private BufferedImage backgroundTile;

        public BackgroundPanel(){
            try {
                backgroundTile = ImageIO.read(getClass().getResource("/background.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int tileWidth = backgroundTile.getWidth();
            int tileHeight = backgroundTile.getHeight();
            for (int y = 0; y < getHeight(); y += tileHeight) {
                for (int x = 0; x < getWidth(); x += tileWidth) {
                    g2d.drawImage(backgroundTile, x, y, this);
                }
            }

            g2d.dispose();
        }
    }

    /**
     * A panel nevének lekérdezésére szolgál.
     * @return
     */
    @Override
    public String getName() {
        return "In Game Panel";
    }
}
