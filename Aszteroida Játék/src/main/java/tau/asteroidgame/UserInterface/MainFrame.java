package tau.asteroidgame.UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Stack;

/**
 * Az osztály felelőssége egy ablak biztosítása a futó játék számára.
 */
public class MainFrame extends JFrame {
    /**
     * A fő menü panel elem.
     */
    private MainMenuPanel mainMenuPanel = null;
    /**
     * Az új játék indítás panel elem.
     */
    private NewGamePanel newGamePanel;
    /**
     * A játék közbeni panel elem.
     */
    private InGamePanel inGamePanel;
    /**
     * A felugró üzenetek panel eleme.
     */
    private DialogPanel dialogPanel;
    /**
     * A játékban használt fő betütípus
     */
    public static Font starGuardianFont =  null;
    /**
     * Az a tároló, ahol a megjelenítendő elemek lesznek
     */
    private JPanel container = null;
    /**
     * A jelenleg megjelenített panel elem
     */
    private Panels currPanel;
    /**
     * Az előző panelek kupaca (visszaváltáshoz)
     */
    private Stack<Panels> panelsStack = new Stack<>();
    /**
     * Megjelenítendő üzenetek kupaca (egyszerre várakozhat több is)
     */
    private Stack<String> messageStack = new Stack<>();
    public MainFrame() {
        super();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 681));

        setTitle("Asteroid Game");
        if(starGuardianFont == null) {
            try {
                starGuardianFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/StarGuardMidCase.ttf"));
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(starGuardianFont);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
       }
        BackgroundPanel bg = new BackgroundPanel();
        container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new CardLayout());
        mainMenuPanel =  new MainMenuPanel(this);
        newGamePanel = new NewGamePanel(this);
        inGamePanel = new InGamePanel(this);
        dialogPanel = new DialogPanel(this);
        container.add(mainMenuPanel,mainMenuPanel.getName());
        container.add(newGamePanel,newGamePanel.getName());
        container.add(inGamePanel, inGamePanel.getName());
        container.add(dialogPanel,dialogPanel.getName());
        bg.add(container);
        currPanel = Panels.MAINMENU;
        add(bg);
        setVisible(true);
    }


    /**
     * Megjeleníti az adott üzenetet egy fölugró ablakban.
     * @param s A megjelenítendő üzenet
     */
    public void displayMessage(String s){
        if(currPanel == Panels.DIALOG)
            messageStack.push(dialogPanel.getText());
        dialogPanel.setMessage(s);
        switchPanel(Panels.DIALOG);
    }

    /**
     * A megjeleleníthető panelek "nevei". Váltáskor ezzel lehet hivatkozni az egyes panelekre
     */
    public enum Panels {
        MAINMENU,
        NEWGAME,
        INGAME,
        DIALOG;
    }

    /**
     * Megjeleníti az adott üzenetet egy fölugró ablakban.
     * @param panel Az újonnan megjelenítendő panel
     */
    public void switchPanel(Panels panel) {
        panelsStack.push(currPanel);
        switch (panel){
            case MAINMENU:
                ((CardLayout)container.getLayout()).show(container,mainMenuPanel.getName());
                break;
            case NEWGAME:
                if(currPanel != Panels.DIALOG)
                    newGamePanel.reset();
                ((CardLayout)container.getLayout()).show(container,newGamePanel.getName());
                break;
            case INGAME:
                ((CardLayout)container.getLayout()).show(container,inGamePanel.getName());
                break;
            case DIALOG:
                ((CardLayout)container.getLayout()).show(container,dialogPanel.getName());
                break;
        }
        currPanel = panel;
    }

    /**
     * Visszavált a korábban megjelenített panelre (lehet több is)
     */
    public void switchToPreviousPanel(){
        Panels newPanel = panelsStack.pop();
        if(newPanel == Panels.DIALOG) {
            dialogPanel.setMessage(messageStack.pop());
        }
        switchPanel(newPanel);
        panelsStack.pop();
    }

    /**
     * Panel lekérése név alapján.
     * @param panel A keresett JPanel elem neve
     * @return A keresett JPanel elem
     */
    public JPanel getPanel(Panels panel) {
        switch(panel){
            case MAINMENU:
                return mainMenuPanel;
            case NEWGAME:
                return newGamePanel;
            case INGAME:
                return inGamePanel;
            default:
                return null;
        }
    }

    /**
     * A háttér megjeleítésére használt panel elem
     */
    private static class BackgroundPanel extends JPanel {
        /**
         * Háttér mező ikonja
         */
        private BufferedImage backgroundTile;
        /**
         * Aszeroida ikonja
         */
        private BufferedImage asteroidImage;
        /**
         * Zöld űrhajó ikonja
         */
        private BufferedImage starship_green;
        /**
         * Meghatározza, hogy a háttéren jelenjenek-e meg extra dekorációk. (Aszteroidák, űrhajó)
         */
        private boolean additionalDecors = true;

        public BackgroundPanel(){
            try {
                backgroundTile = ImageIO.read(getClass().getResource("/background.jpg"));
                BufferedImage asteroidImageTMP = ImageIO.read(getClass().getResource("/asteroid.png"));
                BufferedImage starship_greenTMP = ImageIO.read(getClass().getResource("/starships/green.png"));
                AffineTransform scaler = new AffineTransform();
                scaler.scale(0.5,0.5);
                AffineTransformOp scalerOp =new AffineTransformOp(scaler, AffineTransformOp.TYPE_BILINEAR);
                asteroidImage = scalerOp.filter(asteroidImageTMP, asteroidImage);
                starship_green = scalerOp.filter(starship_greenTMP, starship_green);
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
            if(additionalDecors){


                g2d.drawImage(asteroidImage,250,350,this);

                g2d.drawImage(asteroidImage,400,100,this);
                g2d.drawImage(asteroidImage,20,21,this);
                g2d.drawImage(asteroidImage,650,400,this);
                g2d.drawImage(asteroidImage,700,50,this);

                g2d.drawImage(starship_green,650,375,this);
            }
            g2d.dispose();
        }

        /**
         * Visszaadja,hogy van-e extra dekoráció a hátteren
         * @return Logikai változó az alapján, hogy van-e extra dekoráció a hátteren
         */
        public boolean hasAdditionalDecors() {
            return additionalDecors;
        }

        /**
         * Beállítja, hogy legyen-e extra dekoráció a hátteren
         * @param additionalDecors
         */
        public void setAdditionalDecors(boolean additionalDecors) {
            this.additionalDecors = additionalDecors;
        }
    }
}
