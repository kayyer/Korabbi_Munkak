package tau.asteroidgame.UserInterface;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Util.CommandDatabase;

import javax.swing.*;
import java.awt.*;

/**
 * Az osztály felelőssége egy klikkelhető aszteroida kirajzolása egy gombra,
 * mely parancsát átállítja úgy, hogy az ő által reprezentált
 * Asteroid-ra történő mozgást értelmezni lehessen.
 */
public class AsteroidViewControl extends ViewControl{
    private Asteroid asteroid;
    private ImageIcon asteroidImage;

    /**
     * Kirajzolja a paraméterként kapott JButton-t a megfelelő képpel és klikkelhetővé teszi.
     * A gomb parancsát átállítja “move_aID”-re, ahol aID az asteroid-hoz ID. (CommandDataBase-ből kiszedhető)
     * @param j
     */
    public void draw(JButton j){
        j.setEnabled(true);
        j.setIcon(asteroidImage);
        j.setDisabledIcon(asteroidImage);
        j.setActionCommand("move_" + CommandDatabase.getAsteroidID(asteroid));
    }

    /**
     * Az osztály konstruktora, paraméterben megkapja az aszteroidát és az aszteroida ikonját.
     * @param asteroid
     * @param asteroidImage
     */
    public AsteroidViewControl(Asteroid asteroid, ImageIcon asteroidImage) {
        this.asteroid = asteroid;
        this.asteroidImage = asteroidImage;
    }
}
