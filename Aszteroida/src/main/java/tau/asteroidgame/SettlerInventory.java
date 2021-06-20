package tau.asteroidgame;


/**
 * A telepes által gyűjtött nyersanyagokat tárolja.
 * A tároló felelőssége az anyagok tárolása, kérésre anyag felvétele és eltávolítása.
 * Egy leltár egy telepeshez tartozik, a telepes halálakor a leltár is törlésre kerül minden elemével együtt. 
 *
 */
public class SettlerInventory extends Inventory{
	/**
	 * Igazzal tér vissza mindaddig, amíg nincs 10 elem a leltárban.
     * @return Igaz logikai érték, amíg nincs benne 10 elem, egyébként hamis
	 */
    @Override
    public boolean isFull(){
        return inventory.size() >=10;
    }
}
