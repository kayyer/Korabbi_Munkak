package tau.asteroidgame;

/**
 * A vízjég nyersanyag osztálya.
 */
public class Ice extends Material {
    /**
     * Beiratja magát a paraméterként kapott ResourceRegistry-be, mint Ice típusú Nyersanyag.
     * @param resReg A ResourceRegistry amibe beírja magát a jég. 
     */
    @Override
    public void giveSignature(ResourceRegistry resReg) {
        resReg.register(this);
    }

    /**
     * Akkor hívandó meg, amikor egy teljesen átfúrt napközeli aszteroida magjában van.
     * Ilyen esetben az Ice objektumok eltávolítják magukat (elpárolognak)
     * @param a Az aszteroida, aminben a nyersanyag található
     */
    @Override
    public void exposed(Asteroid a) {
    	System.out.println("Ice melted");
        a.removeMaterial();
    }
    
    @Override
    public String toString() {
    	return "ice";
    }
}
