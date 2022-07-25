package tau.asteroidgame;

/**
 * Amennyiben van elég anyag az adott telepesnél, A TeleportResourceRegistry visszajelzése alapján elkészíti a teleportot, 
 * a telepesnek odaadja a teleport-párt és levonja a felhasznált nyersanyagokat a telepes leltárjából.
 */
public class TeleportRecipe extends Recipe {

    /**
     *Amennyiben van elég anyag az adott telepesnél, A TeleportResourceRegistry visszajelzése alapján elkészíti a teleportot,
     *a telepesnek odaadja a teleport-párt és levonja a felhasznált nyersanyagokat a telepes leltárjából.
     */
    @Override
    public boolean craft(Settler s) {
        // s.getTeleportSize() > 1 , így elvileg max 3 teleportja lehet majd
        if(s.getTeleportSize() > 1){
        	Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.CRAFT_TELEPORT, null));
            return false;
        }
        ResourceRegistry resourceRegistry = new TeleportResourceRegistry();

        Inventory inv = s.getInventory();
        resourceRegistry.register(inv);
        boolean suc = resourceRegistry.isSuccessful();
        if(!suc){
        	Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.CRAFT_TELEPORT, null));
            return false;
        }
        
        resourceRegistry.removeRequiredMaterials(inv);

        Teleport t1 = new Teleport(s);

        Game.INSTANCE.addEvent(new GameEvent(GameEvent.Type.CRAFT_TELEPORT, new Teleport[] {t1, t1.getPair()}));
        return true;
    }
}
