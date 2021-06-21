package tau.asteroidgame;

import java.util.List;
import java.util.ArrayList;

/**
 * Bármely típusú nyersanyag bejegyzésére képes, így vissza tudja adni, hogy van e
 * minden típusból legalább 3 nyersanyag bejegyezve. (bázisépítés feltétele)
 */
public class BaseResourceRegistry extends ResourceRegistry {

    private List<Coal> coals = new ArrayList<>();
    private List<Ice> ices = new ArrayList<>();
    private List<Iron> irons = new ArrayList<>();
    private List<Uranium> uraniums = new ArrayList<>();


    /**
     * A BaseResourceRegistry egyetlen publikus konstruktora.
     * Beállítja a szükséges nyersanyagok listáját.
     */
    public BaseResourceRegistry() {
        requiredMaterials.put(MaterialName.COAL,3);
        requiredMaterials.put(MaterialName.ICE,3);
        requiredMaterials.put(MaterialName.IRON,3);
        requiredMaterials.put(MaterialName.URANIUM,3);
    }

    /**
     * Bejegyzi a paraméterként kapott Coal anyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Coal nyersanyag
     */
    @Override
    public void register(Coal m) {
        if(coals.size() < 3)
            coals.add(m);
        super.register(m);
    }

    /**
     * Bejegyzi a paraméterként kapott Ice nyersanyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Ice nyersanyag
     */
    @Override
    public void register(Ice m) {
        if(ices.size() < 3)
            ices.add(m);
        super.register(m);
    }

    /**
     * Bejegyzi a paraméterként kapott Iron nyersanyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Iron nyersanyag
     */
    @Override
    public void register(Iron m) {
        if(irons.size() < 3)
            irons.add(m);
        super.register(m);
    }

    /**
     * Bejegyzi a paraméterként kapott Uranium anyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Uranium nyersanyag
     */
    @Override
    public void register(Uranium m) {
        if(uraniums.size() < 3)
            uraniums.add(m);
        super.register(m);
    }

    /**
     * Nem csinál semmit, hisz ennek hívásakor már bizonyosan megnyerődött a játék.
     */
    @Override
    public void removeRequiredMaterials(Inventory i) {
    	;
    }


}
