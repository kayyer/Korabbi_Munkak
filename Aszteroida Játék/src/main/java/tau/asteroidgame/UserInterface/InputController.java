package tau.asteroidgame.UserInterface;

import tau.asteroidgame.*;
import tau.asteroidgame.Util.CommandDatabase;
import tau.asteroidgame.Util.NewGameGenerator;

import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;


/**
 * Singleton, ami Inputok feldolgozását kezeli, mint pl.
 * 1. (Enter Menu, Quit, Start Game miután megadtuk a játékosok listáját)
 * 2. Körben jövő Settler akciójának meghatározása után azok érvényesítése, Kör futtatása ezt követően !
 * 4. Adott játékbeli eseményekre történő reagálás kör futás után. (pl. a13 aszteroida fölrobbant !)
 * 3. Visszatérés a főmenübe játék vége esetén.
 */
public class InputController implements ActionListener {
	/** Fő keret, ami segítségével popup üzenetet jeleníthetünk meg, valamint rajta keresztül kérjük le a játékosok listáját új játék kezdésekor */
    private MainFrame mainFrame = null;
    /** Körben jövő Settler, akinek akcióját végre hajtjuk */
    private Settler currentSettler = null;
    /** Single Instance, mivel Singleton */
    private static InputController INSTANCE = null;

    private InputController(){ }

    /** Beállítja a fő keretet, melyhez a popup üzeneteket kéri, valamint rajta keresztül kérjük le a játékosok listáját új játék kezdésekor */
    public void setMainFrame(MainFrame mf){
        mainFrame = mf;

    }
    
