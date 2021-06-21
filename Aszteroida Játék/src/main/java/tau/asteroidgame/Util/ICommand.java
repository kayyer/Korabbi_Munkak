package tau.asteroidgame.Util;

/**
  *	Parancsként a kapott paraméterek alapján hajt végre utasításokat, melyek hatással vannak a játék állapotára.
*/ 
public interface ICommand {
	/**
	 * Kapott paraméterek alapján végre hatja utasításait, melyek hatással vannak a játék 	állapotára.
	 * Hibás/Hiányos paraméter esetén kiírja a hibát a Prototípus koncepciónak 	megfelelő módon. 
	 */
	public void run(String[] param);
}
