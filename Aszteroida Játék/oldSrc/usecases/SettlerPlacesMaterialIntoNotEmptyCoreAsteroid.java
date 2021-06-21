package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Coal;
import tau.asteroidgame.Inventory;
import tau.asteroidgame.Iron;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerPlacesMaterialIntoNotEmptyCoreAsteroid implements UseCase {

	Inventory inv;
	Settler s;
	Iron m;
	Asteroid a;
	Coal core;
	
	
	@Override
	public void init() {
		String program = "";
		Logger.registerObject(program, "Program");
		Logger.registerObject(this, "Skeleton");
		Logger.pushToRegisterNameStack("inv", "s", "m", "a", "core");
		
		Logger.createObjectNextRegister(program);
		inv = new Inventory();
		
		Logger.createObjectNextRegister(program);
		s = new Settler();

		Logger.createObjectNextRegister(program);
		m = new Iron();
		
		Logger.createObjectNextRegister(program);
		a = new Asteroid();
		
		Logger.createObjectNextRegister(program);
		core = new Coal();
		
		Logger.callObject(program, s, "setInventory", inv);
		s.setInventory(inv);
		
		Logger.callObject(program, s, "acceptMaterial", m);
		s.acceptMaterial(m);
		
		Logger.callObject(program, a, "acceptWorker", s);
		a.acceptWorker(s);
		
		Logger.callObject(program, a, "setLayer", 1);
		a.setLayer(1);
		
		Logger.callObject(program, a, "setLayersDrilled", 1);
		a.setLayersDrilled(1);
		
		Logger.callObject(program, a, "setCore", s);
		a.setCore(core);

	}

	@Override
	public void run() {
		Logger.callObject(this, s, "placeMaterial", m);
		s.placeMaterial(m);
	}
	
	public String toString() {
		return "Settler Places Material Into Not Empty Core Asteroid";
	}

}
