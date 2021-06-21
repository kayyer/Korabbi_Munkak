package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerPlacesMaterialIntoEmptyCoreAsteroid implements UseCase {
    Settler s;
    Asteroid a;
    Inventory inv;
    Iron iron;

    @Override
    public void init() {
        Logger.clear();
        String program = "";

        Logger.registerObject(this,"Skeleton");
        Logger.registerObject(program, "Program");

        Logger.pushToRegisterNameStack("inv","s", "m","a");

        Logger.createObjectNextRegister(program);
        inv = new Inventory();

        Logger.createObjectNextRegister(program);
        s = new Settler();


        Logger.createObjectNextRegister(program);
        iron = new Iron();

        Logger.createObjectNextRegister(program);
        a = new Asteroid();


        Logger.callObject(program, s, "setInventory", inv);
        s.setInventory(inv);

        Logger.callObject(program, s, "acceptMaterial",iron);
        s.acceptMaterial(iron);

        Logger.callObject(program, a, "acceptWorker", s);
        a.acceptWorker(s);

    }

    @Override
    public void run() {
        Logger.callObject(this,s,"placeMaterial",iron);
        s.placeMaterial(iron);
    }

    @Override
    public String toString() {
        return "Settler Places Material Into Empty Core Asteroid";
    }
}
