package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.AsteroidField;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerHitBySunstorm implements UseCase {
    Settler s;
    Asteroid a;
    AsteroidField af;
    String program;


    @Override
    public void init() {
        Logger.clear();

        program = "prg";
        Logger.registerObject(program, "Program");
        Logger.registerObject(this,"Skeleton");

        Logger.pushToRegisterNameStack("af", "s", "a");

        Logger.createObjectNextRegister(program);
        af = new AsteroidField();

        Logger.createObjectNextRegister(program);
        s = new Settler();

        Logger.createObjectNextRegister(program);
        a = new Asteroid();



        Logger.callObject(program, a, "acceptWorker", s);
        a.acceptWorker(s);

        Logger.callObject(program, a ,"setLayer", 1);
        a.setLayer(1);

        Logger.callObject(program, af, "addAsteroid", a);
        af.addAsteroid(a);




    }

    @Override
    public void run() {
        Logger.callObject(this,af,"induceSolarStorm");
        af.induceSolarStorm();
    }

    @Override
    public String toString() {
        return "Settler Hit By Sunstorm";
    }
}
