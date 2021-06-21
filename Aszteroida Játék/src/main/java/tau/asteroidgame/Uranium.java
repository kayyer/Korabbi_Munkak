package tau.asteroidgame;


import static tau.asteroidgame.Util.CommandDatabase.getAsteroidID;

/**
 * Az uránium nyersanyag osztálya.
 */
public class Uranium extends Material {
    private int exposure = 0;
    


    /**
     * A teljesen átfúrt napközeli aszteroida magjában hívódik meg a metódus, és ekkor, felrobbantja az aszteroidát.
     * @param a Az aszteroida, aminben a nyersanyag található
     */
    @Override
    public void exposed(Asteroid a) {
    	System.out.println("Uranium exposed");
        //Csak a harmadik expozíció után robban.
        exposure++;
        if(exposure == 3){
            System.out.println("asteroid " + getAsteroidID(a) + " exploded");
            a.explode();
        }
    }

    public void setExposure(int num){
        if((0 <= num) && (num < 3)){
            exposure = num;
        }
    }

    @Override
    public void giveSignature(ResourceRegistry resReg) {
        resReg.register(this);
    }
    
    @Override
    public String toString() {
    	return "uranium" + exposure;
    }
}
