package tau.asteroidgame;

import tau.asteroidgame.Util.CommandDatabase;
import static tau.asteroidgame.Util.CommandDatabase.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Az aszteroida mező egy aszteroidáját reprezentálja. Az aszteroidák vagy
 * napközelben vannak vagy sem, ez bizonyos nyersanyagoknál fontos tényező. Az
 * aszteroida magja homogén nyersanyagot tartalmaz, mely bányászás hatására kiürül,
 * de lehetnek alapból üreges aszteroidák is. A teljesen átfúrt, üreges aszteroidában a
 * karakterek elbújhatnak a Napvihar elől. Ha egy radioaktív maggal rendelkező
 * aszteroida belsejét felfedjük (köpenyét végigfúrjuk) és az aszteroida napközelbe
 * kerül, akkor az aszteroida felrobban. Egy aszteroidának akármennyi szomszédja
 * lehet. Az aszteroida ellenőrzi, hogy a rajta álló asztronauták képesek-e megépíteni az
 * űrbázist.
 */
public class Asteroid extends Transporter implements Steppable {
    private Material core = null;
    private final List<Worker> workers = new ArrayList<>();
    private final List<Transporter> neighbors = new ArrayList<>();
    private int layer;
    private int layersDrilled = 0;
    
    private int sunDistance;
    private int sunDistanceMax = 30;
    private boolean sunclose = false;

    public Asteroid(int layer) {
        this.layer = layer;
        Game.INSTANCE.addSteppable(this);
        
        sunDistance = sunDistanceMax;
    }


    public Asteroid(int layer, Material core) {
    	this.layer = layer;
        this.core = core;
        Game.INSTANCE.addSteppable(this);
        
        sunDistance = sunDistanceMax;
    }
    
    public Asteroid(int layer, Material core, int sunDistanceMax) {
    	this.layer = layer;
        this.core = core;
        Game.INSTANCE.addSteppable(this);
        
        sunDistance = this.sunDistanceMax = sunDistanceMax;
    }

    /**
     * Az aszteroida befogad egy űrmunkást (robot, telepes), ez az űrmunkás mostantól az
     * aszteroidán fog tartózkodni. Ezt követően checkBaseWin() segítségével ellenőrzi a
     * nyerési feltételt, és teljesülés esetén a Game segítségével nyeréssel zárja a játékot.
     * @param w A befogadandó munkás
     * @return Mindig true.
     */
    @Override
    public boolean acceptWorker(Worker w) {
        workers.add(w);
        w.setAsteroid(this);
        boolean baseWon = checkBaseWin();
        if (baseWon) {
        	Game.INSTANCE.endGame(true);
        }

        return true;
    }

    /**
     * Eltávolít egy űrmunkást az aszteroidán tartózkodók listájáról.
     * @param w Az eltávolítandó űrmunkás
     */
    @Override
    public void removeWorker(Worker w) {
        workers.remove(w);
    }

