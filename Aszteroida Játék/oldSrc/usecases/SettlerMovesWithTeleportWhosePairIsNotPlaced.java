package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerMovesWithTeleportWhosePairIsNotPlaced implements UseCase {
    Settler s;
    Teleport dest;
    Teleport t2;
    Asteroid a;
    @Override
    public void init(){
    	String program = "prg";
        Logger.registerObject(program,"Program");
        Logger.registerObject(this,"Skeleton");
        Logger.pushToRegisterNameStack("s","a","dest","t2");
        Logger.createObjectNextRegister(program);
        s = new Settler();
        Logger.createObjectNextRegister(program);
        a = new Asteroid();
        Logger.createObjectNextRegister(program);
        dest = new Teleport();
        Logger.createObjectNextRegister(program);
        t2 = new Teleport();
        
        Logger.callObject(program,a,"acceptWorker",s);
        a.acceptWorker(s);
        Logger.callObject(program,dest,"place",a);
        dest.place(a);
        Logger.callObject(program,dest,"setPair",t2);
        dest.setPair(t2);
        Logger.callObject(program,t2,"setPair",dest);
        t2.setPair(dest);

    }

    @Override
    public void run(){
        Logger.callObject(this, dest,"acceptWorker",s);
        dest.acceptWorker(s);
    }
    @Override
    public String toString()
    {
        return "Settler Moves With Teleport Whose Pair Is Not Placed";
    }
}
