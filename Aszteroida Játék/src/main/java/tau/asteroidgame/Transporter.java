package tau.asteroidgame;

/**
 * A szállító objektumokat reprezentáló ősosztály.
 */
public abstract class Transporter {
    /**
     * A szállító befogad egy űrmunkást. Visszaadja a sikerességet.
     * @param w A befogadandó űrmunkás
     * @return Logikai érték az alapján, hogy a művelet sikeres volt-e
     */
    public abstract boolean acceptWorker(Worker w);

    /**
     * A szállító eltávolít egy űrmunkást.
     * @param w Az eltávolítandó űrmunkás
     */
    public abstract void removeWorker(Worker w);

    /**
     * Eltávolítja a szállító egy szomszédját.
     * @param t Az eltávolítandó szomszéd
     */
    public abstract void removeNeighbor(Transporter t);
    
    /**
     * Hozzá próbálja adni a szállító szomszédságához egy szállítót.
     * Visszaadja, hogy sikeres volt e a hozzáadás.
     */
    public abstract boolean addNeighbor(Transporter t);
    
    
    /**
     * A napvihar elszenvedésekor lezajló folyamatokat végzi el.
     * (depth : Hány egységnyi távolságról érkezik a napvihar adott áramlata, hit: Eddig napvihart elszenvedett aszteroidák) 
     */
    public abstract void sufferSolarstorm(int depth, AsteroidRegistry hit);
}
