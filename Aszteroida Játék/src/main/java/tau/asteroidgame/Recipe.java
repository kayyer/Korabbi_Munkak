package tau.asteroidgame;

public abstract class Recipe {
    /**
     *
     *A recept bonyolítja le a barkácsolásához szükséges nyersanyagok meglétének ellenőrzését, 
     * a felhasznált anyagok leltárból való törlését, valamint a barkácsolandó dolog elkészítését.
     *
     * @param s Barkácsolást végző adott telepes.
     * @return Sikerült e a barkácsolás.
     */
    public abstract boolean craft(Settler s);
}
