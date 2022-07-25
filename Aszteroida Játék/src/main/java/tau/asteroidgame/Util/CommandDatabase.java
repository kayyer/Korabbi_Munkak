package tau.asteroidgame.Util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.AsteroidField;
import tau.asteroidgame.Game;
import tau.asteroidgame.Material;
import tau.asteroidgame.Robot;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Transporter;
import tau.asteroidgame.UnidentifiedFlyingObject;
import tau.asteroidgame.Worker;

/**
 * A létrehozott, elért játékbeli objektumok kezelését parancs oldalról lehetővé tevő statikus osztály! 
 */
public class CommandDatabase {
	/**
	 * Létrehozott AsteroidFieldeket tartalmazza ID szerint.
	 */
	static private Map<String, AsteroidField> asteroidFields = new HashMap<String, AsteroidField>();
	/**
	 * Létrehozott Asteroidokat tartalmazza ID szerint. 
	 */
	static private Map<String, Asteroid> asteroids = new HashMap<String, Asteroid>();
	/**
	 * Létrehozott Settlereket tartalmazza ID szerint. 
	 */
	static private Map<String, Settler> settlers = new HashMap<String, Settler>();
	/**
	 * Létrehozott Robotokat tartalmazza ID szerint. 
	 */
	static private Map<String, Robot> robots = new HashMap<String, Robot>();
	/**
	 * Létrehozott UFO/UnidentifiedFlyingObjecteket tartalmazza ID szerint. 
	 */
	static private Map<String, UnidentifiedFlyingObject> ufos = new HashMap<String, UnidentifiedFlyingObject>();
	/**
	 * Létrehozott teleportokat tartalmazza ID szerint. 
	 */
	static private Map<String, Teleport> teleports = new HashMap<String, Teleport>();
	/**
	 * Létrehozott materialokat tartalmazza ID szerint. 
	 */
	static private Map<String, Material> materials = new HashMap<String, Material>();
	
	private CommandDatabase() {}
	
