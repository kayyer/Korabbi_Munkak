package tau.asteroidgame;

import java.util.List;
import java.util.Random;

import tau.asteroidgame.Util.CommandDatabase;
import tau.asteroidgame.Util.Input;


/**
 * Aszteroidák között mozognak, és használják a kapukat.
 * Csak nyersanyag-felvételre (“bányászásra”) képesek kifúrt aszteroidán, fúrni nem tudnak. Bármennyi nyersanyagot be tudnak gyűjteni eközben. 
 * Robbanás hatására meghalnak, és ekkor az általuk gyűjtött nyersanyagok elvésznek. 
 *
 */
public class UnidentifiedFlyingObject extends Worker{
    // Ez soha nem telik meg.
    private final Inventory inventory = new UFOInventory();
    
    public UnidentifiedFlyingObject(Asteroid a) {
    	a.acceptWorker(this);
    	Game.INSTANCE.addSteppable(this);
    }


    /**
     * nem csinál semmit, mivel fúrásra képtelen. 
     */
    @Override
    public void drill() {
        //It does nothing.
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return "ufo";
    }

    /**
     * Megnézi, hogy végigfúrt e az aszteroida. Ha igen, és úgy véli 35% eséllyel, 
     * hogy a mag nem üres, akkor ki próbálja bányászni az anyagot. Egyébként átmegy egy 
     * szomszédos aszteroidára, amennyiben az van. 
     */
    @Override
    public void step() {
    	Asteroid a = getAsteroid();
    	if (a.isExposed() &&
                ((Game.INSTANCE.isDeterministic() ? Input.getBooleanInput("Kockáztatni fog e az UFO ?") :
    			   							  (new Random().nextFloat() < 0.35)) ) ) {
    		mine();
    	} else {
    		List<Transporter> neighbors = a.getNeighbors();
    		if (!neighbors.isEmpty()) {
    			int maxAsteroidIndex = neighbors.size() - 1;
        		int asteroidIndex;
        		if (Game.INSTANCE.isDeterministic())
        		do {
        			asteroidIndex = Input.getUnsignedIntInput("Válassza ki hogy hányas indexű aszteroidára repüljön át az UFÓ, 0.." + maxAsteroidIndex + " között" );
        		} while (asteroidIndex > maxAsteroidIndex);
        		else {
        			asteroidIndex = new Random().nextInt(maxAsteroidIndex + 1);
        		}
        		
        		Transporter dest = neighbors.get(asteroidIndex);
        		move(dest);
    		} else {
    		    System.out.println("Moving failed!");
            }
    	}
    }

    /**
     * Az ufó halála esetén végbemenő folyamatokat indítja el.
     * Ekkor eltávolítja magát arról az Aszteroidáról, amin van, és a Game (Steppables-re vonatkozó) nyilvántartásából. 
     */
    @Override
    public void die() {
    	System.out.println("ufo " + CommandDatabase.getUFOID(this) +" die");
    	getAsteroid().removeWorker(this);
    	Game.INSTANCE.removeSteppable(this);
    }

    /**
     * Az ufó elrobbanásakor végbemenő folyamatokat indítja el.
     * Ekkor meghal. (meghívja die() metódust magán) 
     */
    @Override
    public void explode() {
    	System.out.println("ufo " + CommandDatabase.getUFOID(this) +" explode");
    	die();
    }

    /**
     * Mindig beteszi a leltárba az m paraméterben kapott anyagot, a tároló soha nem telik meg.
     * @param m : Material
     * @return A művelet sikerét jelző logikai érték.
     */
    public boolean acceptMaterial(Material m) {
        inventory.addMaterial(m);
        return true;
    }
    
    @Override
    public String toString() {
       	StringBuilder sb = new StringBuilder().append("Robot ").append(CommandDatabase.getUFOID(this)).append('\n')
       	.append("Asteroid: ").append(CommandDatabase.getAsteroidID(getAsteroid())).append('\n');
       	
       	return sb.toString();
    }

    public boolean mine() {
        Asteroid asteroid = getAsteroid();
        if (asteroid.mined(inventory)) {
        	List<Material> matList = inventory.getMaterials();
        	System.out.println(matList.get(matList.size() - 1));
        	return true;
        }
        //if (asteroid.getCore() == null)
        	//System.out.println("Mining failed: Empty core!");
        //else
        	System.out.println("Mining failed!");
        return false;
    }
}
