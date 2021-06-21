package tau.asteroidgame.UserInterface;

import java.util.HashMap;
import java.util.Map;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Material;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Worker;

/**
 * Adott játékbeli objektumhoz tartozó megjelenítést lekezelő ViewControl lekérdezését szolgáló adatbázis.
 * Be lehet regisztrálni ViewControllokat, valamint le lehet kérdezni adott megjeleníthető játékbeli objektumokhoz tartozó ViewControllokat.
 *
 */
public class ViewControlDatabase {
	/** Adott Asteroidhoz tartozó AsteroidViewControllokat tároló Map */
	private static Map<Asteroid, AsteroidViewControl> asteroidViewControlMap = new HashMap<Asteroid, AsteroidViewControl>();
	/** Adott Materialhoz tartozó MaterialViewControllokat tároló Map */
	private static Map<Material, MaterialViewControl> materialViewControlMap = new HashMap<Material, MaterialViewControl>();
	/** Adott Asteroidhoz tartozó AsteroidViewControllokat tároló Map */
	private static Map<Worker, WorkerViewControl> workerViewControlMap = new HashMap<Worker, WorkerViewControl>();
	/** Adott Teleporthoz tartozó AsteroidViewControllokat tároló Map */
	private static Map<Teleport, TeleportViewControl> teleportViewControlMap = new HashMap<Teleport, TeleportViewControl>();
	
	/** Asteroidhoz tartozó AsteroidViewControl beregisztrálása,
	 *  hogy később le tudjuk kérdezni, hogy a paraméterként kapott Asteroidhoz melyik AsteroidViewControl tartozik.
	 *  
	 * @param a Asteroid, akihez tartozó AsteroidViewControl-t regisztráljuk
	 * @param avc Beregisztrálandó AsteroidViewControl
	 */
	public static void register(Asteroid a, AsteroidViewControl avc) {
		asteroidViewControlMap.put(a, avc);
	}
	/** Materialhoz tartozó MaterialViewControl beregisztrálása,
	 *  hogy később le tudjuk kérdezni, hogy a paraméterként kapott Materialhoz melyik MaterialViewControl tartozik.
	 *  
	 * @param m Material, akihez tartozó MaterialViewControl-t regisztráljuk
	 * @param mvc Beregisztrálandó MaterialViewControl
	 */
	public static void register(Material m, MaterialViewControl mvc) {
		materialViewControlMap.put(m, mvc);
		
	}
	/** Workerhöz tartozó WorkerViewControl beregisztrálása,
	 *  hogy később le tudjuk kérdezni, hogy a paraméterként kapott Workerhöz melyik WorkerViewControl tartozik.
	 *  
	 * @param w Worker, akihez tartozó WorkerViewControl-t regisztráljuk
	 * @param wvc Beregisztrálandó WorkerViewControl
	 */
	public static void register(Worker w, WorkerViewControl wvc) {
		workerViewControlMap.put(w, wvc);
	}
	/** Teleporthoz tartozó TeleportViewViewControl beregisztrálása,
	 *  hogy később le tudjuk kérdezni, hogy a paraméterként kapott Asteroidhoz melyik AsteroidViewControl tartozik.
	 *  
	 * @param t Teleport, akihez tartozó TeleportViewControl-t regisztráljuk
	 * @param tvc Beregisztrálandó TeleportViewControl
	 */
	public static void register(Teleport t, TeleportViewControl tvc) {
		teleportViewControlMap.put(t, tvc);
	}
	
	/**
	 * Paraméterként kapott Asteroidhoz tartozó AsteroidViewControl lekérdezése.
	 * @param a A hozzá tartozó AsteroidViewControlt kérjük le.
	 * @return Paraméterhez tartozó AsteroidViewControl, amennyiben volt regisztrálás, egyébként null.
	 */
	public static AsteroidViewControl getAsteroidViewControl(Asteroid a) {
		return asteroidViewControlMap.get(a);
	}
	/**
	 * Paraméterként kapott Materialhoz tartozó MaterialViewControl lekérdezése.
	 * @param m A hozzá tartozó MaterialViewControlt kérjük le.
	 * @return Paraméterhez tartozó MaterialViewControl, amennyiben volt regisztrálás, egyébként null.
	 */
	public static MaterialViewControl getMaterialViewControl(Material m) {
		return materialViewControlMap.get(m);
	}
	/**
	 * Paraméterként kapott Workerhoz tartozó WorkerViewControl lekérdezése.
	 * @param w A hozzá tartozó WorkerViewControlt kérjük le.
	 * @return Paraméterhez tartozó WorkerViewControl, amennyiben volt regisztrálás, egyébként null.
	 */
	public static WorkerViewControl getWorkerViewControl(Worker w) {
		return workerViewControlMap.get(w);
	}
	/**
	 * Paraméterként kapott Teleporthoz tartozó TeleportViewControl lekérdezése.
	 * @param t A hozzá tartozó TeleportViewControlt kérjük le.
	 * @return Paraméterhez tartozó TeleportViewControl, amennyiben volt regisztrálás, egyébként null.
	 */
	public static TeleportViewControl getTeleportViewControl(Teleport t) {
		return teleportViewControlMap.get(t);
	}
	
	/**
	 * Összes regisztrálás törlése. (Lásd: register<> metódusok).
	 */
	public static void reset() {
		asteroidViewControlMap.clear();
		materialViewControlMap.clear();
		workerViewControlMap.clear();
		teleportViewControlMap.clear();
	}
}
