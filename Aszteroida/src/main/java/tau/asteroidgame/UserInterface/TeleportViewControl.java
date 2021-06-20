package tau.asteroidgame.UserInterface;

import tau.asteroidgame.Teleport;
import tau.asteroidgame.Util.CommandDatabase;

import javax.swing.*;
import java.awt.*;

/**
 * Az osztály felelőssége egy teleport kirajzolása gombra, akár, mint lehelyezhető Teleport,
 * vagy lehelyezett Teleport, melyen keresztül át lehet mozogni. A parancsot annak megfelelően állítja be,
 * hogy értelmezni lehessen az ő általa reprezentált Teleportra történő mozgást, vagy annak lehelyezését.
 */
public class TeleportViewControl extends ViewControl{
    /**
     * Az őáltala reprezentált teleport.
     */
    private Teleport teleport;
    /**
     * A kép, mellyel a teleport lerakott allapotban ábrázolva van
     */
    private ImageIcon teleportImage = new ImageIcon(new ImageIcon(getClass().getResource("teleport.png")).getImage().getScaledInstance(IconConstraints.asteroidIconSize,IconConstraints.asteroidIconSize, Image.SCALE_SMOOTH));
    /**
     * A kép, mellyel a teleport a telepesnel ábrázolva van.
     */
    private ImageIcon teleportItemImage = new ImageIcon(new ImageIcon(getClass().getResource("teleport.png")).getImage().getScaledInstance(IconConstraints.itemIconSize,IconConstraints.itemIconSize, Image.SCALE_SMOOTH));

    /**
     * Kirajzolja a paraméterként kapott JButton-t a megfelelő képpel.
     * A gomb parancsát átállítja “move_teleportID”-ra, ahol a teleportID a teleport-hoz ID.
     * @param j
     */
    public void draw(JButton j){
    j.setEnabled(true);
    j.setIcon(teleportImage);
    String tID = CommandDatabase.getTeleportID(teleport);
    j.setActionCommand("move_" + tID);
    }

    /**
     * Kirajzolja a paraméterként kapott JButton-t a megfelelő képpel.
     * A gomb parancsát átállítja “placeTeleport_teleportID”-ra, ahol a teleportID a teleport-hoz ID.
     * @param j
     */
    public void drawAsItem(JButton j){
        j.setEnabled(true);
        j.setIcon(teleportItemImage);
        String tID = CommandDatabase.getTeleportID(teleport);
        j.setActionCommand("placeTeleport_" + tID);
    }
    public TeleportViewControl(Teleport t){
        teleport = t;
    }

}
