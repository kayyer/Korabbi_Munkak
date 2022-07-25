package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Robot;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class RobotMovesWithTeleportWhosePairIsNotPlaced implements UseCase{
    Robot r;
    Settler s;
    Teleport dest;
    Teleport t2;
    Asteroid a;
    String program;
    
	@Override
	public void init() {
        program = "prg";
        Logger.registerObject(program,"Program");
        Logger.registerObject(this,"Skeleton");
        Logger.pushToRegisterNameStack("r","a","dest","t2");
        Logger.createObjectNextRegister(program);
        r = new Robot();
        Logger.createObjectNextRegister(program);
        a = new Asteroid();
        Logger.createObjectNextRegister(program);
        dest = new Teleport();
        Logger.createObjectNextRegister(program);
        t2 = new Teleport();
        
        Logger.callObject(program,a,"acceptWorker",r);
        a.acceptWorker(r);
        Logger.callObject(program,dest,"place",a);
        dest.place(a);
        Logger.callObject(program,dest,"setPair",t2);
        dest.setPair(t2);
        Logger.callObject(program,t2,"setPair",dest);
        t2.setPair(dest);
	}
	@Override
	public void run() {
        Logger.callObject(program,r,"move",dest);
        r.move(dest);
	}
    @Override
    public String toString()
    {
        return "Robot Moves With Teleport Whose Pair Is Not Placed";
    }
}
