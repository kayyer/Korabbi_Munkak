package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlersLose implements UseCase {
    private Settler s;
    private Asteroid a;

    @Override
    public void init() {
    	String program = ""; Logger.registerObject(program, "Program");
        Logger.registerObject(this, "Skeleton");
        Logger.pushToRegisterNameStack("s","a");

        Logger.createObjectNextRegister(program);
        s = new Settler();

        Logger.createObjectNextRegister(program);
        a = new Asteroid();
        
        Logger.callObject(program,s,"setAsteroid",a);
        s.setAsteroid(a);
    }

    @Override
    public void run() {
        Logger.callObject(this,s,"die");
        s.die();
    }

    @Override
    public String toString() {
        return "Settlers Lose";
    }
}
