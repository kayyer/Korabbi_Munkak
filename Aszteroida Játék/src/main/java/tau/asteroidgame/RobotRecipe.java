package tau.asteroidgame;



/**
 *  Amennyiben van elég anyag az adott telepesnél, elkészíti a robotot és felhasználja hozzá a szükséges nyersanyagokat, vagyis törli a barkácsolást végző telepes leltárjából. 
 *
 */
public class RobotRecipe extends Recipe {
	
	public RobotRecipe() {}
	
    /**
     * Egy robot elkészítésekor végbemenő folyamatokat elindítja.
     * Ha a paraméterként kapott telepes leltárjában van legalább 1 Iron, 1 Coal, és 1 Uranium,
     * akkor robotot létrehozva elhelyezi azt azon az aszteroidán, amin a telepes van, eltávolítja a fölhasznált nyersanyagokat a telepes leltárjából, és igazzal tér vissza.
     * Egyébként nem csinál semmit, és hamissal tér vissza. 
     *
     * @param s Barkácsoló telepes
     * @return Sikerült e a barkácsolás
     */
    @Override
    public boolean craft(Settler s) {
        ResourceRegistry resourceRegistry = new RobotResourceRegistry();
        
        Inventory inv = s.getInventory();
        
        resourceRegistry.register(inv);
        
        boolean suc = resourceRegistry.isSuccessful();
        
        if(!suc){
        	Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.CRAFT_ROBOT, null));
            return false;
        }
        
        resourceRegistry.removeRequiredMaterials(inv);

        Asteroid a = s.getAsteroid();
        Robot robot = new Robot(a);

        Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.CRAFT_ROBOT, robot));
        return true;
    }
}
