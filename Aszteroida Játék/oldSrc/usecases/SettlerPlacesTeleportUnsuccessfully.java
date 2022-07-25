package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Settler;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerPlacesTeleportUnsuccessfully implements UseCase {
	Settler s;

	@Override
	public void init() {
		Logger.clear();
		String program = "";
		Logger.registerObject(this, "Skeleton");
		Logger.registerObject(program, "Skeleton");
		Logger.pushToRegisterNameStack("s");
		
		Logger.createObjectNextRegister(program);
		s = new Settler();
		
	}

	@Override
	public void run() {
		Logger.callObject(this, s, "placeTeleport");
		s.placeTeleport();
	}
	
	

	public String toString() {
		return "Settler Places Teleport Unsuccessfully";
	}
}
