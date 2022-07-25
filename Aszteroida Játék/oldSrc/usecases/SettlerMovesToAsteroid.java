package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerMovesToAsteroid implements UseCase {
	String program = "p";
	Settler s;
	Asteroid a;
	Asteroid dest;
	
	@Override
	public void init() {
		Logger.clear(); Logger.registerObject(program, "Program");
		Logger.registerObject(this, "Skeleton");
		
		Logger.pushToRegisterNameStack("s", "a", "dest");
		
		Logger.createObjectNextRegister(program);
		s = new Settler();
		
		Logger.createObjectNextRegister(program);
		a = new Asteroid();
		
		Logger.createObjectNextRegister(program);
		dest = new Asteroid();
		
		Logger.callObject(program, a, "acceptWorker", s);
		a.acceptWorker(s);
		
		Logger.callObject(program, a, "addNeighbor", dest);
		a.addNeighbor(dest);
		
		Logger.callObject(program, dest, "addNeighbor", a);
		dest.addNeighbor(a);
	}

	@Override
	public void run() {
		Logger.callObject(this, s, "move", dest);
		s.move(dest);
	}
	
	
	public String toString() {
		return "Settler Moves To Asteroid";
	}

}
