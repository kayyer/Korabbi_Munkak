package tau.asteroidgame.UserInterface;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Material;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.Transporter;
import tau.asteroidgame.Worker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static tau.asteroidgame.UserInterface.IconConstraints.*;

/**
 * Az osztály felelőssége az aszteroidamezőt megjelenítő és azzal interakciót
 * biztosító elemek számára a hely biztosítása.
 */
@SuppressWarnings("serial")
public class AsteroidFieldPanel extends JPanel {
	
	/**
	 * Aszteroidamezőbeli elemek (pl. Aszteroida, Munkás) megjelenítésére használt gombokat biztosító pool.
	 */
	private class ButtonPool {
		/** Kiosztásra használt gombok listája */
		private List<JButton> buttonPool = new ArrayList<JButton>();
		/** Kiosztott gombok száma */
		int usedButtonCounter = 0;
		
		/**
		 * Gombot oszt ki
		 * */
		public JButton getButton() {
			if (usedButtonCounter >= buttonPool.size() ) {
				usedButtonCounter++;
				JButton button = new JButton();
				button.addActionListener(InputController.getInstance());
				button.setOpaque(false);
				button.setContentAreaFilled(false);
				button.setBorderPainted(false);
				buttonPool.add(button);
				return button;
			}
			return buttonPool.get(usedButtonCounter++);
		}
		
		/**
		 * Kiosztásokat megszünteti.
		 */
		public void reset() {
			usedButtonCounter = 0;
			for (JButton button : buttonPool)
				remove(button);
		}
	}

    /**
     * A telepeshez tartozó aszteroida és kifúrt esetben annak magjának kirajzolására,
     * valamint adott mennyiségű szomszédok kirajzolására használt gombhalmaz, mely mérete bővítve van
     * abban az esetben, ha nincs elég gomb az ábrázolásra túl nagy szomszédság esetén.
     */
    private ButtonPool buttonPool = new ButtonPool();
   
    /**
     * Ablak középpontja, melynél a jelenlegi telepeshez tartozó asteroid van megjelenítve.
     * Referenciaként használják a szomszédos aszteroidák pozíciójuk meghatározására.
     */
    private Point center = null;


    /**
     * Eltávolítja az összes eddig kirajzolt gombot, majd úgy rajzol,
     * hogy a középpontban a telepeshez tartozó nem klikkelhető Asteroid van.
     * E köré kör-körösen megjelennek a szomszédos aszteroidák, melyek klikkelhetőek
     * és klikkelés hatására a hozzájuk történő mozgást
     * @param s
     */
    public void update(Settler s){
        buttonPool.reset();
        Asteroid a = s.getAsteroid();
        AsteroidViewControl avc = ViewControlDatabase.getAsteroidViewControl(a);

        Dimension winSize = this.getSize();
        
        center = new Point(winSize.width / 2, winSize.height / 2);
        
        JButton b = buttonPool.getButton();
        this.add(b);
        b.setBounds(center.x - asteroidIconSize / 2, center.y - asteroidIconSize / 2, asteroidIconSize, asteroidIconSize);
        avc.draw(b); b.setEnabled(false);
        if (a.isExposed()) {
        	Material core = a.getCore();
        	if (core != null) {
        		JButton coreB = buttonPool.getButton();
        		this.add(coreB);
        		b.setBounds(center.x - itemIconSize / 2, center.y - itemIconSize / 2, itemIconSize, itemIconSize);
        		ViewControlDatabase.getMaterialViewControl(core).draw(coreB);
        	}
        }
        
        
        
        drawWorkersAroundButton(b, a.getWorkers());
        
        List<Transporter> neighbors = a.getNeighbors();
        drawNeighborsAroundCenter(neighbors);
    }

    
    /**
     * Körkörösen, véletlenszerű szögtől kezdve kirajzolja a szomszédokat a középpont körül, 
     * valamint maga köré a rajta lévő munkásokat drawWorkersAroundButton() segítségével
     * @param neighbors Kirajzolandó szomszédok.
     */
    private void drawNeighborsAroundCenter(List<Transporter> neighbors) {
    	int neighborSize = neighbors.size();
    	double startDeg = new Random().nextDouble() * 2 * Math.PI;
    	double degIncr = 2 * Math.PI / neighborSize;
    	
    	for (int i = 0; i < neighborSize; i++) {
    		JButton b = buttonPool.getButton();
    		this.add(b);
    		b.setBounds(center.x + (int)(3 * asteroidIconSize * Math.cos(startDeg + i * degIncr) - asteroidIconSize / 2),
    					center.y + (int)(3 * asteroidIconSize * Math.sin(startDeg + i * degIncr) - asteroidIconSize / 2),
    				    asteroidIconSize, asteroidIconSize);
    		
    		Transporter t = neighbors.get(i);
    		if (t instanceof Asteroid) {
    			ViewControlDatabase.getAsteroidViewControl((Asteroid) t).draw(b);
    			drawWorkersAroundButton(b, ((Asteroid) t).getWorkers());
    		}
    		else
    			ViewControlDatabase.getTeleportViewControl((Teleport) t).draw(b);
    	}
    }
    
    /**
     * Körkörösen, véletlenszerű szögtől kezdve kirajzolja a munkásopkat az adott, aszteroidát reprezentáló gomb körül. 
     * @param jb Adott aszteroidát reprezentáló gömb.
     * @param workers Kirajzolandó munkások
     */
    private void drawWorkersAroundButton(JButton jb, List<Worker> workers) {
    	Point loc = jb.getLocation();
    	
    	int workerSize = workers.size();
    	double startDeg = new Random().nextDouble() * 2 * Math.PI;
    	double degIncr = 2 * Math.PI / workerSize;
    	
    	for (int i = 0; i < workerSize; i++) {
    		JButton b = buttonPool.getButton();
    		this.add(b);
    		b.setBounds(loc.x + asteroidIconSize / 2 + (int)(0.8 * asteroidIconSize * Math.cos(startDeg + i * degIncr) - workerIconSize / 2),
    					loc.y + asteroidIconSize / 2 + (int)(0.8 * asteroidIconSize * Math.sin(startDeg + i * degIncr) - workerIconSize / 2),
    				    workerIconSize, workerIconSize);
    		ViewControlDatabase.getWorkerViewControl(workers.get(i)).draw(b);
    	}
    }

    public AsteroidFieldPanel() {
        super();
        setPreferredSize(new Dimension(900, 450));
        setLayout(new BorderLayout(0,0));
        setOpaque(false);


        setVisible(true);

    }

}
