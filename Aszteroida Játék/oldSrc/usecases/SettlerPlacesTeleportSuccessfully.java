package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerPlacesTeleportSuccessfully implements UseCase {
    Settler s;
    Asteroid a;
    Teleport t;
    Teleport t2;

    @Override
    public void init() {
        Logger.clear();
        String program = "";

        Logger.registerObject(this,"Skeleton");
        Logger.registerObject(program, "Program");

        Logger.pushToRegisterNameStack("s", "a", "t", "t2");

        Logger.createObjectNextRegister(program);
        s = new Settler();

        Logger.createObjectNextRegister(program);
        a = new Asteroid();

        Logger.callObject(this, a, "acceptWorker", s);
        a.acceptWorker(s);

        Logger.createObjectNextRegister(program, s);
        t = new Teleport(s);

    }


    @Override
    public void run() {

        Logger.callObject(this,s,"placeTeleport");
        boolean res = s.placeTeleport();

        if(!res){
            System.out.println("Teleport placement was unsuccessful!");
        }

    }

    @Override
    public String toString() {
        return "Settler Places Teleport Successfully";
    }
}
