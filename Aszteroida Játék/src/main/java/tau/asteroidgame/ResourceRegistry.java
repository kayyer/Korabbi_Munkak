package tau.asteroidgame;


import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Bármely típusú anyag bejegyzés tárolására képes, így vissza tudja adni, hogy
 * valamennyi típusból van-e elég nyersanyag bejegyezve egy adott célra. Absztrakt
 * osztály, a leszármazottak a receptek és a bázis nyerési feltételének ellenőrzésére
 * használandók.
 */
public abstract class ResourceRegistry {
    protected final Map<MaterialName,Integer> requiredMaterials = new HashMap<>();
    private final Map<MaterialName,Integer> registeredMaterials = new HashMap<>();
    /**
     * Bejegyzi a paraméterben kapott leltár összes elemét a megfelelő regiszterekbe.
     * @param i A bejegyezni kívánt leltár
     */
    public void register(Inventory i) {
        List<Material> materialList = i.getMaterials();
        for (int j = 0; j < materialList.size(); j++) {
            Material m = materialList.get(j);
            m.giveSignature(this);
        }
    }

    /**
     * Bejegyzi a paraméterként kapott Iron nyersanyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Iron nyersanyag
     */
    public void register(Iron m) {
        Integer c = registeredMaterials.get(MaterialName.IRON);
        int n = (c != null) ? c+1 : 1;
        registeredMaterials.put(MaterialName.IRON,n);
    }

    /**
     * Bejegyzi a paraméterként kapott Ice nyersanyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Ice nyersanyag
     */
    public void register(Ice m) {
        Integer c = registeredMaterials.get(MaterialName.ICE);
        int n = (c != null) ? c+1 : 1;
        registeredMaterials.put(MaterialName.ICE,n);
    }

    /**
     * Bejegyzi a paraméterként kapott Coal anyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Coal nyersanyag
     */
    public void register(Coal m) {
        Integer c = registeredMaterials.get(MaterialName.COAL);
        int n = (c != null) ? c+1 : 1;
        registeredMaterials.put(MaterialName.COAL,n);
    }

    /**
     * Bejegyzi a paraméterként kapott Uranium anyagot a típus-specifikus regiszterbe.
     * @param m A bejegyezni kívánt Uranium nyersanyag
     */
    public void register(Uranium m) {
        Integer c = registeredMaterials.get(MaterialName.URANIUM);
        int n = (c != null) ? c+1 : 1;
        registeredMaterials.put(MaterialName.URANIUM,n);
    }

    /**
     * A feljegyzet anyagok listáját és az előírt anyagok listájának összehasonlítása alapján
     * visszaadja, hogy valamennyi típusból van-e elég nyersanyag bejegyezve, vagy nem.
     * @return Logikai érték, az alapján, hogy valamennyi típusból van-e elég nyersanyag bejegyezve, vagy nem.
     */
    public boolean isSuccessful(){
        for(Map.Entry<MaterialName, Integer> entry: requiredMaterials.entrySet()){
            Integer c = registeredMaterials.get(entry.getKey());
            if(c == null || c < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Eltávolítja a paraméterként kapott inventory-ból az összes szükséges nyersanyagok
     *
     * Megjegyzés: Ezt csak abban az esetben érdemes meghívni, ha az isSuccessful() függvény igaz értékkel tért vissza
     * @param i Inventory, amiből a nyersanyagokat el akarjuk távolítani
     */
    public void removeRequiredMaterials(Inventory i){
        for(Map.Entry<MaterialName, Integer> entry: requiredMaterials.entrySet()){
            for (int j = 0; j < entry.getValue(); j++) {
               //TODO: ezt
            }
        }
    }
}
