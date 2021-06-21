package tau.asteroidgame;

import java.util.HashSet;
import java.util.Set;

/**
 * Napszél lefolyása közben belé lehet jegyezni, ha egy Aszteroida elszenvedte a napvihart (hogy ne legyen végtelen rekurzió).
 * Le lehet kérdezni tőle, hogy egy adott Aszteroida belé van e jegyezve, vagy nem
 * (Aszteroida megkérdezi, hogy őmaga elszenvedte már e a napszelet)
 */
public class AsteroidRegistry {
    //! Tartalmazza a bejegyzett aszteroidákat.
    private Set<Asteroid> asteroids = new HashSet<>();

    /**
     * Bejegyez egy Aszteroidát, vagyis hozzáadja a bejegyzett aszteroidák listájához.
     * (ha már be volt jegyezve, nem csinál semmit)
     */
    public void register(Asteroid a){
        asteroids.add(a);
    }

    /**
     * Visszaadja, hogy a paraméterként kapott Aszteroida be volt már jegyezve.
     * @param a A keresett aszteroida
     * @return Logikai érték az alapján, hogy a kapott aszteroida benne van-e vagy sem
     */
    public boolean contains(Asteroid a){
        return asteroids.contains(a);
    }
}
