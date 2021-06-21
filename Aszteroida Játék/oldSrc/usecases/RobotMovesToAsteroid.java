package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Robot;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class RobotMovesToAsteroid implements UseCase{
	String program = "p";
	Robot r;
	Asteroid a;
	Asteroid dest;
	
	@Override
	public void init() {
		Logger.registerObject(program, "Program");
		Logger.registerObject(this, "Skeleton");
		
		Logger.pushToRegisterNameStack("r", "a", "dest");
		
		Logger.createObjectNextRegister(program);
		r = new Robot();
		
		Logger.createObjectNextRegister(program);
		a = new Asteroid();
		
		Logger.createObjectNextRegister(program);
		dest = new Asteroid();
		
		Logger.callObject(program, a, "acceptWorker", r);
		a.acceptWorker(r);
		
		Logger.callObject(program, a, "addNeighbor", dest);
		a.addNeighbor(dest);
		
		Logger.callObject(program, dest, "addNeighbor", a);
		dest.addNeighbor(a);
	}
	@Override
	public void run() {
		Logger.callObject(this, r, "move", dest);
		r.move(dest);
	}
	
	public String toString() {
		return "Robot Moves To Asteroid";
	}	
}
