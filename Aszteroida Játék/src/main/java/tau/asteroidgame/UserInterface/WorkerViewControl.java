package tau.asteroidgame.UserInterface;

import javax.swing.*;

import tau.asteroidgame.Worker;

/**
 * Kapott Gombra kirajzoltatja az adott Munkáshoz (UnidentifiedFlyingObject / Settler / Robot) tartozó képet úgy,
 * hogy ne lehessen klikkelhető.
 */
public class WorkerViewControl extends ViewControl{
	/**
	 * Az adott munkas
	 */
	Worker w;
	/**
	 *  A kép, ami az adott munkást reprezentálja.
	 */
    private ImageIcon workerImage;

	/**
	 * Kirajzolja workerImage-t a j gombra, és nem klikkelhetővé teszi.
	 * @param j
	 */
	public void draw(JButton j){
    	j.setIcon(workerImage);
    	j.setDisabledIcon(workerImage);
    	j.setEnabled(false);
    }
    public WorkerViewControl(Worker w, ImageIcon workerImage) {
    	this.w = w;
    	this.workerImage = workerImage;
    }
}
