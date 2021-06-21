package tau.asteroidgame.Util.usecases;
import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class RobotDrillsToIronCore implements UseCase {
    Robot r;
    Asteroid a;
    Iron i;
    String program;
    
    @Override
    public void init() {
        program = "prg";
        Logger.registerObject(program, "Program");
        Logger.registerObject(this, "Skeleton");
        Logger.pushToRegisterNameStack("r", "a", "core");
        Logger.createObjectNextRegister(program);
        r = new Robot();
        Logger.createObjectNextRegister(program);
        a = new Asteroid();
        Logger.createObjectNextRegister(program);
        i = new Iron();
        Logger.callObject(program, a, "acceptWorker", r);
        a.acceptWorker(r);
        Logger.callObject(program,a,"setCore",i);
        a.setCore(i);
    }

	@Override
	public void run() {
        Logger.callObject(this,r,"drill");
        r.drill();
	}
	
	public String toString(){
		return "Robot Drills To Iron Core";
	}
}
