	package tau.asteroidgame;


import static tau.asteroidgame.Util.CommandDatabase.getAsteroidID;

import java.util.*;

import tau.asteroidgame.Util.CommandDatabase;
import tau.asteroidgame.Util.Input;

public class Settler extends Worker {

    private Inventory inventory = new SettlerInventory();
    private static Recipe[] recipes = new Recipe[]{new RobotRecipe(), new TeleportRecipe()};
    private List<Teleport> teleports = new ArrayList<>();

    public Settler(Asteroid a){
        Game.INSTANCE.addSettler(this);
        a.acceptWorker(this);
    }
    /**
     *A jatekos minden lepesben eldontheti mit szeretne csinalni a telepessel
     */
    public void step() {
    	/*
        Asteroid a = getAsteroid();
        int choice = 0;
        int innerChoice = -1;
        
        
        System.out.println("Mit lépsz?");
        do{
        	choice = Input.getUnsignedIntInput("1.Mozgás\n2.Fúrás\n3.Bányászás\n4.Teleport elhelyezése\n5.Kraftolás\n6.Kilépés");
        } while (choice < 7 || choice < 1);
        switch(choice){
        	case 1:
        		List<Transporter> options = a.getNeighbors();
                if(options.isEmpty())
                	break;
                    
                do {
                	innerChoice = Input.getUnsignedIntInput("Válassz egy számot 1 és " + options.size() + " között");
                } while(innerChoice < 1 || innerChoice > options.size());
                move(options.get(innerChoice - 1));
                break;
            case 2:
                drill();
                break;
            case 3:
            	mine();
                break;
            case 4:
            	placeTeleport();
            	break;
            case 5:
            	innerChoice = -1;
                do {
                	innerChoice = Input.getUnsignedIntInput("Melyik receptet szeretnéd elkészíteni?\n1.Robot\n2.Teleport");
                }while(innerChoice != 1 || innerChoice != 2);
                if(innerChoice == 1)
                {
                	craft(recipes[0]);
                }
                else{
                	craft(recipes[1]);
                }
                break;
            case 6:
            default:
            	System.exit(0);
        }
        System.out.println("-------------------------");
        */
    	Game.INSTANCE.setSettlerToStep(this);
    }

