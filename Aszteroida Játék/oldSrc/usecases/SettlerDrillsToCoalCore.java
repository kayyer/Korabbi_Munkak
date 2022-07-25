package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;
import tau.asteroidgame.*;
public class SettlerDrillsToCoalCore implements UseCase {
    Asteroid a;
    Settler s;
    Coal c;
    String program;

    @Override
    public void init(){
        Logger.clear();
        program = "prg";
        Logger.registerObject(program, "Program");
        Logger.registerObject(this, "Skeleton");
        Logger.pushToRegisterNameStack("s", "a", "core");
        Logger.createObjectNextRegister(program);
        s = new Settler();
        Logger.createObjectNextRegister(program);
        a = new Asteroid();
        Logger.createObjectNextRegister(program);
        c = new Coal();
        Logger.callObject(program, a, "acceptWorker", s);
        a.acceptWorker(s);
        Logger.callObject(program,a,"setCore",c);
        a.setCore(c);


    }

    @Override
    public void run() {
        Logger.callObject(this, s, "drill");
        s.drill();
    }
    @Override
    public String toString(){
        return "Settler Drills To Coal Core";
    }

}
