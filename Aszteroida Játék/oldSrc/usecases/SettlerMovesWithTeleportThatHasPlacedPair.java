package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerMovesWithTeleportThatHasPlacedPair implements UseCase {
    Settler s;
    Teleport dest;
    Teleport t2;
    Asteroid a;
    Asteroid a2;
    @Override
    public void init(){
    	
        Logger.clear();
        Logger.registerObject(this,"Skeleton");
        String program = "prg";
        Logger.registerObject(program, "Program");
        
        Logger.pushToRegisterNameStack("s","a","dest","t2","a2");
        Logger.createObjectNextRegister(program);
        s = new Settler();
        Logger.createObjectNextRegister(program);
        a = new Asteroid();
        Logger.createObjectNextRegister(program);
        dest = new Teleport();
        Logger.createObjectNextRegister(program);
        t2 =  new Teleport();
        Logger.createObjectNextRegister(program);
        a2 = new Asteroid();
        Logger.callObject(program, a,"acceptWorker",s);
        a.acceptWorker(s);

        Logger.callObject(program,dest,"setPair",t2);
        dest.setPair(t2);
        Logger.callObject(program,t2,"setPair",dest);
        t2.setPair(dest);
        Logger.callObject(program,dest,"place",a);
        dest.place(a);
        Logger.callObject(program,t2,"place",a2);
        t2.place(a2);


    }

    @Override
    public void run(){
        Logger.callObject(this,dest,"acceptWorker",s);
        dest.acceptWorker(s);
    }
    @Override
    public String toString()
    {
       return "Settler Moves With Teleport That Has Placed Pair";
    }
}
