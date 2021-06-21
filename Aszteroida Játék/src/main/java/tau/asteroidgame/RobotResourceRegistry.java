package tau.asteroidgame;


/**
 * Bármely típusú anyag bejegyzés tárolására képes, így vissza tudja adni, hogy valamennyi
 * típusból van-e elég nyersanyag bejegyezve a robot létrehozására (1 Iron, 1 Coal, 1
 * Uranium).
 */
public class RobotResourceRegistry extends ResourceRegistry {

    private Iron iron = null;
    private Coal coal = null;
    private Uranium uranium = null;

    /**
     * Bejegyzi a paraméterként kapott Iron nyersanyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Iron nyersanyag
     */
    @Override
    public void register(Iron m) {
        if(iron == null)
            iron = m;
        super.register(m);
    }

    /**
     * Bejegyzi a paraméterként kapott Coal anyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Coal nyersanyag
     */
    @Override
    public void register(Coal m) {
        if(coal == null)
            coal = m;
        super.register(m);
    }

    /**
     * Bejegyzi a paraméterként kapott Uranium anyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Uranium nyersanyag
     */
    @Override
    public void register(Uranium m) {
        if(uranium == null)
            uranium = m;
        super.register(m);
    }

    /**
     * A RobotResourceRegistry egyetlen publikus konstruktora.
     * Beállítja a szükséges nyersanyagok listáját.
     */
    public RobotResourceRegistry() {
        requiredMaterials.put(MaterialName.IRON,1);
        requiredMaterials.put(MaterialName.COAL,1);
        requiredMaterials.put(MaterialName.URANIUM,1);
    }

    /**
     * Eltávolítja a paraméterként kapott inventory-ból az összes szükséges nyersanyagok
     *
     * Megjegyzés: Ezt csak abban az esetben érdemes meghívni, ha az isSuccessful() függvény igaz értékkel tért vissza
     * @param i Inventory, amiből a nyersanyagokat el akarjuk távolítani
     */
    @Override
    public void removeRequiredMaterials(Inventory i) {
        i.removeMaterial(iron);
        i.removeMaterial(coal);
        i.removeMaterial(uranium);
    }
}
