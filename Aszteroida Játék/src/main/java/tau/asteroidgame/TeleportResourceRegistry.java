package tau.asteroidgame;

import java.util.ArrayList;
import java.util.List;

/**
 * A teleporthoz szükséges nyersanyagbejegyzője. A bejegyzett és szükséges anyagok
 * listájának összehasonlítása alapján engedélyezheti a barkácsolást azáltal, hogy jelzi,
 * hogy van e legalább 1 Iron, 1 Ice, 1 Uranium bejegyezve.
 */
public class TeleportResourceRegistry extends ResourceRegistry {

    private List<Iron> irons = new ArrayList<>();
    private Ice ice = null;
    private Uranium uranium = null;

    /**
     * Bejegyzi a paraméterként kapott Ice nyersanyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Ice nyersanyag
     */
    @Override
    public void register(Ice m) {
        if(ice == null)
            ice = m;
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
     * Bejegyzi a paraméterként kapott Iron nyersanyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Iron nyersanyag
     */
    @Override
    public void register(Iron m) {
        if(irons.size() < 2)
            irons.add(m);
        super.register(m);
    }

    /**
     * A TeleportResourceRegistry egyetlen publikus konstruktora.
     * Beállítja a szükséges nyersanyagok listáját.
     */
    public TeleportResourceRegistry() {
        requiredMaterials.put(MaterialName.IRON,2);
        requiredMaterials.put(MaterialName.ICE,1);
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
        i.removeMaterial(ice);
        i.removeMaterial(uranium);
        i.removeMaterial(irons.get(1));
        i.removeMaterial(irons.get(0));
    }
}
