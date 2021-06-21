package tau.asteroidgame;


import static tau.asteroidgame.Util.CommandDatabase.getAsteroidID;

/**
 * Az űrmunkás egy általánosítás, mint a játékban tevékenykedő telepesek/robotok
 * ősosztálya. Deklarálja a telepesek és robotok akcióit, mint fúrás, halál, mozgás és ami
 * még történhet velük, hogy felrobbannak vagy lekérdezik a leltár tartalmát.
 */
public abstract class Worker implements Steppable {
    private Asteroid asteroid;
    /**
     * Az űrmunkás halála esetén végbemenő folyamatokat indítja el.
     */
    public abstract void die();

    /**
     * Az űrmunkás a paraméterben kapott szállítóra lép, ez lehet teleport vagy aszteroida.
     * @param t A szállító, ahova a munkás lépne
     */
    public void move(Transporter t) {
    	Asteroid curr = asteroid;
    	if(t.acceptWorker(this)) {
    		curr.removeWorker(this);
            System.out.println(getName()+" move "+getAsteroidID(asteroid));
    	}else{
            System.out.println("Moving failed!");
        }
    }

    /**
     * Az űrmunkás felrobbanásakor végbemenő folyamatokat indítja el.
     */
    public abstract void explode();

    /**
     * Az űrmunkás fúrás folyamatát indítja el.
     */
    public void drill() {
    	if (!asteroid.drilled())
    		System.out.println("Drilling failed: Asteroid fully drilled!");
    	else {
    		System.out.println("Layers left: " + asteroid.getRemainingLayer());
    	}
    }

    /**
     * Visszaadja az űrmunkáshoz tartozó aszteroidát
     * @return Az űrmunkáshoz tartozó aszteroidát
     */
    public Asteroid getAsteroid() {
        return asteroid;
    }

    /**
     * Beállítja az űrmunkás aszteroidáját
     * @param asteroid Az űrmunkás új aszteroidája
     */
    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    /**
     * Visszatér az űrmunkás leltárával.
     * Figyelem: Ez alapértelmezetten nem definiált!
     * @return Az űrmunkás leltára
     */
    public abstract Inventory getInventory();

    public abstract String getName();
}
