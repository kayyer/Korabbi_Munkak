package tau.asteroidgame;


/**
 * Teljesen üres, nem rakható bele semmi, és mindig azt jelzi, hogy tele van. (Robot, UFÓ 
 * használja) 
 */
public class BlankInventory extends Inventory{
	/**
	 * Mindig igazzal tér vissza (nincs benne hely egyáltalán)
     * @return Hamis logikai érték
	 */
    @Override
    public boolean isFull() {
        return true;
    }
}
