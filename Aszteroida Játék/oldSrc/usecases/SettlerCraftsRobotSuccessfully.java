package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;


public class SettlerCraftsRobotSuccessfully implements UseCase {
    private Settler s;
    private Asteroid a;
    private RobotRecipe rcp;

    @Override
    public void init() {
        Logger.registerObject(this, "Program");
        Logger.pushToRegisterNameStack("a", "s","inv", "uranium", "coal", "iron","rpc","robotResReg","r");

        Logger.createObjectNextRegister(this);
        a = new Asteroid();

        Logger.createObjectNextRegister(this);
        s = new Settler();
        Logger.createObjectNextRegister(this);
        Inventory inv = new Inventory();


        Logger.createObjectNextRegister(this);
        Uranium uranium = new Uranium();

        Logger.createObjectNextRegister(this);
        Coal coal = new Coal();

        Logger.createObjectNextRegister(this);
        Iron iron = new Iron();

        Logger.createObjectNextRegister(this);
        rcp = new RobotRecipe();

        Logger.callObject(this,a,"acceptWorker",s);
        a.acceptWorker(s);

        Logger.callObject(this,s,"setInventory",inv);
        s.setInventory(inv);

        Logger.callObject(this,inv,"addMaterial",uranium);
        inv.addMaterial(uranium);
        Logger.callObject(this,inv,"addMaterial",coal);
        inv.addMaterial(coal);
        Logger.callObject(this,inv,"addMaterial",iron);
        inv.addMaterial(iron);
    }

    @Override
    public void run() {
        Logger.callObject(this,s,"craft",rcp);
        boolean res = s.craft(rcp);
        if(!res){
            System.out.println("Hiba történt!");
        }
    }

    @Override
    public String toString() {
        return "Settler Crafts Robot Successfully";
    }
}
