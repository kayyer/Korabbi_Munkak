package tau.asteroidgame;

import java.util.ArrayList;
import java.util.List;

/**
 *Az osztály felelősége a játék irányítása, a körök vezérlése és a játékosok/telepesek
 * Nyilvántartása. Ha az összes telepes meghalt, akkor véget vet a játéknak vesztéssel.
 */
public enum Game {
    INSTANCE;
	

    private final List<Steppable> steppables= new ArrayList<>();
    private final List<Settler> settlers = new ArrayList<>();
    private AsteroidField field;
    private boolean deterministic = false;
    
    private List<GameEvent> events = new ArrayList<GameEvent>();
    
    public boolean hasEvent() { return !events.isEmpty(); }
    public GameEvent getEvent() {
    	if (hasEvent()) {
    		GameEvent event = events.remove(events.size() - 1);
    		return event;
    	}
    	return null;
    }
    public void addEvent(GameEvent event) { events.add(event); }
    
    /**
     * Visszaadja a jelenleg érvényes aszteroidamezőt.
     */
    public AsteroidField getField() {
    	return field;
    }
    
    /**
     * Jelzi, hogy vége van e a játéknak.
     */
    private boolean isOver = false;

    public boolean getIsOver(){return isOver;}
    
    
    /**
     * Beállítja az érvényes aszteroidamezőt. 
     */
    public void setField(AsteroidField af) {
    	if (field != null)
    		steppables.remove(field);
    	field = af;
    }
    
	/**
	 * Visszaadja, hogy determinisztikus mód van e beállítva vagy nem.
	 */
	public boolean isDeterministic() {
		return deterministic;
	}
	
	/**
	 * Beállítja, hogy determinisztikus mód legyen e beállítva vagy nem.
	 */
	public void setDeterministic(boolean val) {
		deterministic = val;
	}

    /**
     * Egy kört megvalósítva meghív minden léptethető elemet és lépteti azokat.
     */
    public void round() {
    	for (Steppable s : steppables)
    		s.step();
    }
    
    private Settler settlerToStep = null;

    
	void setSettlerToStep(Settler settler) {
		settlerToStep = settler;
	}
	
    private int stepIndex = 0;
    

    public Settler step() {
    	if (settlerToStep == null)
    		stepIndex = 0;
    	else
    		stepIndex++;
    	
    	settlerToStep = null;
    	while (!settlers.isEmpty()) {
    		while (stepIndex < steppables.size()) {
    			steppables.get(stepIndex).step();
    			if (settlerToStep != null)
    				return settlerToStep;
    		
    			stepIndex++;
    		}
    		stepIndex = 0;
    	}
    	return null;
    }
    
    /**
     *  Addig játszatja a köröket, amíg nincs vége a játéknak.
     */
    public void play() {
    	isOver = false;
    	while (!isOver) {
    		round();
    	}
    }

    /**
     * Ha a kapott paraméter igaz, akkor sikerrel fejezi a játékot, egyébként vesztéssel.
     * @param b Logikai érték az alapján, hogy a játékot a telepesek nyerték-e vagy sem.
     */
    public void endGame(boolean b) {
    	isOver = true;
    	
        if(b){
        	System.out.println("Settlers Won!");
        }else{
            System.out.println("Settlers Lost!");
        }
    }

    /**
     * Eltávolít egy léptethető elemet a léptethető elemek listájából.
     * @param s Az eltávolítandó Steppable
     */
    public void removeSteppable(Steppable s) {
        steppables.remove(s);
        stepIndex--;
    }

    /**
     * Eltávolít egy telepetess a telepesek listájából.
     * @param s Az eltávolítandó telepes
     */
    public void removeSettler(Settler s) {
        removeSteppable(s);
        settlers.remove(s);
        if(settlers.size() < 1){
            endGame(false);
        }
    }

    /**
     * Felvesz egy telepest a telepesek listájába.
     * @param s A hozzáadandó telepes
     */
    public void addSettler(Settler s) {
        addSteppable(s);
        settlers.add(s);
        
        /**
         * A van legalább 1 settler beregisztrálva, akkor el lehet kezdeni a játékot.
         */
        if (settlers.size() == 1)
        	isOver = false;
    }

    /**
     * Felvesz egy léptethető elemet a léptethető elemek listájába.
     * @param s A hozzáadandó Steppable
     */
    public void addSteppable(Steppable s) {
        steppables.add(s);
    }
    
    public void reset() {
    	steppables.clear();
    	field = null;
    	settlerToStep = null;
    }

}


