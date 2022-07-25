package tau.asteroidgame.Util;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.AsteroidField;
import tau.asteroidgame.Coal;
import tau.asteroidgame.Ice;
import tau.asteroidgame.Iron;
import tau.asteroidgame.Material;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Robot;
import tau.asteroidgame.UnidentifiedFlyingObject;
import tau.asteroidgame.Uranium;
import tau.asteroidgame.UserInterface.AsteroidViewControl;
import tau.asteroidgame.UserInterface.IconConstraints;
import tau.asteroidgame.UserInterface.MaterialViewControl;
import tau.asteroidgame.UserInterface.ViewControlDatabase;
import tau.asteroidgame.UserInterface.WorkerViewControl;
import tau.asteroidgame.UserInterface.NewGamePanel.Player;


/**
 * Új játékot generál véletlenszerűen, adott telepesek listáját kapva ikonnal.
 * A generált objektumok CommandDataBase-ba beregiszrálása,
 *  megfelelő ViewControl létrehozása és beregisztrálását ViewControlDatabase-be is lekezeli.
 */
public class NewGameGenerator {
	/**
	 * Új játékot generál véletlenszerűen, adott telepesek listáját kapva ikonnal.
	 * A generált objektumok CommandDataBase-ba beregiszrálása,
	 *  megfelelő ViewControl létrehozása és beregisztrálását ViewControlDatabase-be is lekezeli.
	 */
	static public void generateNewGame(List<Player> players) {
		CommandDatabase.reset();
		ViewControlDatabase.reset();
		// Game.INSTANCE.reset(); CommandDatabase.reset() meghívja
		Random rand = new Random();
		
		int numOfSettlers = players.size();
		int numOfRobots = 1 + rand.nextInt(10);
		int numOfUFOs = 1 + rand.nextInt(10);
		int numOfAsteroids = 20 + rand.nextInt(25);
		
		AsteroidField af = new AsteroidField();
		CommandDatabase.registerAsteroidField(af);
		CommandDatabase.bindAsteroidField(af);
		
		@SuppressWarnings("rawtypes")
		Class cl = NewGameGenerator.class;
		ImageIcon asteroidIcon = new ImageIcon(new ImageIcon(cl.getResource("/asteroid.png")).getImage().getScaledInstance(IconConstraints.asteroidIconSize, IconConstraints.asteroidIconSize, Image.SCALE_SMOOTH));
		ImageIcon ironIcon = new ImageIcon(new ImageIcon(cl.getResource("/materials/iron.png")).getImage().getScaledInstance(IconConstraints.itemIconSize, IconConstraints.itemIconSize, Image.SCALE_SMOOTH));
		ImageIcon iceIcon = new ImageIcon(new ImageIcon(cl.getResource("/materials/ice.png")).getImage().getScaledInstance(IconConstraints.itemIconSize, IconConstraints.itemIconSize, Image.SCALE_SMOOTH));
		ImageIcon coalIcon = new ImageIcon(new ImageIcon(cl.getResource("/materials/coal.png")).getImage().getScaledInstance(IconConstraints.itemIconSize, IconConstraints.itemIconSize, Image.SCALE_SMOOTH));
		ImageIcon uraniumIcon = new ImageIcon(new ImageIcon(cl.getResource("/materials/uranium.png")).getImage().getScaledInstance(IconConstraints.itemIconSize, IconConstraints.itemIconSize, Image.SCALE_SMOOTH));
		ImageIcon robotIcon = new ImageIcon(new ImageIcon(cl.getResource("/robot.png")).getImage().getScaledInstance(IconConstraints.workerIconSize, IconConstraints.workerIconSize, Image.SCALE_SMOOTH));
		ImageIcon ufoIcon = new ImageIcon(new ImageIcon(cl.getResource("/ufo.png")).getImage().getScaledInstance(IconConstraints.workerIconSize, IconConstraints.workerIconSize, Image.SCALE_SMOOTH));
		
		
		List<Asteroid> asteroidList = new ArrayList<Asteroid>();
		// Generate Asteroids
		for (int i = 0; i < numOfAsteroids; i++) {
			int layer = 1 + rand.nextInt(10);
			int sunDistMax = 10 + rand.nextInt(20);
			
			int matIndex = rand.nextInt(4);
			Material m = null;
			ImageIcon mIcon = null;
			MaterialViewControl mvc = null;
			switch (matIndex) {
			case 0:
				m = new Ice();
				mIcon = iceIcon;
				break;
			case 1:
				m = new Iron();
				mIcon = ironIcon;
				break;
			case 2:
				m = new Coal();
				mIcon = coalIcon;
				break;
			case 3:
			default:
				m = new Uranium();
				mIcon = uraniumIcon;
			}
			
			CommandDatabase.registerMaterial(m);
			mvc = new MaterialViewControl(m, mIcon);
			ViewControlDatabase.register(m, mvc);
			
			
			Asteroid a = new Asteroid(layer, m, sunDistMax);
			CommandDatabase.registerAsteroid(a);
			AsteroidViewControl avc = new AsteroidViewControl(a, asteroidIcon);
			ViewControlDatabase.register(a,  avc);
			
			af.addAsteroid(a);
			asteroidList.add(a);
		}
		
		
		// Generate settlers
		for (int i = 0; i < numOfSettlers; i++) {
			Player p = players.get(i);
			Settler s = new Settler(asteroidList.get(rand.nextInt(numOfAsteroids)));
			CommandDatabase.registerSettler(s, p.getName());
			WorkerViewControl svc = new WorkerViewControl(s, p.getIcon());
			ViewControlDatabase.register(s, svc);
		}
			
		// Generate robots
		for (int i = 0; i < numOfRobots; i++) {
			Robot r = new Robot(asteroidList.get(rand.nextInt(numOfAsteroids)));
			CommandDatabase.registerRobot(r);
			WorkerViewControl rvc = new WorkerViewControl(r, robotIcon);
			ViewControlDatabase.register(r, rvc);
		}	
		// Generate UFOS
		for (int i = 0; i < numOfUFOs; i++) {
			UnidentifiedFlyingObject u = new UnidentifiedFlyingObject(asteroidList.get(rand.nextInt(numOfAsteroids)));
			CommandDatabase.registerUFO(u);
			WorkerViewControl uvc = new WorkerViewControl(u, ufoIcon);
			ViewControlDatabase.register(u, uvc);
		}
		
		
		// set neighbor status (asteroids make up connected graph);
		int asteroidSize = asteroidList.size();
		
		for (int i = 0; i < asteroidSize - 1; i++) {
			Asteroid a = asteroidList.get(i);
			Asteroid b = asteroidList.get(i+1);
			a.addNeighbor(b);
			b.addNeighbor(a);
			int extraEdges = rand.nextInt(4);
			int extraEdges_i = 0;
			// Asteroid_size >= 20, legfeljebb 5 szomszéd így a while biztosan véget ér.
			while (extraEdges_i < extraEdges) {
				b = asteroidList.get(rand.nextInt(asteroidSize));
				if (b != a && !a.getNeighbors().contains(b)) {
					a.addNeighbor(b);
					b.addNeighbor(a);
				}
				extraEdges_i++;
			}
		}		
	}
}