	/**
	 * Bindolja a paraméterben kapott aszteroidát, hogy a jövőben AsteroidCommand által létrehozott aszteroidák hozzá tartozzanak. 
	 * @param af bindolandó Asteroid
	 */
	static public void bindAsteroidField(AsteroidField af) { Game.INSTANCE.setField(af); }
	
	
	/**
	 * Visszadja a bindolt AsteroidFieldet. null, ha nincsen bindolva.
	 */
	static public AsteroidField getBoundAsteroidField() {
		return Game.INSTANCE.getField();
	}
	
	
	/**
	 * A getXYZ osztályok közös logikáját implementáló generic.
	 */
	static private <T> String getGenericID(Map<String, T> registry, T object) {
		Iterator<Entry<String, T>> it = registry.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, T> entry = it.next();
			if (entry.getValue() == object)
				return entry.getKey();
		}
		return null;
	}
	
	/**
	 * A getAllXYZ osztályok közös logikáját implementáló generic.
	 */
	static private <T> Collection<T> getAllGenericEntries(Map<String, T> registry) {
		return registry.values();
	}
	
	/**
	 * A registerXYZ osztályok közös logikáját implementáló generic.
	 */
	static private <T> String registerGeneric(Map<String, T> registry, T toRegister, String idBase) {
		if (toRegister == null) return null;
		String regKey = getGenericID(registry, toRegister);
		if (regKey != null)
			return regKey;
		
		regKey = idBase + Integer.toString(registry.size() + 1);
		registry.put(regKey, toRegister);
		
		return regKey;
	}
	
	
	/**
	 * Följegyzi az adott AszteroidFieldet, és annak generált ID-ját visszaadja. 
	 * ID: af[x], ahol [x] jelzi, hogy hányadik beregisztrált AsteroidField. 
	 *
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel.  
	 */
	static public String registerAsteroidField(AsteroidField af) {
		return registerGeneric(asteroidFields, af, "af");
	}
	
	
	/**
	 * Följegyzi az adott Aszteroidot, és annak generált ID-ját visszaadja. 
	 * ID: a[x], ahol [x] jelzi, hogy hányadik beregisztrált Asteroid. 
	 *
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel.  
	 */
	static public String registerAsteroid(Asteroid a) {
		return registerGeneric(asteroids, a, "a");
	}
	
	/**
	 * Följegyzi az adott Settlert, és annak generált ID-ját visszaadja. 
	 * ID: s[x], ahol [x] jelzi, hogy hányadik beregisztrált Settler. 
	 *
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel.  
	 */
	static public String registerSettler(Settler s) {
		return registerGeneric(settlers, s, "s");
	}
	
	/**
	 * Följegyzi az adott Settlert adott névvel.
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel.  
	 */
	static public String registerSettler(Settler s, String name) {
		if (s == null) return null;
		String regKey = getSettlerID(s);
		if (regKey != null)
			return regKey;
		
		regKey = name;
		settlers.put(regKey, s);
		
		return regKey;
	}
	
	/**
	 * Följegyzi az adott Robotot, és annak generált ID-ját visszaadja. 
	 * ID: r[x], ahol [x] jelzi, hogy hányadik beregisztrált Robot. 
	 *
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel. 
	 */
	static public String registerRobot(Robot r) {
		return registerGeneric(robots, r, "r");
	}
	
	/**
	 * Följegyzi az adott UnidentifiedFlyingObject-t, és annak generált ID-ját visszaadja. 
	 * ID: u[x], ahol [x] jelzi, hogy hányadik beregisztrált UnidentifiedFlyingObject. 
	 *
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel.  
	 */
	static public String registerUFO(UnidentifiedFlyingObject u) {
		return registerGeneric(ufos, u, "u");
	}
	
	/**
	 * Följegyzi az adott Teleportot, és annak generált ID-ját visszaadja. 
	 * ID: g[x], ahol [x] jelzi, hogy hányadik beregisztrált Teleport. 
	 *
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel.  
	 */
	static public String registerTeleport(Teleport g) {
		return registerGeneric(teleports, g, "g");
	}
	
	/**
	 * Följegyzi az adott Teleportot, és annak generált ID-ját visszaadja. 
	 * ID: g[x], ahol [x] jelzi, hogy hányadik beregisztrált Teleport. 
	 *
	 * Null paraméter esetén nullal tér vissza.
	 * Ha már be van jegyezve, akkor a meglévő ID-vel.  
	 */
	static public String registerMaterial(Material m) {
		return registerGeneric(materials, m, "m");
	}
	
	/**
	 * Visszaadja az adott ID-vel beregisztrált AsteroidFieldet.
	 * Nullal tér vissza, amennyiben nincs ilyen ID-n beregisztrált. 
	 */
	static public AsteroidField getAsteroidField(String id) {
		return asteroidFields.get(id);
	}
	/**
	 * Visszaadja az adott ID-vel beregisztrált Asteroidot.
	 * Nullal tér vissza, amennyiben nincs ilyen ID-n beregisztrált. 
	 */
	static public Asteroid getAsteroid(String id) {
		return asteroids.get(id);
	}
	
	/**
	 * Visszaadja az adott ID-vel beregisztrált Materialt.
	 * Nullal tér vissza, amennyiben nincs ilyen ID-n beregisztrált. 
	 */
	static public Material getMaterial(String id) {
		return materials.get(id);
	}

	/**
	 * Visszaadja az adott ID-vel rendelkező űrmunkást, legyen az telepes, robot vagy UFO.
	 * Ha nincs ilyen id-val űrmunkás akkor null-a tér vissza.
	 */
	static public Worker getWorker(String id) {
		if(getSettler(id) != null){
			return settlers.get(id);
		}else if(getRobot(id) != null){
			return robots.get(id);
		}else if(getUFO(id) != null){
			return ufos.get(id);
		}else{
			return null;
		}
	}
	/**
	 * Visszaadja az adott ID-vel rendelkező szállítót, legyen az teleport vagy aszteroida.
	 * Ha nincs ilyen id-val se aszteroida se szállító akkor null-al tér vissza.
	 */
	static public Transporter getTransporter(String id) {
		if(getAsteroid(id) != null){
			return asteroids.get(id);
		}else if(getTeleport(id) != null){
			return teleports.get(id);
		}else{
			return null;
		}
	}
	/**
	 * Visszaadja az adott ID-vel beregisztrált Settlert.
	 * Nullal tér vissza, amennyiben nincs ilyen ID-n beregisztrált. 
	 */
	static public Settler getSettler(String id) {
		return settlers.get(id);
	}
	/**
	 * Visszaadja az adott ID-vel beregisztrált Robotot.
	 * Nullal tér vissza, amennyiben nincs ilyen ID-n beregisztrált. 
	 */
	static public Robot getRobot(String id) {
		return robots.get(id);
	}
	/**
	 * Visszaadja az adott ID-vel beregisztrált UnidentifiedFlyingObjectet.
	 * Nullal tér vissza, amennyiben nincs ilyen ID-n beregisztrált. 
	 */
	static public UnidentifiedFlyingObject getUFO(String id) {
		return ufos.get(id);
	}
	
	/**
	 * Visszaadja az adott ID-vel beregisztrált Teleportot.
	 * Nullal tér vissza, amennyiben nincs ilyen ID-n beregisztrált. 
	 */
	static public Teleport getTeleport(String id) {
		return teleports.get(id);
	}
	
	
	/**
	 * Visszaadja az összes beregisztrált AsteroidFieldet.
	 */
	static public Collection<AsteroidField> getAllAsteroidFields() {
		return getAllGenericEntries(asteroidFields);
	}
	
	/**
	 * Visszaadja az összes beregisztrált Asteroidot.
	 */
	static public Collection<Asteroid> getAllAsteroids() {
		return getAllGenericEntries(asteroids);
	}
	
	/**
	 * Visszaadja az összes beregisztrált Settlert.
	 */
	static public Collection<Settler> getAllSettlers() {
		return getAllGenericEntries(settlers);
	}
	
	/**
	 * Visszaadja az összes beregisztrált Robotot.
	 */
	static public Collection<Robot> getAllRobots() {
		return getAllGenericEntries(robots);
	}
	
	/**
	 * Visszaadja az összes beregisztrált UnidentifiedFlyingObjectet.
	 */
	static public Collection<UnidentifiedFlyingObject> getAllUFOs() {
		return getAllGenericEntries(ufos);
	}
	
	/**
	 * Visszaadja az összes beregisztrált Teleportot.
	 */
	static public Collection<Teleport> getAllTeleports() {
		return getAllGenericEntries(teleports);
	}
	
	/**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva az AsteroidField. 
	 * Nullal tér vissza, amennyiben nincs beregisztrálva. 
	 */
	static public String getAsteroidFieldID(AsteroidField af) {
		return getGenericID(asteroidFields, af);
	}
	
	/**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva az Asteroid. 
	 * Nullal tér vissza, amennyiben nincs beregisztrálva. 
	 */
	static public String getAsteroidID(Asteroid a) {
		return getGenericID(asteroids, a);
	}
	
	/**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva a Material. 
	 * Nullal tér vissza, amennyiben nincs beregisztrálva. 
	 */
	static public String getMaterialID(Material m) {
		return getGenericID(materials, m);
	}
	
	/**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva a Settler. 
	 * Nullal tér vissza, amennyiben nincs beregisztrálva. 
	 */
	static public String getSettlerID(Settler s) {
		return getGenericID(settlers, s);
	}
	
	/**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva a Robot.
	 */
	static public String getRobotID(Robot r) {
		return getGenericID(robots, r);
	}
	
	/**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva az UnidentifiedFlyingObject. 
	 * Nullal tér vissza, amennyiben nincs beregisztrálva. 
	 */
	static public String getUFOID(UnidentifiedFlyingObject ufo) {
		return getGenericID(ufos, ufo);
	}
	
	/**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva a Teleport.
	 * Nullal tér vissza, amennyiben nincs beregisztrálva. 
	 */
	static public String getTeleportID(Teleport t) {
		return getGenericID(teleports, t);
	}
	
	
	/**
	 * /**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva a Teleport/Asteroid.
	 * Nullal tér vissza, amennyiben nincs beregisztrálva.
	 */
	static public String getTransporterID(Transporter t) {
		if (t.getClass() == Asteroid.class)
			return getAsteroidID((Asteroid) t);
	return getTeleportID((Teleport) t);
	}
	
	/**
	 * /**
	 * Visszaadja az adott ID-t, amivel be lett regisztrálva a Settler/Worker/UFO
	 * Nullal tér vissza, amennyiben nincs beregisztrálva. 
	 */
	static public String getWorkerID(Worker w) {
		Class c = w.getClass();
		if (c == Settler.class)
			return getSettlerID((Settler) w);
		else if (c == Robot.class)
			return getRobotID((Robot) w);
		return getUFOID((UnidentifiedFlyingObject) w);
	}
	

	
	/**
	 * Az eddig beregisztrált összes objektumot törli a nyilvántartásból. A bindolt AsteroidFieldet is. 
	 */
	static public void reset() {
		asteroidFields.clear();
		asteroids.clear();
		settlers.clear();
		robots.clear();
		ufos.clear();
		teleports.clear();
		materials.clear();
		Game.INSTANCE.reset();
	}
}
