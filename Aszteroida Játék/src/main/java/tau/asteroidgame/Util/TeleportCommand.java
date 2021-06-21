package tau.asteroidgame.Util;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Worker;

/**
 * Az teleport parancsot megvalósító osztáy
 */
public class TeleportCommand  implements ICommand {

    /**
     *Vegre hajtja a teleporton a paramaterekben kapott utasitasokat , vagy errort dob ha nem megfelelo parametert kap
     */
    @Override
    public void run(String[] param) {
    	if (param.length < 2) {
			System.out.println("ERROR: Invalid Parameter Format");
			return;
		}
        if (param[1].equals("create")) {
            if (param.length < 3) {
                System.out.println("ERROR: Missing parameter");
                return;
            } else if (param.length == 3) {
                Settler s = CommandDatabase.getSettler(param[2]);
                if (s == null) {
                    System.out.println("ERROR: Invalid SettlerID");
                    return;
                }
                Teleport t = new Teleport(s);
                System.out.println(CommandDatabase.registerTeleport(t) + " " + CommandDatabase.registerTeleport(t.getPair()));
            } else if (param.length == 4) {
                Asteroid a = CommandDatabase.getAsteroid(param[2]);
                if (a == null) {
                    System.out.println("ERROR: Invalid AsteroidID");
                    return;
                }
                if (CommandDatabase.getAsteroid(param[3]) == null) {
                    if (CommandDatabase.getSettler(param[3]) == null) {
                        System.out.println("ERROR: Missing AsteroidId or SettlerId");
                        return;
                    } else {
                        Settler s = CommandDatabase.getSettler(param[3]);
                        Teleport t = new Teleport(a, s);
                        System.out.println(CommandDatabase.registerTeleport(t) + " " + CommandDatabase.registerTeleport(t.getPair()));
                    }
                } else {
                    Asteroid a2 = CommandDatabase.getAsteroid(param[3]);
                    Teleport t = new Teleport(a, a2);
                    System.out.println(CommandDatabase.registerTeleport(t) + " " + CommandDatabase.registerTeleport(t.getPair()));
                    
                }
            }
        } else if (CommandDatabase.getTeleport(param[1]) != null) {
            Teleport t = CommandDatabase.getTeleport(param[1]);
            switch (param[2]) {
                case "crazy":
                    t.sufferSolarstorm(0, null);
                    //System.out.println(param[1] + " went crazy");
                    break;
                case "step":
                    t.step();
                    break;
                case "accept":
                    if (t.isPlaced() && t.getPair().isPlaced()) {
                        if (param[3] == null) {
                            System.out.println("ERROR: Missing workerID");
                            break;
                        }
                        boolean success = false;
                        for (int i = 3; i < param.length; i++) {
                        	Worker w = CommandDatabase.getWorker(param[i]);
                            if (w != null) {
                            	success = t.acceptWorker(w);
                            }
                            else {
                                System.out.println("ERROR: Invalid workerID");
                            }
                        }
                        if (success) {
                            System.out.println("Teleported to " + CommandDatabase.getTeleportID(t.getPair()));
                        }
                    } else {
                        System.out.println("Teleport failed: One or more component is not placed");
                    }
                    break;
                case "destroy":
                    t.destroy();
                    break;
                default:
                    System.out.println("ERROR: Invalid parameters");

            }
        } else {
            System.out.println("ERROR: Invalid parameters");
        }
    }
}
