package tau.asteroidgame;

public class GameEvent {
	public enum Type {
		SETTLER_DIED,
		SETTLER_MOVED,
		SETTLER_PLACED_MATERIAL,
		MINED,
		CRAFT_TELEPORT,
		CRAFT_ROBOT,
		GAME_WON,
		GAME_LOST,
		ASTEROID_EXPLODED,
		SUNSTORM
	}
	private Type type;
	private Object subject;
	
	public GameEvent(Type type, Object subject) {
		this.type = type;
		this.subject = subject;
	}
	
	public Type getType() { return type; }
	public Object getSubject() { return subject; }

}
