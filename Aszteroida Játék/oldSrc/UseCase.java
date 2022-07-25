package tau.asteroidgame.Util;

public interface UseCase {
	/**
	 * A tesztesethez szükséges objektumok inicializálása (kommunikációs diagramról)
	 */
	public void init();
	/**
	 * A tesztesethez futtatása (szekvencia diagramról)
	 */
	public void run();
}
