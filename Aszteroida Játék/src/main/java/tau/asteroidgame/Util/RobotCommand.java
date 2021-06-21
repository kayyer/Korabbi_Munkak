package tau.asteroidgame.Util;


import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Robot;

import static tau.asteroidgame.Util.CommandDatabase.*;

/**
 * A robot parancsot megvalósító osztáy
 */
public class RobotCommand implements ICommand {
    /**
     * Kapott paraméterek alapján végre hatja utasításait
     */
    @Override
    public void run(String[] param) {
        if (param.length < 2) {
            System.out.println("ERROR: Invalid Parameter Format");
            return;
        }
    	
        if (param[1].equals("create") && param.length == 3  && param[2] != null) {
            Asteroid a = getAsteroid(param[2]);
            if(a != null) {
                System.out.println(registerRobot(new Robot(a)));
            }else {
                System.out.println("ERROR: Invalid parameter");
            }
        } else if (getRobot(param[1]) != null && param.length > 2) {
            Robot rob = getRobot(param[1]);
            // drill
            if (param[2].equals("drill")) {
            	rob.drill();
            }
            // move
            else if (param[2].equals("move")) {
                if (param.length > 3) {
                    if (getTransporter(param[3]) != null) {
                        rob.move(getTransporter(param[3]));
                    } else {
                        System.out.println("ERROR: Invalid ID");
                    }

                } else {
                    System.out.println("ERROR: Invalid Parameter Format");
                }
            }
            // die
            else if (param[2].equals("die")) {
                rob.die();
            }
            // explode
            else if (param[2].equals("explode")) {
                rob.explode();
            }
            // step
            else if (param[2].equals("step")) {
                rob.step();
            }
        } else {
            System.out.println("ERROR: Invalid ID");
        }
    }
}
