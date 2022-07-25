package tau.asteroidgame;

import java.util.List;
import java.util.Random;

import tau.asteroidgame.Util.CommandDatabase;
import tau.asteroidgame.Util.Input;

import static tau.asteroidgame.Util.CommandDatabase.getRobotID;


/**
 * “Mesterséges intelligenciával” működő munkások, amik csak fúrni és mozogni tudnak, nyersanyagot kibányászni nem. 
 * Az aszteroida felrobbanásakor egy szomszédos aszteroidára repülnek át, kivéve, ha nincsen szomszédos aszteroida, mert ekkor megöli magát. 
 *
 */
public class Robot extends Worker {

	private final Inventory inventory = new BlankInventory();
	public Robot(){
		Game.INSTANCE.addSteppable(this);
	}
	
    public Robot(Asteroid a) {
        a.acceptWorker(this);
        
        Game.INSTANCE.addSteppable(this);
    }
	
    /**
     * Az robot felrobbanásakor végbemenő folyamatokat indítja el.
     * Ekkor átmozog egy szomszédos aszteroidára.
     * Ha nincs szomszédos aszteroida, megöli magát (meghívja a die() metódust)
     */
    @Override
    public void explode() {
    	Asteroid a = getAsteroid();
    	
    	List<Transporter> neighbors = a.getNeighbors();
    	
    	if (neighbors.isEmpty()) {
    		die();

    	}
    	else {
    		int maxAsteroidIndex = neighbors.size() - 1;
    		int asteroidIndex ;
    		
    		if (Game.INSTANCE.isDeterministic())
    			do {
    				asteroidIndex = Input.getUnsignedIntInput("Válassza ki hogy hányas indexű aszteroidára repüljön át a robot, 0.." + maxAsteroidIndex + " között" );
    			} while (asteroidIndex > maxAsteroidIndex);
    		else
    			asteroidIndex = new Random().nextInt(maxAsteroidIndex + 1);
    		
    		Transporter dest = neighbors.get(asteroidIndex);
    		move(dest);
    	}
    }

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	
	/**
		Ha az aszteroida, amin van nincs teljesen átfúrva, és
		1.: nincs napközelben, vagy 
		2.: napközelben van, de vállalja a kockázatot 35% eséllyel, akkor fúr.
		
		Egyébként átlép szomszédos aszteroidára. (Ha nincs ilyen, akkor nem csinál semmit)  
	 */
	
    public void step() {
    	Asteroid a = getAsteroid();
    	boolean isExposed =  a.isExposed();
    	boolean sunclose = a.isSunclose();
    	
    	
    	if (!isExposed &&
    	   (!sunclose || (Game.INSTANCE.isDeterministic() ? Input.getBooleanInput("Kockáztatni fog e a robot ?") :
    			   											(new Random().nextFloat() < 0.35))))
    	{
    		//System.out.println("robot "+getRobotID(this) + " drill");
    		drill();
    	}
    	else {
    		List<Transporter> neighbors = a.getNeighbors();
    		if (!neighbors.isEmpty()) {
    			int maxAsteroidIndex = neighbors.size() - 1;
        		int asteroidIndex;
        		if (Game.INSTANCE.isDeterministic())
        		do {
        			asteroidIndex = Input.getUnsignedIntInput("Válassza ki hogy hányas indexű aszteroidára repüljön át a robot, 0.." + maxAsteroidIndex + " között" );
        		} while (asteroidIndex > maxAsteroidIndex);
        		else {
        			asteroidIndex = new Random().nextInt(maxAsteroidIndex + 1);
        		}
        		
        		Transporter dest = neighbors.get(asteroidIndex);
        		move(dest);
				//System.out.println("Robot "+getRobotID(this) + "move");
    		}else {
    			System.out.println("Moving failed!");
			}
    	}
    }

    /**
     * A robot halálának folyamatát indítja el.
     * Ekkor eltávolítja magát arról az aszteroidáról, amin éppen van, és a Game (Steppables-re vonatkozó) nyilvántartásából.
     */
    @Override
    public void die() {
    	Asteroid a = getAsteroid();
    	a.removeWorker(this);
    	System.out.println("robot " + getRobotID(this) + " died");
    	Game.INSTANCE.removeSteppable(this);
    }
    
    @Override
    public String toString() {
       	StringBuilder sb = new StringBuilder().append("Robot ").append(getRobotID(this)).append('\n')
       	.append("\tAsteroid: ").append(CommandDatabase.getAsteroidID(getAsteroid())).append('\n');
       	
       	return sb.toString();
    }

	@Override
	public String getName() {
		return "robot";
	}
}