    /**
     *  A telepes eltávolítatja magát az aszteroidáról, amin van, a Game nyilvántartásából,
     *  és elpusztítja a nála lévő teleportokat, amennyiben vannak.
     */
    @Override
    public void die() {
        System.out.println("settler "+ CommandDatabase.getSettlerID(this) + " died");
        while(!teleports.isEmpty()) {
                teleports.get(0).destroy();
        }
        
        Asteroid asteroid = getAsteroid();
        asteroid.removeWorker(this);
        Game.INSTANCE.removeSettler(this);
        
        Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.SETTLER_DIED, this));
    }
    
    @Override
    public void move(Transporter t) {
    	Asteroid old = getAsteroid();
    	super.move(t);
    	Asteroid current = getAsteroid();
    	Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.SETTLER_MOVED, old != current ? current : null));
    }

    /**
     *  Robbanáskor meghal, így csak meghívódik a die() metódus.
     */
    @Override
    public void explode() {
        System.out.println("settler "+ CommandDatabase.getSettlerID(this) + " explode");
        die();
    }

    /**
     * A telepes bányászási akcióját valósítja meg, vagyis meghívja annak az aszteroidának mined(s: Settler) függvényét, amin éppen rajta van.
     * @return A művelet sikerességét jelző logikai érték
     */
    public boolean mine() {
        Asteroid asteroid = getAsteroid();
        if(asteroid.mined(inventory)) {
        	List<Material> matList = inventory.getMaterials();
        	Material minedMat = matList.get(matList.size() - 1);
        	System.out.println(minedMat);
        	Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.MINED, minedMat));
        	return true;
        }
        System.out.println("Mining failed!");
        Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.MINED, null));
        return false;
    }

    /**
     *  A telepes befogad egy teleportot amit később elhelyezhet. Igazzal tér vissza, ha még nem volt nála 2 teleport, egyébként hamissal.
     * @param t : Teleport
     * @return A művelet sikerességét jelző logikai érték
     */
    public boolean acceptTeleport(Teleport t) {
        if(hasTeleport() && (teleports.size() > 2)){
            return false;
        }
        teleports.add(t);
        t.setHolder(this);
        return true;
    }

    /**
     * A telelportkapu-pár egyik példányának megsemmisülésekor ez a függvény
     * távolítja el a telepes zsebéből az érvénytelen teleporkaput.
     * (Az egyiket már letették, de a másik még a telepes zsebében van)
     * @param t : Teleport
     */

    public void removeTeleport(Teleport t) {
        teleports.remove(t);
    }

    /**
     * A telepes megkapja az aszteroidától az m paraméterben kapott anyagot. Amennyiben
     * túl sok anyag van nála, hamisat ad vissza, és nem tárolja el az anyagot, egyébként
     * eltárolja, és igazat ad vissza.
     * @param m : Material
     * @return A művelet sikerességét jelző logikai érték
     */
    public boolean acceptMaterial(Material m) {
    	return inventory.addMaterial(m);
    }

    /**
     * Ellenőrzi, hogy a telepes leltárja megtelte e
     * @return Igazat ad vissza, ha a telepes leltára tele van.
     */
    public boolean isInventoryFull() {
        return inventory.isFull();
    }

    /**
     *A telepes ezen függvény meghívásával tud barkácsolni a paraméterként kapott
     *Recept segítségével (meghívja rajta a craft(s: Settler) metódust). Sikeres barkácsolás
     *esetén igazat ad vissza, egyébként hamisat
     * @param r : Recipe A barkácsolandó recept
     * @return A művelet sikerességét jelző logikai érték
     */
    //
    public boolean craft(Recipe r) {
        return r.craft(this);
    }

    /**
     * A telepes le próbál tenni egy m paraméterben kapott anyagot az aszteroida magjába.
     * Ha ez sikerült, igazzal tér vissza, egyébként hamissal.
     * @param m Az elhejezendő nyersanyag
     * @return A művelet sikerességét jelző logikai érték
     */
    //
    public boolean placeMaterial(Material m) {
        Asteroid asteroid = getAsteroid();
        if(asteroid.acceptMaterial(m))
        {
            inventory.removeMaterial(m);
            Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.SETTLER_PLACED_MATERIAL, m));
            return true;
        }
        Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.SETTLER_PLACED_MATERIAL, null));
        return false;
    }

    /**
     *
     *  A telepes megpróbál letenni egy teleportkaput az aszteroidára. Ha ez sikerült
     *  (volt nála legalább 1 teleport), igazzal tér vissza, egyébként hamissal
     * @return A művelet sikerességét jelző logikai érték
     */
    public boolean placeTeleport() {
        Asteroid asteroid = getAsteroid();

        if(!teleports.isEmpty())
        {
            teleports.get(0).place(asteroid);
            teleports.remove(0);
            return true;
        }
        return false;
    }
    
    /**
    *
    *  A telepes megpróbálja letenni a paraméterben kapott teleportkaput az aszteroidára. Ha ez sikerült
    *  (volt nála legalább a paraméterben kapott teleport), igazzal tér vissza, egyébként hamissal
    * @return A művelet sikerességét jelző logikai érték
    */
   public boolean placeTeleport(Teleport t) {
       if(teleports.contains(t))
       {
           t.place(getAsteroid());
           teleports.remove(t);
           return true;
       }
       return false;
   }

    /**
     * Visszaadja, hogy van-e a telepesnél teleport, ami nincs lerakva
     * @return Igazat ad vissza, ha van a telepesnél legalább egy Teleport
     */
    public boolean hasTeleport() {
        return !teleports.isEmpty();
    }

    public int getTeleportSize(){
        return teleports.size();
    }
    
    public List<Teleport> getTeleports() {
    	return teleports;
    }
    
    @Override
    public Inventory getInventory() {
        return inventory;
    }


    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder().append("Settler ").append(CommandDatabase.getSettlerID(this)).append('\n')
    	.append("\tAsteroid: ").append(CommandDatabase.getAsteroidID(getAsteroid())).append('\n')
    	.append("\tInventory: ");
    	List<Material> mats = inventory.getMaterials();
    	for (Material m : mats)
    		sb.append(m.toString()).append(' ');
    	
    	return sb.toString();
    }

    @Override
    public String getName() {
        return "settler";
    }
}
