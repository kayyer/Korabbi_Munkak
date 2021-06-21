package tau.asteroidgame.Util;

import tau.asteroidgame.*;

import static tau.asteroidgame.Util.CommandDatabase.*;

/**
 * Az asteroid parancsot megvalósító osztáy
 */
public class AsteroidCommand implements ICommand {
    /**
     * Kapott paraméterek alapján végre hatja utasításait
     */
    @Override
    public void run(String[] param) {
        if (param.length < 2) {
            System.out.println("ERROR: Invalid Parameter Format");
            return;
        }

        boolean param3Flag = false;
        if (param[1].equals("create")) {
            if (getBoundAsteroidField() == null) {
                System.out.println("ERROR: No AsteroidFieldBound");
            } else if (param.length > 2) {
                Asteroid a = new Asteroid(Integer.parseInt(param[2]));
                if (param.length > 3) {
                    switch (param[3]) {
                        case "iron":
                            a.setCore(new Iron());
                            break;
                        case "ice":
                            a.setCore(new Ice());
                            break;
                        case "coal":
                            a.setCore(new Coal());
                            break;
                        case "uranium0":
                            Uranium u0 = new Uranium();
                            u0.setExposure(0);
                            a.setCore(u0);
                            break;
                        case "uranium1":
                            Uranium u1 = new Uranium();
                            u1.setExposure(1);
                            a.setCore(u1);
                            break;
                        case "uranium2":
                            Uranium u2 = new Uranium();
                            u2.setExposure(2);
                            a.setCore(u2);
                            break;
                        case "none":
                            break;
                        default:
                            System.out.println("Error: Invalid parameter!");
                            break;
                    }

                    if ((param.length > 4)) {
                        a.setLayersDrilled(Integer.parseInt(param[4]));
                        if (param.length > 5) {
                            a.setSunDistance(Integer.parseInt(param[5]));
                        }
                    }
                }
                System.out.println(registerAsteroid(a));
            }


    

        } else if (

                getAsteroid(param[1]) != null && param.length > 2) {

            Asteroid ast = getAsteroid(param[1]);

            switch (param[2]) {
                case "setNeighbors":
                case "setNeighbor":
                    for (int i = 3; i < param.length; i++) {
                        ast.addNeighbor(getAsteroid(param[i]));
                    }
                    break;
                case "explode":
                    ast.explode();
                    break;
                case "accept":
                    for (int i = 3; i < param.length; i++) {
                        ast.acceptWorker(getWorker(param[i]));
                    }
                    break;
                case "setMaterial":
                    switch (param[3]) {
                        case "iron":
                            ast.setCore(new Iron());
                            break;
                        case "ice":
                            ast.setCore(new Ice());
                            break;
                        case "coal":
                            ast.setCore(new Coal());
                            break;
                        case "uranium0":
                            Uranium u0 = new Uranium();
                            u0.setExposure(0);
                            ast.setCore(u0);
                            break;
                        case "uranium1":
                            Uranium u1 = new Uranium();
                            u1.setExposure(1);
                            ast.setCore(u1);
                            break;
                        case "uranium2":
                            Uranium u2 = new Uranium();
                            u2.setExposure(2);
                            ast.setCore(u2);
                            break;
                        default:
                            System.out.println("ERROR: Invalid Parameter Format");
                            break;
                    }
                    break;
                case "setLayer":
                    if (param.length > 3) {
                        ast.setLayer(Integer.parseInt(param[3]));
                    } else {
                        System.out.println("ERROR: Invalid Parameter Format");
                    }
                    break;
                case "setDrilledLayers":
                    if (Integer.parseInt(param[3]) > ast.getLayer()) {
                        System.out.println("ERROR: Invalid Parameter Format");
                    } else {
                        ast.setLayersDrilled(Integer.parseInt(param[3]));
                    }
                    break;
                case "setSunclose":
                    if (param.length > 3) {
                        ast.setSunclose(Boolean.parseBoolean(param[3]));
                    } else {
                        System.out.println("ERROR: Invalid Parameter Format");
                    }
                    break;
                case "setSunDistance":
                    if (param.length > 3) {
                        ast.setSunDistance(Integer.parseInt(param[3]));
                    } else {
                        System.out.println("ERROR: Invalid Parameter Format");
                    }

                    break;
                default:
                    System.out.println("ERROR: Invalid Parameter Format");
                    break;
            }
        } else {
            System.out.println("ERROR: Invalid ID");
        }
    }

}
