package tau.asteroidgame;


/**
 * A vas nyersanyag osztálya.
 */
public class Iron extends Material {

	
	/**
	 * Beiratja magát a paraméterként kapott ResourceRegistry-be, mint Iron típusú 
	 * nyersanyag. 
	 */
    @Override
    public void giveSignature(ResourceRegistry resReg) {
        resReg.register(this);
    }
    
    @Override
    public String toString() {
    	return "iron";
    }
}
