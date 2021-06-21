package tau.asteroidgame;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public abstract class Inventory {
    protected final List<Material> inventory = new ArrayList<>();


    /**
     * Visszaadja, hogy a leltárban található anyagok darabszáma elérte-e, a maximumot.
     * @return Logikai érték az alapján,hogy leltárban található anyagok darabszáma elérte-e, a maximumot.
     */
    public abstract boolean isFull();

    /**
     Hozzáad egy anyagot a leltárhoz. Visszaadja, hogy sikerült e a hozzáadás.
     (ha isFull() hamis, valójában hozzáadja és igazzal tér vissza, egyébként nem csinál semmit és hamissal tér vissza) 
	*/
    public boolean addMaterial(Material m) {
    	if (!isFull()) {
    		inventory.add(m);
    		return true;
    	}
    	return false;
    }


    /**
     * Eltávolít egy anyagot a leltárból.
     * @param m Az eltávolítandó nyersanyag
     */
    public void removeMaterial(Material m) {
        inventory.remove(m);

    }

    /**
     * Visszaadja a leltárban tárolt nyersanyagokat
     * @return A leltárban található nyersanyagok listája
     */
    public List<Material> getMaterials(){
        return Collections.unmodifiableList(inventory);
    }
}
