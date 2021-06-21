package tau.asteroidgame;

import tau.asteroidgame.Util.CommandDatabase;
import tau.asteroidgame.Util.Input;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Az aszteroidamező kelti a napviharokat és lépteti az aszteroidákat. 
 */
public class AsteroidField implements Steppable {
    private final List<Asteroid> asteroids = new ArrayList<>();
    
    /**
     * Napvihart kelt az aszteroidamezőben.
     * Ekkor a mezőben véletlenszerűen lévő napvihar centrumként kiválasztott aszteroidán meghívódik a sufferSolarstorm() metódus 0-s mélységgel és üres AsteroidRegistry-vel. 
     */
    public void induceSolarStorm() {
        Random rn = new Random();

        AsteroidRegistry ar = new AsteroidRegistry();
        
        int stormCenterIdx = Game.INSTANCE.isDeterministic() ? 0 : rn.nextInt(asteroids.size());
        asteroids.get(stormCenterIdx).sufferSolarstorm(0, ar);
    }

    /**
     *  Véletlenszerűen, kis eséllyel meghívódik az induceSolarstorm() metódus.
     */
    public void step() {
    	boolean solarStormHappens = false;
    	
    	if (Game.INSTANCE.isDeterministic()) {
    		solarStormHappens = Input.getBooleanInput("Is a solar storm going to happen ?"); 
    	}
    	else {
    		Random rn = new Random();
    		float chance = rn.nextFloat();
    		solarStormHappens = chance < 0.9 ? false : true;
    	}
    	
    	if (solarStormHappens) {
    		Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.SUNSTORM, this));
    		induceSolarStorm();
    	}
    }
    /**
     * Hozzáadja a paraméterben kapott aszteroidát a mezőhöz.
     * @param a1 Az aszteroida amit hozzáadunk a mezőhöz
     */
    public void addAsteroid(Asteroid a1) {
        asteroids.add(a1);
    }
    
    
    @Override
    public String toString() {
    	return "AsteroidField " + CommandDatabase.getAsteroidFieldID(this);
    }
}
