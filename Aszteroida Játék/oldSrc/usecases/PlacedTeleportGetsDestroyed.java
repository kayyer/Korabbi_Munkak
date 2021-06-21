package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class PlacedTeleportGetsDestroyed implements UseCase {
    private Settler s;
    private Asteroid a,a2;
    private Teleport t,t2;

    @Override
    public void init() {
        Logger.registerObject(this, "Skeleton");
        String program = "prg"; Logger.registerObject(program, "Program");
        Logger.pushToRegisterNameStack("s", "a2","a", "t", "t2");

        Logger.createObjectNextRegister(program);
        s= new Settler();

        Logger.createObjectNextRegister(program);
        a2 = new Asteroid();

        Logger.createObjectNextRegister(program);
        a = new Asteroid();

        Logger.createObjectNextRegister(program);
        t = new Teleport();

        Logger.createObjectNextRegister(program);
        t2 = new Teleport();

        Logger.callObject(program,s,"acceptTeleport",t2);
        s.acceptTeleport(t2);

        Logger.callObject(program,t2,"setPair",t);
        t2.setPair(t);

        Logger.callObject(program,t,"setPair",t2);
        t.setPair(t2);

        Logger.callObject(program,a2,"acceptWorker",s);
        a2.acceptWorker(s);

        Logger.callObject(program,t,"place",a);
        t.place(a);
    }

    @Override
    public void run() {
        Logger.callObject(this,a,"explode");
        a.explode();
    }

    @Override
    public String toString() {
        return "Placed Teleport Gets Destroyed";
    }
}
