package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class RobotDrillsToUraniumCore implements UseCase {
    String program;
    Robot r;
    Asteroid a;
    Uranium core;

    @Override
    public void init() {
        program = "prg";
        Logger.registerObject(program, "Program");
        Logger.registerObject(this,"Skeleton");

        Logger.pushToRegisterNameStack("r", "core", "a");

        Logger.createObjectNextRegister(program);
        r = new Robot();

        Logger.createObjectNextRegister(program);
        core = new Uranium();

        Logger.createObjectNextRegister(program);
        a = new Asteroid();

        Logger.callObject(program, a, "setCore", core);
        a.setCore(core);

        Logger.callObject(program, a, "acceptWorker", r);
        a.acceptWorker(r);

    }

    @Override
    public void run() {
        Logger.callObject(this,r,"drill");
        r.drill();
    }

    @Override
    public String toString() {
        return "Robot Drills To Uranium Core";
    }
}
