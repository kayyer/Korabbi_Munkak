package tau.asteroidgame.Util.usecases;
import tau.asteroidgame.*;
import tau.asteroidgame.Util.*;

public class RobotDrillsToCoalCore implements UseCase {
    Robot r;
    Asteroid a;
    Coal c;
    String program;


    @Override
    public void init(){
        program = "prg";
        Logger.registerObject(program, "Program");
        Logger.registerObject(this, "Skeleton");
        Logger.pushToRegisterNameStack("r", "a", "core");
        Logger.createObjectNextRegister(program);
        r = new Robot();
        Logger.createObjectNextRegister(program);
        a = new Asteroid();
        Logger.createObjectNextRegister(program);
        c = new Coal();
        Logger.callObject(program, a, "acceptWorker", r);
        a.acceptWorker(r);
        Logger.callObject(program,a,"setCore",c);
        a.setCore(c);


    }
    @Override
    public void run(){
        Logger.callObject(this,r,"drill");
        r.drill();
    }
    @Override
    public String toString(){
        return "Robot Drills To Coal Core";
    }
}
