package tau.asteroidgame;

/**
 * A szén nyersanyag osztálya. 
 */
public class Coal extends Material {

    public Coal() {
    }
	/**
	 * Beiratja magát a paraméterként kapott ResourceRegistry-be, mint Coal típusú Nyersanyag 
	 * @param resReg a ResourceRegistry amibe beírja magát a szén.
	 */
    @Override
    public void giveSignature(ResourceRegistry resReg) {
        resReg.register(this);
    }
    
    @Override
    public String toString() {
    	return "coal";
    }
}
