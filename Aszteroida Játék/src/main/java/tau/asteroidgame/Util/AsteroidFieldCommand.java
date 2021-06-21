package tau.asteroidgame.Util;

import tau.asteroidgame.AsteroidField;

/**
 * Az asteroidfield parancsot megvalósító osztáy
 */
public class AsteroidFieldCommand implements ICommand {
    /**
     * Kapott paraméterek alapján végre hatja utasításait az AsteroidField-re vonatkozó utasításokat esetleges hibajelzéssel.
     */
    @Override
    public void run(String[] param) {
    	if (param.length < 2) {
			System.out.println("ERROR: Invalid Parameter Format");
			return;
		}
        boolean invalid = true;
        switch (param[1]) {
            case "create":
                AsteroidField af = new AsteroidField();
                System.out.println(CommandDatabase.registerAsteroidField(af));
                invalid = false;
                break;
            case "step":
                if (CommandDatabase.getBoundAsteroidField() != null) {
                    CommandDatabase.getBoundAsteroidField().step();
                    invalid = false;
                    break;
                }
        }
        if(CommandDatabase.getAsteroidField(param[1])!=null) {
            AsteroidField af = CommandDatabase.getAsteroidField(param[1]);
            CommandDatabase.bindAsteroidField(af);
            invalid = false;
        }
        if(invalid) {
            System.out.println("ERROR: Invalid parameter");
        }

}


}
