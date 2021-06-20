package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class NotPlacedTeleportGetsDestroyed implements UseCase {
    String program;
    Asteroid a;
    Asteroid a2;
    Settler s;
    Teleport t;
    Teleport t2;

    @Override
    public void init() {
        program = "prg";
        Logger.registerObject(program, "Program");
        Logger.registerObject(this,"Skeleton");

        Logger.pushToRegisterNameStack("s", "a", "a2", "t2", "t");

        Logger.createObjectNextRegister(program);
        s = new Settler();

        Logger.createObjectNextRegister(program);
        a = new Asteroid();

        Logger.createObjectNextRegister(program);
        a2 = new Asteroid();

        Logger.createObjectNextRegister(program);
        t2 = new Teleport();
        
        Logger.createObjectNextRegister(program);
        t = new Teleport();

        Logger.callObject(program, s, "acceptTeleport", t);
        s.acceptTeleport(t);
        
        Logger.callObject(program, t, "setPair", t2);
        t.setPair(t2);
        
        Logger.callObject(program, t2, "setPair", t);
        t2.setPair(t);

        Logger.callObject(program, a, "acceptWorker", s);
        a.acceptWorker(s);
        
        Logger.callObject(program, t2, "place", a2);
        t2.place(a2);
    }

    @Override
    public void run() {
        Logger.callObject(this,s,"die");
        s.die();
    }

    @Override
    public String toString() {
        return "Not Placed Teleport Gets Destroyed";
    }
}
