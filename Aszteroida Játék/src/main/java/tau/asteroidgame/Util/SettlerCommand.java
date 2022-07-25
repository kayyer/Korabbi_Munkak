package tau.asteroidgame.Util;

import tau.asteroidgame.*;

import java.util.List;

/**
 * Az settler parancsot megvalósító osztáy
 */
public class SettlerCommand implements ICommand {

    /**
     * Kapott paraméterek alapján végre hatja a Settler-re vonatkozó utasításokat.
     */
    @Override
    public void run(String[] param){
    	if (param.length < 2) {
			System.out.println("ERROR: Invalid Parameter Format");
			return;
		}
        if(param[1].equals("create") && CommandDatabase.getAsteroid(param[2]) != null){
            Settler s = new Settler(CommandDatabase.getAsteroid(param[2]));
            System.out.println(CommandDatabase.registerSettler(s));
        }
        else if (CommandDatabase.getSettler(param[1]) != null)
        {
            Settler s = CommandDatabase.getSettler(param[1]);
            boolean failed = true;
            switch(param[2]){
                case "inventory":
                    if(param[3].equals("add")){
                        switch(param[4]){
                            case "ice" :
                                if(s.acceptMaterial(new Ice()))
                                    failed = false;
                                break;
                            case "iron":
                                if(s.acceptMaterial(new Iron()))
                                    failed = false;
                                break;
                            case "coal":
                                if(s.acceptMaterial(new Coal()))
                                    failed = false;
                                break;
                            case "uranium":
                            case "uranium0":
                                if(s.acceptMaterial(new Uranium()))
                                    failed = false;
                                break;
                            case "uranium1":
                                Uranium u = new Uranium();
                                u.exposed(new Asteroid(0));
                                if(s.acceptMaterial(u))
                                    failed = false;
                                break;
                            case "uranium2":
                                Uranium u2 = new Uranium();
                                u2.exposed(new Asteroid(0));
                                u2.exposed(new Asteroid(0));
                                if(s.acceptMaterial(u2))
                                    failed = false;
                                break;
                            default:
                                System.out.println("ERROR: Invalid parameter");
                                failed = false;
                        }
                        if(failed) {
                            System.out.println("ERROR: Inventory full");
                        }
                    }
                    break;
                case "mine":
                    /*if(s.mine()) {
                        List<Material> l = s.getInventory().getMaterials();
                        System.out.println(l.get(l.size() - 1 ).toString());
                    }
                    else
                        System.out.println("Mining failed!");
                    */
                	s.mine();
                    break;
                case "drill":
                    if(s.getAsteroid().getRemainingLayer() == 0) {
                        System.out.println("Drilling failed: Asteroid fully drilled!");
                    }
                    else
                    {
                        s.drill();
                    }
                    break;
                case "craft" :
                    failed = true;
                    if(param[3].equals("teleport"))
                    {
                        TeleportRecipe tr = new TeleportRecipe();
                        if(s.craft(tr))
                            failed = false;
                    }
                    else if(param[3].equals("robot"))
                    {
                        RobotRecipe rr = new RobotRecipe();
                        if(s.craft(rr))
                            failed = false;
                    }
                    else{
                        System.out.println("ERROR: Invalid parameters");
                        failed = false;
                    }
                    if(failed)
                            System.out.println("Crafting failed: Not enough materials or holds more than 1 teleports.");
                    break;
                case "move" :
                    if(CommandDatabase.getAsteroid(param[3]) != null)
                        s.move(CommandDatabase.getAsteroid(param[3]));
                    else if(CommandDatabase.getTeleport(param[3])!= null)
                        s.move(CommandDatabase.getTeleport(param[3]));
                    else
                        System.out.println("ERROR: Invalid parameter");
                    break;
                case "placeTeleport":
                    if(!s.placeTeleport())
                        System.out.println("Teleport not found");
                    break;
                case "die" :
                    s.die();
                    break;
                case "explode":
                    s.explode();
                    break;
                case "placeMaterial":
                    int ind = Integer.parseInt(param[3]);
                    if(ind >= 0 && ind < s.getInventory().getMaterials().size()) {
                        if (!s.placeMaterial(s.getInventory().getMaterials().get(ind)))
                        {
                            if(!s.getAsteroid().isExposed())
                                System.out.println("Asteroid not fully drilled");
                            else if(s.getAsteroid().getCore() != null)
                                System.out.println("Asteroid not empty");
                        }
                    }
                    break;
                default :
                    System.out.println("ERROR : Invalid Parameters");


            }
        }
    }

}