    /** InputController singleton egyetlen példányánát adja vissza */
    public static InputController getInstance(){
        if(INSTANCE == null)
            INSTANCE = new InputController();
        return INSTANCE;
    }
    
    
    /**
     * Inputok feldolgozását kezeli, mint pl.
     * 1. (Enter Menu, Quit, Start Game miután megadtuk a játékosok listáját)
     * 2. Körben jövő Settler akciójának meghatározása után azok érvényesítése, Kör futtatása ezt követően !
     * 4. Adott játékbeli eseményekre történő reagálás kör futás után. (pl. a13 aszteroida fölrobbant !)
     * 3. Visszatérés a főmenübe játék vége esetén.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	String command = e.getActionCommand();
    	// Főmenübe történő visszatérés vagy kilépés. Ekkor nem futtatunk tovább kört.
        if (command.startsWith("enterMenu") || command.startsWith("quit"))
        {
        	if (command.startsWith("enterMenu")) {
                mainFrame.switchPanel(MainFrame.Panels.MAINMENU);
            } else {
                quit();
            }
        }
        // Egyébként új játék létrehozása, Settler-re vonatkozó interakció után kört futtatunk !
        else
        {
        	if (command.startsWith("startGame")) {
        	    List<NewGamePanel.Player> players = ((NewGamePanel) mainFrame.getPanel(MainFrame.Panels.NEWGAME)).getPlayerList();
        	    if(players.isEmpty()){
                    mainFrame.displayMessage("Not enough players!");
                    return;
                }else {
                    NewGameGenerator.generateNewGame(players);
                    mainFrame.switchPanel(MainFrame.Panels.INGAME);
                }
        	} else if (command.startsWith("mine")) {
                currentSettler.mine();
            } else if (command.startsWith("drill")) {
                currentSettler.drill();
            } else if (command.startsWith("placeItem_")) {
                Material m = CommandDatabase.getMaterial(e.getActionCommand().substring(10));
                currentSettler.placeMaterial(m);
            } else if (command.startsWith("placeTeleport_")) {
                Teleport t = CommandDatabase.getTeleport(e.getActionCommand().substring(14));
                currentSettler.placeTeleport(t);
            } else if (command.startsWith("move_")) {
                currentSettler.move(CommandDatabase.getTransporter(command.substring(5)));
            } else if (command.startsWith("craftRobot")) {
                currentSettler.craft(new RobotRecipe());
            } else if (command.startsWith("craftTeleport")) {
            	currentSettler.craft(new TeleportRecipe());
            }

        	// Kör futtatása, soron jövő settler megkapáása
            Settler settlerToStep = Game.INSTANCE.step();

            // Különböző kör futtatás után előforduló események lekezelése.
            while (Game.INSTANCE.hasEvent()) {
                GameEvent event = Game.INSTANCE.getEvent();
                GameEvent.Type eventType = event.getType();
                if (eventType == GameEvent.Type.SETTLER_DIED) {
                    mainFrame.displayMessage("Settler " + CommandDatabase.getSettlerID((Settler) event.getSubject()) + " Has Died");
                } else if (eventType == GameEvent.Type.SETTLER_MOVED) {
                	Asteroid moved = (Asteroid) event.getSubject();
                    mainFrame.displayMessage(moved != null ? ("Successfully moved to asteroid " + CommandDatabase.getAsteroidID(moved)) :
                    										  "Failed to move to asteroid");
                } else if (eventType == GameEvent.Type.SETTLER_PLACED_MATERIAL) {
                	Material placedMaterial = (Material) event.getSubject();
                	if (placedMaterial != null)
                		mainFrame.displayMessage("Successfully placed " + placedMaterial + " material");
                	else
                		mainFrame.displayMessage("Failed to place material !");
                } else if (eventType == GameEvent.Type.MINED) {
                	Material minedMat = (Material) event.getSubject();
                    mainFrame.displayMessage(minedMat == null ? "Mining failed!" : "Successfully mined " + minedMat +"!");
                } else if (eventType == GameEvent.Type.CRAFT_ROBOT) {
                	Robot craftedRobot = (Robot) event.getSubject();
                	if (craftedRobot != null) {
                		mainFrame.displayMessage("Crafted Robot successfully");
                		CommandDatabase.registerRobot(craftedRobot);
                		ImageIcon rIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/robot.png")).getImage().getScaledInstance(IconConstraints.workerIconSize, IconConstraints.workerIconSize, Image.SCALE_SMOOTH));
                		WorkerViewControl rvc = new WorkerViewControl(craftedRobot, rIcon);
                		ViewControlDatabase.register(craftedRobot, rvc);
                	}
                	else {
                		mainFrame.displayMessage("Failed to craft robot");
                	}
                } else if (eventType == GameEvent.Type.CRAFT_TELEPORT) {
                	Teleport[] craftedTeleports = (Teleport[]) event.getSubject();
                	if (craftedTeleports != null) {
                		mainFrame.displayMessage("Crafted Teleport successfully");
                		
                		Teleport t1 = craftedTeleports[0];
                		CommandDatabase.registerTeleport(t1);
                		TeleportViewControl tvc1 = new TeleportViewControl(t1);
                		ViewControlDatabase.register(t1, tvc1);
                		
                		Teleport t2 = craftedTeleports[1];
                		CommandDatabase.registerTeleport(t2);
                		TeleportViewControl tvc2 = new TeleportViewControl(t2);
                		ViewControlDatabase.register(t2, tvc2);
                	}
                	else {
                		mainFrame.displayMessage("Failed to craft Teleport");
                	}
                } else if (eventType == GameEvent.Type.GAME_WON) {
                    mainFrame.displayMessage("Base Built,Game Won! Returning To Main Menu");
                } else if (eventType == GameEvent.Type.GAME_LOST) {
                    mainFrame.displayMessage("Game Lost! Returning To Main Menu");
                } else if (eventType == GameEvent.Type.ASTEROID_EXPLODED) {
                    mainFrame.displayMessage("Asteroid " + CommandDatabase.getAsteroidID((Asteroid) event.getSubject()) + " Has Exploded");
                } else if (eventType == GameEvent.Type.SUNSTORM) {
                    mainFrame.displayMessage("A Sun Storm Has Hit The Asteroid Field");
                }
            }
            
            // Ha nincs vége a játéknak, frissítjük az InGamePanelt.
            if (!Game.INSTANCE.getIsOver() && settlerToStep != null) {
                InGamePanel ig = (InGamePanel) mainFrame.getPanel(MainFrame.Panels.INGAME);
                ig.update(settlerToStep);
                currentSettler = settlerToStep;
            } else {
                mainFrame.switchPanel(MainFrame.Panels.MAINMENU);
            }


        }

    }
    
    /** Kilépés a játékból. */
    public void quit(){
        mainFrame.dispose();
    }

}
