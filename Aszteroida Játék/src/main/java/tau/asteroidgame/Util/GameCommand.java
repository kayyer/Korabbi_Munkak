package tau.asteroidgame.Util;

import java.io.File;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import tau.asteroidgame.AsteroidField;
import tau.asteroidgame.Game;

/**
 * Kapott paraméterek alapján végre hatja utasításait a Game-re vonatkozó utasításokat 	esetleges hibajelzéssel. 
 */
public class GameCommand implements ICommand {

	/**
	 * Kapott paraméterek alapján végre hatja utasításait a Game-re vonatkozó utasításokat 	esetleges hibajelzéssel. 
	 */
	@Override
	public void run(String[] param) {
		if (param.length < 2) {
			System.out.println("ERROR: Invalid Parameter Format");
			return;
		}
		switch(param[1]) {
		case "startloop":
			System.out.println("game STARTED");
			Game.INSTANCE.play();
			break;
		case "deterministicMode":
			if (param.length < 3 || !(param[2].equals("false") || param[2].equals("true"))) {
				System.out.println("ERROR: Invalid Parameter Format");
				break;
			}
				
			Game.INSTANCE.setDeterministic(param[2].equals("false") ? false : true);
			break;
		case "bindAsteroidField":
			AsteroidField af;
			if (param.length < 3 || (af = CommandDatabase.getAsteroidField(param[2])) == null) {
				System.out.println("ERROR: Invalid Parameter Format");
				break;
			}
			Game.INSTANCE.setField(af);
			break;
		case "save":
			if (param.length < 3) {
				System.out.println("ERROR: Invalid Parameter Format");
				break;
			}
			PrintStream filePs = null;
			try {
				filePs = new PrintStream(new File(param[2] + ".gameState"));
				printGameInfo(filePs);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (filePs != null)
					filePs.close();
			}
			break;
		case "info":
			printGameInfo(System.out);
			break;
		default:
			System.out.println("ERROR: Invalid Command");
		}
		
	}
	
	/**
	 * Kapott printStreambe kiíratja az összes objektumot ami az adott Collectionben van.
	 */
	private  <T> void printObjectsGeneric(PrintStream ps, Collection<T> objs) {
		Iterator<T> it = objs.iterator();
		while (it.hasNext()) {
			ps.println(it.next());
		}
	}
	
	/**
	 * Az összes CommandDatabase-be beregisztrálható objektumot kiiratja a paraméterként kapott PrintStreambe.
	 */
	private void printGameInfo(PrintStream ps) {
		printObjectsGeneric(ps, CommandDatabase.getAllAsteroidFields());
		printObjectsGeneric(ps, CommandDatabase.getAllAsteroids());
		printObjectsGeneric(ps, CommandDatabase.getAllSettlers());
		printObjectsGeneric(ps, CommandDatabase.getAllRobots());
		printObjectsGeneric(ps, CommandDatabase.getAllUFOs());
		printObjectsGeneric(ps, CommandDatabase.getAllTeleports());	
	}

}
