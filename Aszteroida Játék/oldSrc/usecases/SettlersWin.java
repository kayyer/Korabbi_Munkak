package tau.asteroidgame.Util.usecases;

import org.checkerframework.checker.units.qual.A;
import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlersWin implements UseCase {

    private Settler s1,s2;
    private Asteroid a1,a2;
    private AsteroidField af;
    private Inventory i1,i2;
    private Coal c1,c2,c3;
    private Ice ic1,ic2,ic3;
    private Iron ir1,ir2,ir3;
    private Uranium u1,u2,u3;

    @Override
    public void init() {
    	String program = ""; Logger.registerObject(program, "Program");
        Logger.registerObject(this,"Skeleton");
        Logger.pushToRegisterNameStack("i1","s1","a1","ur1","ur2","ur3","ic1","ic2","ic3","ir1","ir2","ir3",
                "c1","c2","c3","i2","a2","s2","af");
        Logger.createObjectNextRegister(program);
        i1 = new Inventory();

        Logger.createObjectNextRegister(program);
        s1 = new Settler();

        Logger.createObjectNextRegister(program);
        a1 = new Asteroid();

        Logger.callObject(program,s1,"setInventory",i1);
        s1.setInventory(i1);

        Logger.callObject(program,s1,"setAsteroid",a1);
        a1.acceptWorker(s1);

        Logger.createObjectNextRegister(program);
        u1 = new Uranium();
        Logger.createObjectNextRegister(program);
        u2 = new Uranium();
        Logger.createObjectNextRegister(program);
        u3 = new Uranium();

        Logger.createObjectNextRegister(program);
        ic1 = new Ice();
        Logger.createObjectNextRegister(program);
        ic2 = new Ice();
        Logger.createObjectNextRegister(program);
        ic3 = new Ice();

        Logger.createObjectNextRegister(program);
        ir1 = new Iron();
        Logger.createObjectNextRegister(program);
        ir2 = new Iron();
        Logger.createObjectNextRegister(program);
        ir3 = new Iron();

        Logger.callObject(program,i1,"addMaterial",u1);
        i1.addMaterial(u1);
        Logger.callObject(program,i1,"addMaterial",u2);
        i1.addMaterial(u2);
        Logger.callObject(program,i1,"addMaterial",u3);
        i1.addMaterial(u3);
        Logger.callObject(program,i1,"addMaterial",ic1);
        i1.addMaterial(ic1);
        Logger.callObject(program,i1,"addMaterial",ic2);
        i1.addMaterial(ic2);
        Logger.callObject(program,i1,"addMaterial",ic3);
        i1.addMaterial(ic3);
        Logger.callObject(program,i1,"addMaterial",ir1);
        i1.addMaterial(ir1);
        Logger.callObject(program,i1,"addMaterial",ir2);
        i1.addMaterial(ir2);
        Logger.callObject(program,i1,"addMaterial",ir3);
        i1.addMaterial(ir3);

        Logger.createObjectNextRegister(program);
        c1 = new Coal();
        Logger.createObjectNextRegister(program);
        c2 = new Coal();
        Logger.createObjectNextRegister(program);
        c3 = new Coal();

        Logger.createObjectNextRegister(program);
        i2 = new Inventory();

        Logger.callObject(program,i2,"addMaterial",c1);
        i2.addMaterial(c1);
        Logger.callObject(program,i2,"addMaterial",c2);
        i2.addMaterial(c2);
        Logger.callObject(program,i2,"addMaterial",c3);
        i2.addMaterial(c3);

        Logger.createObjectNextRegister(program);
        a2 = new Asteroid();

        Logger.createObjectNextRegister(program);
        s2 = new Settler();

        Logger.callObject(program,s2,"setInventory",i2);
        s2.setInventory(i2);


        Logger.callObject(program,a2,"acceptWorker",s2);
        a2.acceptWorker(s2);

        Logger.createObjectNextRegister(program);
        af = new AsteroidField();

        Logger.callObject(program,af,"addAsteroid",a1);
        af.addAsteroid(a1);
        Logger.callObject(program,af,"addAsteroid",a2);
        af.addAsteroid(a2);
    }

    @Override
    public void run() {
        Logger.callObject(this,s2,"move",a1);
        s2.move(a1);
    }

    @Override
    public String toString() {
        return "Settlers Win";
    }
}
