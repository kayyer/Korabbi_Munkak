package tau.asteroidgame;

import java.util.List;
import java.util.Random;

import tau.asteroidgame.Util.CommandDatabase;


/**
 * Aszteroidákra lehelyezhető teleportkapu osztály.
 * Egy kapuval át lehet mozogni egy másik aszteroidára, amin a kapu párja található.
 * A kapu és párja is elpusztul, ha az egyik kapu aszteroidája felrobban. 
 *
 */
public class Teleport extends Transporter implements Steppable {

    private boolean placed = false;
    private Settler holder = null;
    private Teleport pair;
    private Asteroid asteroid = null;
    private boolean crazy = false;

    
    public Teleport(Settler holder) {
        this.holder = holder;
        holder.acceptTeleport(this);
        pair = new Teleport(holder,this);
    }

   
    public Teleport(Asteroid a1, Asteroid pair_a2) {
    	place(a1);
    	pair = new Teleport(pair_a2, this);
    }
    
    public Teleport(Asteroid a, Settler s) {
    	place(a);
    	pair = new Teleport(s, this);
    }
    
    public Teleport(Settler holder, Teleport pair) {
        this.holder = holder;
        holder.acceptTeleport(this);
        this.pair = pair;
    }
    
    public Teleport(Asteroid a1, Teleport pair) {
    	place(a1);
    	this.pair = pair;
    }
    
    

    /**
     * A kergült kapu mozgását megvalósító függvény.
     */
    public void moveToNeighbor() {
    	if (asteroid != null) {
    		List<Transporter> neighbors = asteroid.getNeighbors();
    		// Ha csak a teleport a szomszédja, nincs hova mozogni.
    		if (neighbors.size() < 2)
    			return;
    		
    		// Ne tudjuk kiválasztani önmagunkat következő aszteroidának!
    		asteroid.removeNeighbor(this);
    		
    		int nextAsteroidIdx = 0;
    		if (!Game.INSTANCE.isDeterministic()) {
    			nextAsteroidIdx = new Random().nextInt(neighbors.size());
    		}
    		// Ha nem sikerül a mozgás, attól még maradjanak szomszédok. 
        	asteroid.addNeighbor(this);
    		Transporter nextDest = neighbors.get(nextAsteroidIdx);

    		if (nextDest.addNeighbor(this)) {
    			asteroid.removeNeighbor(this);
    			// Ha igazzal tér vissza addNeighbor, akkor biztosan Asteroid
    			asteroid = (Asteroid) nextDest;
    			
    			System.out.println("crazy teleport " + CommandDatabase.getTeleportID(this) + " moved to " + CommandDatabase.getAsteroidID(asteroid));
    		} else {
    		    //Ha elsőre teleportot találtunk, akkor keressünk egy nem teleport szomszédot
                // Ez azért kell, hogy a Teleport ne mehessen át egy másik teleporton keresztül
    		    for (int i = 0; i < neighbors.size(); i++){
    		        if(neighbors.get(i).addNeighbor(this)){
    		            asteroid.removeNeighbor(this);
                        asteroid = (Asteroid) nextDest;
                        System.out.println("crazy teleport " + CommandDatabase.getTeleportID(this) + " moved to " + CommandDatabase.getAsteroidID(asteroid));
                        break;
                    }
                }

            }
    	}
    }

    
    /**
     * A teleportkapu átviteti a munkást, amennyiben a párja le van helyezve, és igazzal tér 
     *  vissza, egyébként nem csinál semmit, és hamissal tér vissza. 
     */
    @Override
    public boolean acceptWorker(Worker w) {
        if(isPlaced() && pair.isPlaced()){
            Asteroid tmp = pair.getAsteroid();
            tmp.acceptWorker(w);
            return true;
        }
        return false;
    }

    public Asteroid getAsteroid() {
        return asteroid;
    }

    public Teleport getPair(){
        return pair;
    }

    public Settler getHolder() {
        return holder;
    }

    @Override
    public void removeWorker(Worker w) {}


    /**
     * Ekkor megsemmisül, így párjának is szól, hogy semmisüljön meg. (Meghívja a
     * destroy() metódust)
     * @param t Az eltávolítandó szomszéd
     */
    @Override
    public void removeNeighbor(Transporter t)
    {
        destroy();
    }

    /**
     *  A teleportkapu elpusztul párjával együtt
     */
    public void destroy() {
        if(placed)
        {
            asteroid.removeNeighbor(this);
        }
        else
        {
            holder.removeTeleport(this);
        }
        asteroid = null;
        holder = null;
        if(pair.isPlaced())
        {
            Asteroid tmp = pair.getAsteroid();
            tmp.removeNeighbor(pair);
        }
        else
        {
            Settler tmp = pair.getHolder();
            tmp.removeTeleport(pair);
        }
        System.out.println("Teleport " + CommandDatabase.getTeleportID(this) + "," + CommandDatabase.getTeleportID(pair) + " destroyed");
    }

    /**
     * A teleportkapu a paraméterben kapott aszteroidára kerül elhelyezésre.
     * @param a A kapott aszteroida
     */
    public void place(Asteroid a) {
        placed = true;
        holder = null;
        asteroid = a;
        asteroid.addNeighbor(this);
    }

    /**
     *
     * @return Igazat ad vissza, ha a teleportkapu le van helyezve
     */
    public boolean isPlaced() {
        return placed;
    }

    /**
     * Beállítja a teleportkapu párját
     * @param pair a teleportkapu  új párja
     */
    public void setPair(Teleport pair) {
        this.pair = pair;
    }

    /**
     * Beállítja a Teleportot birtokló telepest
     * @param holder A Teleport új birtokosa
     */
    public void setHolder(Settler holder) {
        this.holder = holder;
    }
    
    /**
     *  A teleport elszenvedi a napvivar következményeit, vagyis megkergül !
     *  ( depth-et nem nézzük, hiszen az az Aszteroida hívta meg rajta, amin el van helyezve, és őt biztosan elérte a napszél hatása ekkor)
     */
    public void sufferSolarstorm(int depth, AsteroidRegistry hit) {
    	if (!crazy) {
    		crazy = true;
            System.out.println(CommandDatabase.getTeleportID(this) + " went crazy");
    	}
    }

    /**
     * Ha a teleport megkergült állapotban van, akkor át próbál mozogni egy aszteroidára (moveToNeighbor())
     */
	@Override
	public void step() {
		if (crazy) {
			moveToNeighbor();
		}
	}

	/**
	 * Ha le van helyezve, akkor hozzá próbálja adni a paraméterként kapott Transportert a hozzá tartozó Asteroidhoz, ami igazzal tér vissza, ha nem szomszédja már asteroidnak, egyébként hamisat.
	 * Ha nincsen lehelyezve, akkor hamisat.
	 */
	@Override
	public boolean addNeighbor(Transporter t) {
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("Teleport ").append(CommandDatabase.getTeleportID(this)).append('\n');
		if (placed)
			sb.append("\tAsteroid: ").append(CommandDatabase.getAsteroidID(asteroid)).append('\n');
		else
			sb.append("\tHolder: ").append(CommandDatabase.getSettlerID(holder)).append('\n');
		
		sb.append("\tPair: ").append(CommandDatabase.getTeleportID(pair));
		
		return sb.toString();
	}
}
