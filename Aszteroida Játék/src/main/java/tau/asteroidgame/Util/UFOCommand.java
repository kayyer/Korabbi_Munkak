package tau.asteroidgame.Util;

import tau.asteroidgame.*;

import java.util.List;

/**
 * Az ufo parancsot megvalósító osztáy
 */
public class UFOCommand implements ICommand{
    /**
     * Kapott paraméterek alapján végre hatja utasításait az UFOra vonatkozó utasításokat.
     */
    @Override
    public void run(String[] param){
    	if (param.length < 2) {
			System.out.println("ERROR: Invalid Parameter Format");
			return;
		}
        if(param[1].equals("create") && CommandDatabase.getAsteroid(param[2]) != null)
        {
            UnidentifiedFlyingObject ufo = new UnidentifiedFlyingObject(CommandDatabase.getAsteroid(param[2]));
            System.out.println(CommandDatabase.registerUFO(ufo));
        }
        else if(CommandDatabase.getUFO(param[1]) != null)
        {
            UnidentifiedFlyingObject ufo = CommandDatabase.getUFO((param[1]));
            switch(param[2]){
                case "inventory":
                    if(param[3].equals("add"))
                    {
                        switch(param[4]){
                            case "ice" :
                                ufo.acceptMaterial(new Ice());
                                break;
                            case "iron":
                                ufo.acceptMaterial(new Iron());
                                break;
                            case "coal":
                                ufo.acceptMaterial(new Coal());
                                break;
                            case "uranium":
                            case "uranium0":
                                ufo.acceptMaterial(new Uranium());
                                break;
                            case "uranium1":
                                Uranium u = new Uranium();
                                u.setExposure(1);
                                ufo.acceptMaterial(u);
                                break;
                            case "uranium2":
                                Uranium u2 = new Uranium();
                                u2.setExposure(2);
                                ufo.acceptMaterial(u2);
                                break;
                            default:
                                System.out.println("ERROR: Invalid parameter");
                        }
                    }
                    break;
                case "mine":
                	/*
                    ufo.getAsteroid().setLayer(0);
                    int old = ufo.getInventory().getMaterials().size();
                    ufo.step();
                    if(old < ufo.getInventory().getMaterials().size())
                    {
                        List<Material> l = ufo.getInventory().getMaterials();
                        System.out.println(l.get(l.size() - 1 ).toString());
                    }
                    else {
                        if(ufo.getAsteroid().getCore() == null){
                            System.out.println("Mining failed: Empty core!");
                        }else
                            System.out.println("Mining failed");

                    }
                    */
                	ufo.mine();
                    break;

                case "move":
                   if(CommandDatabase.getAsteroid(param[3]) != null)
                        ufo.move(CommandDatabase.getAsteroid(param[3]));
                   else if(CommandDatabase.getTeleport(param[3])!= null)
                       ufo.move(CommandDatabase.getTeleport(param[3]));
                   else
                       System.out.println("ERROR: Invalid parameter");
                   break;
                case "die":
                    ufo.die();
                    //System.out.println("ufo " + param[1] + " die");
                    break;
                case "explode":
                    ufo.explode();
                    //System.out.println("ufo " + param[1] + " explode");
                    //System.out.println("ufo " + param[1] + " die");
                    break;
                case "step":
                	/*
                    Asteroid oldA = ufo.getAsteroid();
                    int oldI = ufo.getInventory().getMaterials().size();
                    ufo.step();

                    if(oldI < ufo.getInventory().getMaterials().size()) {
                        List<Material> l = ufo.getInventory().getMaterials();
                        System.out.println(l.get(l.size() - 1).toString());
                    }
                    */
                	ufo.step();
                    break;
                default:
                    System.out.println("ERROR: Invalid parameter");
            }
        }
        else {
            System.out.println("ERROR: Invalid parameter");
        }

    }
}
