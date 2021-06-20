package tau.asteroidgame.Util.usecases;
import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerMinesNonEmptyCoreAsteroid implements UseCase{
	Settler s;
	Asteroid a;
	Inventory inv;
	Iron core;
	@Override
	public void init() {

        Logger.registerObject(this, "Skeleton");
        Logger.pushToRegisterNameStack("inv", "s","a","core");
        
        Logger.createObjectNextRegister(this);
        inv = new Inventory();
        Logger.createObjectNextRegister(this);
        s = new Settler();
        Logger.createObjectNextRegister(this);
        a = new Asteroid();
        Logger.createObjectNextRegister(this);
        core = new Iron();
        
        Logger.callObject(this, s, "setInventory", inv);
        s.setInventory(inv);
        
		Logger.callObject(this, a, "acceptWorker", s);
		a.acceptWorker(s);
		
		Logger.callObject(this, a, "setCore", core);
		a.setCore(core);
		
		Logger.callObject(this, a, "setLayer", 1);
		a.setLayer(1);
		
		Logger.callObject(this, a, "SetLayersDrilled", 1);
		a.setLayersDrilled(1);
	}
	@Override
	public void run() {
        Logger.callObject(this, s, "mine");
        s.mine();
	}
	@Override
	public String toString() {
		return "Settler Mines Non-Empty Core Asteroid";
	}
	
}
