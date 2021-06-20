package tau.asteroidgame.Util.usecases;

import tau.asteroidgame.*;
import tau.asteroidgame.Util.Logger;
import tau.asteroidgame.Util.UseCase;

public class SettlerCraftsTeleportSuccessfully implements UseCase {
    private Settler s;
    private TeleportRecipe rcp;

    @Override
    public void init() {
        Logger.registerObject(this, "Program");
        Logger.pushToRegisterNameStack("s","inv", "uranium", "ice", "iron1","iron2","rpc","telResReg","t1","t2");

        Logger.createObjectNextRegister(this);
        s = new Settler();
        Logger.createObjectNextRegister(this);
        Inventory inv = new Inventory();


        Logger.createObjectNextRegister(this);
        Uranium uranium = new Uranium();

        Logger.createObjectNextRegister(this);
        Ice ice = new Ice();

        Logger.createObjectNextRegister(this);
        Iron iron = new Iron();

        Logger.createObjectNextRegister(this);
        Iron iron2 = new Iron();

        Logger.createObjectNextRegister(this);
        rcp = new TeleportRecipe();

        Logger.callObject(this,s,"setInventory",inv);
        s.setInventory(inv);

        Logger.callObject(this,inv,"addMaterial",uranium);
        inv.addMaterial(uranium);
        Logger.callObject(this,inv,"addMaterial",ice);
        inv.addMaterial(ice);
        Logger.callObject(this,inv,"addMaterial",iron);
        inv.addMaterial(iron);
        Logger.callObject(this,inv,"addMaterial",iron2);
        inv.addMaterial(iron2);
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
        return "Settler Crafts Teleport Successfully";
    }
}