    /**
     * Az aszteroida felrobban és a rajta lévő telepesek, robotok is elszenvedik a
     * Robbanást. Az esetlegesen rajta lévő teleportkaput is elpusztítja, ha el van helyezve.
     */
    public void explode() {
    	Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.ASTEROID_EXPLODED, this));
    	
        for (int i = 0; i < workers.size();++i){
        	Worker w = workers.get(i);
            w.explode();
        }

        for (int i = neighbors.size()-1; i>= 0;--i) {
            Transporter t = neighbors.get(i);
            t.removeNeighbor(this);
        }

        Game.INSTANCE.removeSteppable(this);
        
    }

    /**
     *
     * @return Igaz értéket ad vissza, ha az aszteroida teljesen meg van fúrva.
     */
    public boolean isExposed() {
        return layersDrilled >= layer;
    }

    /**
	  * Az aszteroida elszenvedi a naphivar következményeit, vagyis ha az áramlat mélysége depth <= 5, és nincs beregisztrálva hit-be akkor:  
      * 1. ha nem teljesen átfúrt, üreges, akkor a rajta lévő munkások (telepesek, robotok, ufók) elpusztulnak. 
      * 2. Beregisztrálja magát hit-be. 
      * 3. A szomszédjaira meghívja a sufferSolarstormot ugyanazon paraméterekkel, viszont a depth egyel nagyobb lesz. 
      * Egyébként nem csinál semmit. 
     */
    public void sufferSolarstorm(int depth, AsteroidRegistry hit) {
        if (depth <= 5 && !hit.contains(this)) {
        	if (!isExposed() || core != null) {
        		for (Worker w: workers)
        			w.die();
        	}
        	hit.register(this);
        	
        	for (Transporter t : neighbors)
        		t.sufferSolarstorm(depth + 1, hit);
        }
    }

    /**
     * Az aszteroida fúrva van, ezért a fúrás hatására eggyel növeli a fúrt lyukat, vagyis a
     * layersDrilled változó értékét, amennyiben nincsen teljesen átfúrva
     * (layersDrilled=layer). Egyébként nem csinál semmit.
     */
    public boolean drilled() {
    	// 
    	if (layersDrilled < layer) {
    		layersDrilled++;
    		// Magon csak akkor hívunk expose-ot, ha csak most lett teljesen végigfúrva.
    		// Egyébként step felelőssége az expose() meghívása.
    		if(isExposed() && core != null){
            	core.exposed(this);
        	}
    		return true;
    	}
    	
    	return false;
    }

    /**
     * Megpróbálja lebonyolítani a bányászást. 
     * Amennyiben teljesen átfurt és nem üres a mag, akkor át próbálja adni az anyagot 
     * a paraméterben kapott Inventory-nak. 
     * Ha sikeresen át tudta adni az anyagot (inv-en addMaterial(core) meghívása igazzal tér vissza), 
     * akkor üregessé válik, megnézi a nyerési feltételt checkBaseWin() 
     * segítségével, és igazat ad vissza, jelezve a sikeres bányászást.
     *  
     * Egyébként hamisat ad vissza.
     *  
	 * (Nyerési feltétel teljesülése esetén Game segítségével nyeréssel zárja a játékot)
	 *   
     * @param inv Paraméterben kapott leltár
     * @return Az akció sikerességéte jelző logikai érték
     */
    public boolean mined(Inventory inv) {
        if (core != null && isExposed()) {
        	boolean storeSuccess = inv.addMaterial(core);
        	if (storeSuccess) {
        		this.removeMaterial();
        		boolean baseWin = this.checkBaseWin();

        		if (baseWin) {
        			Game.INSTANCE.endGame(true);
        		}
        		return true;
        	}
        	return false;
        }
        return false;
    }

    /**
     * El próbálja helyezni a paraméterként kapott anyagot a magjában. Ha teljesen átfúrt és
     * üreges, akkor ez sikerül, és igazzal tér vissza, egyébként hamissal. (Siker esetén a
     * @param m A befogadandó nyeranyag
     * @return Az akció sikerességéte jelző logikai érték
     */
    public boolean acceptMaterial(Material m) {

        if(core == null && isExposed()){
            core = m;
            
            if (sunclose) {
            	core.exposed(this);
            }
            return true;
        }
        return false;
    }

    /**
     * Az aszteroidából eltávolítja az abban található anyagot, amennyiben nem üreges.
     * Egyébként nem csinál semmit.
     */
    public void removeMaterial() {
    	if (isExposed())
    		core = null;
    }

    /**
     * Hozzáadja a paraméterül kapott aszteroidát a szomszédokhoz.
     * @param t A hozzáadandó szomszéd
     * @return Ha már szomszédként szerepelt, hamisat ad vissza, egyébként igazat.
     */
    public boolean addNeighbor(Transporter t) {
        neighbors.add(t);
        return true;
    }

    /**
     * Eltávolítja a paraméterül kapott aszteroidát a szomszédokból. Ha nem volt eredetileg
     * szomszéd, nem csinál semmit.
     * @param t Az eltávolítandó szomszéd
     */
    @Override
    public void removeNeighbor(Transporter t) {
        neighbors.remove(t);
    }

    /**
     * Ellenőrzést végez, hogy teljesülnek-e a bázisépítés feltételei.
     * @return Ha teljesülnek, igaz értékkel tér vissza, ellenkező esetben hamissal.
     */
    public boolean checkBaseWin() {
    	BaseResourceRegistry reg = new BaseResourceRegistry();
    	for(Worker w : workers) {
    		Inventory inv = w.getInventory();
    		if(inv != null) {
                reg.register(inv);
            }
    	}
    	boolean temp = reg.isSuccessful();
    	return temp;
    }


    
    /**
     * Az aszteroida léptetésére szolgáló függvény. 
     * 1.A Ha közeledik a naphoz (!sunclose), akkor eggyel közelebb viszi a napot. (sunDistance--) Ha ennek hatására közel van a naphoz (sunDistance == 0), akkor ez belső állapotban jelződik (sunclose = true). 
	*  1.B Egyébként Ha távolodik a naptól (sunclose), akkor eggyel távolabb viszi a naptól (sunDistance++). Ha ennek hatására távol lesz a naptól (sunDistance == sunDistanceMax), akkor ez belső állapotban jelződik (sunclose = false). 
	*  2. Ha ezután  napközelivé válik és teljesen kifúrt, nem üreges, akkor meghívja az exposed(a: Asteroid) függvényt a magon. 
     */
    public void step() {
    	if (!sunclose) {
    		sunDistance--;
    		if (sunDistance == 0)
    			sunclose = true;
    	}
    	else {
    		sunDistance++;
    		if (sunDistance == sunDistanceMax)
    			sunclose = false;
    	}
    	
    	if (isExposed() && core != null)
    		core.exposed(this);
    }

    /**
     * Az aszteroida napközeli állapotát kéri
     * @return Igazat ad vissza, ha az aszteroid napközelben van
     */
    public boolean isSunclose() {
        return sunclose;
    }
    
    /**
     * Beállítja az aszteroidát napközelbe/naptávolba
     */
    public void setSunclose(boolean val) {
    	    sunclose = val;
    	    if(isExposed() && core != null){
    	        core.exposed(this);
            }
    }
    
    /**
     * Leírás: Beállítja az aszteroida jelenlegi távolságát a naptól.
     *  0..sunMaxDistance között kell lennie, ekkor beállítja és igazat ad vissza, egyébként hamisat. 
     */
    public boolean setSunDistance(int dist) {
    	if (dist >= 0 && dist <= sunDistanceMax) {
    		sunDistance = dist;
    		return true;
    	}
    	return false;
    }
    
    public Material getCore(){
        return core;
    }
    /**
     * Beállítja az aszteroida magját a paraméterül kapott nyersanyagra
     * @param m A nyersanyag, amit az aszteroida magjába szeretnénk helyezni
     */
    public void setCore(Material m) {
    	core = m;
    }

    /**
     * Visszaadja az aszteroida szomszédjait
     * @return Az aszteroida szomszédja
     */
    public List<Transporter> getNeighbors() {
        return neighbors;
    }

    /**
     * Visszaadja az aszteroidán lévő munkásokat.
     * @return Aszteroidán lévő munkások.
     */
    public List<Worker> getWorkers() {
        return workers;
    }
    
    /**
     * Beállítja az aszteroidába fúrt lyuk mélysége. 0..layer közé clampeli a paraméteren keresztül kapott értéket.
     */
    public void setLayersDrilled(int layersDrilled) {
    	this.layersDrilled = Math.min( Math.max(0, layersDrilled), layer);

    }

    /**
     * Beállítja az aszteroidát borító sziklaréteg vastagságát
     * @param l Az aszteroidát borító sziklaréteg vastagságát
     */
    public void setLayer(int l) {
        layer = l;
        layersDrilled = Math.min(layer, layersDrilled);

        if(isExposed() && isSunclose() && core != null){
            core.exposed(this);
        }
    }

    public int getLayer(){
        return layer;
    }

    public int getRemainingLayer(){
        if(layer - layersDrilled >= 0)
            return layer - layersDrilled;
        return 0;
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Asteroid ").append(CommandDatabase.getAsteroidID(this)).append('\n');
    	sb.append("\tLayers: ").append(layer - layersDrilled).append('\n');
    	sb.append("\tNeighbors: ");
    	for (Transporter t : neighbors) {
    		if (t.getClass() == Asteroid.class)
    			sb.append(CommandDatabase.getAsteroidID((Asteroid) t)).append(' ');
    	}
    	sb.append('\n');
    	sb.append("\tCore: ").append(core == null ? "null" : core.toString()).append('\n');
    	sb.append("\tWorkers: ");
    	for (Worker w: workers)
    		sb.append(CommandDatabase.getWorkerID(w)).append(' ');
    	sb.append('\n');
    	sb.append("\tSundistance: ").append(sunDistance).append('\n');
    	sb.append("\tSunclose: ").append(sunclose);
    	
    	return sb.toString();
    }
}
