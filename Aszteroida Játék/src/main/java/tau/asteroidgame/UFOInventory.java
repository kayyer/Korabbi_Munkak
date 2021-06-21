package tau.asteroidgame;

/**
 * Korlátlan mennyiségű anyag tárolására képes, sosem telik meg.
 */
public class UFOInventory extends Inventory{
    /**
     * Mindig hamissal tér vissza (végtelen hely van benne)
     * @return Igaz érték
     */
    @Override
    public boolean isFull() {
        return false;
    }
}
