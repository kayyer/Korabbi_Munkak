package tau.asteroidgame;


/**
 * A nyersanyagok általánosítása.
 * A játékban kibányászható nyersanyagok osztálya.
 */
public abstract class Material {
    /**
     * Akkor hívandó meg, amikor egy teljesen átfúrt napközeli aszteroida magjában van.
     * (A paraméter maga az aszteroida, ami magjában helyezkedik el).
     * Alapértelmezésben nem csinál semmit.
     * @param a Az aszteroida, aminben a nyersanyag található
     */
    public void exposed(Asteroid a) {}

    /**
     * Beiratja magát a paraméterként kapott ResourceRegistry-be, mint adott típusú
     * nyersanyag.
     * @param resReg ResourceRegistry, amibe a nyersanyag beregisztrálja magát
     */
    abstract public void giveSignature(ResourceRegistry resReg);
}
