package tau.asteroidgame.UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Inventory;
import tau.asteroidgame.Material;
import tau.asteroidgame.RobotResourceRegistry;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.TeleportResourceRegistry;
import tau.asteroidgame.Util.CommandDatabase;

/**
 * Az osztály felelőssége a telepesről információt közlő és irányítására szolgáló elemek számára a hely biztosítása. 
 */
public class SettlerInfoPanel extends JPanel {
	/** A gomb, amit ha a játékos lenyom, a telepes fúr egy aszteroidában */
	private MenuButton drillButton;
	/** A gomb, amit ha a játékos lenyom, a telepes bányászik az aszteroidából */
	private MenuButton mineButton;
	/** A gomb, amit ha a játékos lenyom, a telepes karkácsol egy robotot és le helyezi az aszteroidára */
	private MenuButton craftRobotButton;
	/** A gomb, amit ha a játékos lenyom, a telepes barkácsol egy teleportkapu párt */
	private MenuButton craftTeleportButton;
	/** Azok a gombok, amik egy-egy nyersanyagot jelképeznek. Ha a játékos rányom, a 	telepes lehelyezi a kiválasztott nyersanyagot az aszteroidába  */
	private JButton[] inventoryItemButton;
	/** Azok a gombok, amik egy-egy nyersanyagot jelképeznek. Ha a játékos rányom, a 	telepes lehelyezi a kiválasztott nyersanyagot az aszteroidába  */
	private JButton[] teleportItemButton;
	/** Kiírja az adott telepes CommandDatabase-ben bejegyzett nevét */
	private JLabel settlerNameLabel;
	/** Ide írja ki a játékos számára, hogy eddig mennyire van kifúrva az aszteroida, amin 	éppen áll  */
	private JLabel layersDrilledLabel;
	
	
	public SettlerInfoPanel() {
		this.setPreferredSize(new Dimension(850, 150));
		
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 4);
		Border grayItemBorder = BorderFactory.createLineBorder(new Color(77, 77, 77), 2);
		Border turquoiseItemBorder = BorderFactory.createLineBorder(new Color(2, 169, 138), 2);
		
		Color blueBackgroundColor = new Color(128, 179, 255);
		Color purpleBackgroundColor = new Color(153, 85, 255);
		
		Color grayBackgroundColor = new Color(111, 124, 145);
		Color turquoiseBackgroundColor = new Color(85, 255, 221);
		
		Dimension leftPanelButtonDimension = new Dimension(245, 39);
		Dimension inventoryItemDimension = new Dimension(40, 40);
		Dimension teleportItemDimension = new Dimension(30, 30); 
		
		Font font = MainFrame.starGuardianFont.deriveFont(25.0F);
		
		this.setBackground(new Color(128, 179, 255));
		this.setBorder(BorderFactory.createLineBorder(new Color(4, 88, 213), 4));
		
		drillButton = new MenuButton("DRILL"); drillButton.setPreferredSize(leftPanelButtonDimension);
		drillButton.setActionCommand("drill"); drillButton.addActionListener((ActionListener) InputController.getInstance());
		drillButton.setFont(font);
		
		mineButton = new MenuButton("MINE"); mineButton.setPreferredSize(leftPanelButtonDimension);
		mineButton.setActionCommand("mine"); mineButton.addActionListener((ActionListener) InputController.getInstance());
		mineButton.setFont(font);
		
		craftRobotButton = new MenuButton("CRAFT ROBOT"); craftRobotButton.setPreferredSize(leftPanelButtonDimension);
		craftRobotButton.setActionCommand("craftRobot"); craftRobotButton.addActionListener((ActionListener) InputController.getInstance());
		craftRobotButton.setFont(font);
		
		craftTeleportButton = new MenuButton("CRAFT TELEPORT"); craftTeleportButton.setPreferredSize(leftPanelButtonDimension);
		craftTeleportButton.setActionCommand("craftTeleport	"); craftTeleportButton.addActionListener((ActionListener) InputController.getInstance());
		craftTeleportButton.setFont(font);
		
		
		layersDrilledLabel = new JLabel("LAYERS DRILLED: - "); layersDrilledLabel.setPreferredSize(leftPanelButtonDimension); layersDrilledLabel.setBorder(blackBorder);
		layersDrilledLabel.setBackground(grayBackgroundColor);
		layersDrilledLabel.setForeground(Color.BLACK);
		layersDrilledLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layersDrilledLabel.setOpaque(true);
		layersDrilledLabel.setFont(font);
		
		settlerNameLabel = new JLabel("Player-"); layersDrilledLabel.setPreferredSize(leftPanelButtonDimension); settlerNameLabel.setBorder(blackBorder);
		settlerNameLabel.setBackground(grayBackgroundColor);
		settlerNameLabel.setForeground(Color.BLACK);
		settlerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		settlerNameLabel.setOpaque(true);
		settlerNameLabel.setFont(font);
		
		inventoryItemButton = new JButton[10];
		teleportItemButton = new JButton[3];
		
		for (int i = 0; i < 10; i++) {
			JButton b = inventoryItemButton[i] = new JButton();
			b.setPreferredSize(inventoryItemDimension);
			b.setBorder(grayItemBorder);
			b.setBackground(grayBackgroundColor);
			b.addActionListener((ActionListener) InputController.getInstance());
		}
		
		for (int i = 0; i < 3; i++) {
			JButton b = teleportItemButton[i] = new JButton();
			b.setPreferredSize(teleportItemDimension);
			b.setBorder(turquoiseItemBorder);
			b.setBackground(turquoiseBackgroundColor);
			b.addActionListener((ActionListener) InputController.getInstance());
		}
		
		JPanel leftBottomPanel = new JPanel();
		leftBottomPanel.setBackground(blueBackgroundColor);
		GridLayout leftBottomPanelLayout = new GridLayout(2, 2);
		leftBottomPanelLayout.setHgap(13); leftBottomPanelLayout.setVgap(6);
		leftBottomPanel.setLayout(leftBottomPanelLayout);
		
		leftBottomPanel.add(drillButton); leftBottomPanel.add(craftRobotButton); leftBottomPanel.add(mineButton); leftBottomPanel.add(craftTeleportButton);
		
		JPanel leftTopPanel = new JPanel();
		leftTopPanel.setBackground(blueBackgroundColor);
		leftTopPanel.add(settlerNameLabel);
		leftTopPanel.add(layersDrilledLabel);
		
		
		BorderLayout leftEncompassingPanelLayout = new BorderLayout();
		leftEncompassingPanelLayout.setVgap(0); leftEncompassingPanelLayout.setHgap(0);
		JPanel leftPanel = new JPanel(leftEncompassingPanelLayout);
		
		leftPanel.add(leftTopPanel, BorderLayout.NORTH); leftPanel.add(leftBottomPanel, BorderLayout.CENTER);
		leftPanel.setBackground(blueBackgroundColor);
		
		
		GridLayout inventoryItemLayout = new GridLayout(2, 5);
		inventoryItemLayout.setHgap(10); inventoryItemLayout.setVgap(10);
		JPanel rightInventoryPanel = new JPanel(inventoryItemLayout);
		rightInventoryPanel.setBackground(purpleBackgroundColor);
		for (int i = 0; i < 10; i++)
			rightInventoryPanel.add(inventoryItemButton[i]);
		
		GridLayout teleportItemLayout = new GridLayout(3, 1);
		teleportItemLayout.setHgap(10); teleportItemLayout.setVgap(3);
		JPanel rightTeleportPanel = new JPanel(teleportItemLayout);
		rightTeleportPanel.setBackground(purpleBackgroundColor);
		
		for (int i = 0; i < 3; i++)
			rightTeleportPanel.add(teleportItemButton[i]);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(blueBackgroundColor);
		rightPanel.add(rightInventoryPanel);
		rightPanel.add(rightTeleportPanel);
		
		rightPanel.setBackground(purpleBackgroundColor);
		rightPanel.setBorder(BorderFactory.createLineBorder(new Color(51, 0, 128), 5));
		
		FlowLayout layout = (FlowLayout) this.getLayout();
		layout.setHgap(20);
		this.add(leftPanel);
		this.add(rightPanel);
	}
	
	/** Frissíti a panelt és a panel elemeit a paraméterül kapott telepes alapján.  
	 * 
	 * @param s Telepes, aki következik.
	 */
	public void update(Settler s) {
		Asteroid a = s.getAsteroid();
		Inventory inv = s.getInventory();
		boolean exposed = a.isExposed();
		
		// drill/ mine button
		if (exposed) {
			drillButton.setEnabled(false);
			//
			if (a.getCore() != null)
				mineButton.setEnabled(true);
			else
				mineButton.setEnabled(false);
			//
		}
		else {
			drillButton.setEnabled(true);
			mineButton.setEnabled(false);
		}
		
		//layers drilled, name labels
		layersDrilledLabel.setText("LAYERS DRILLED: " + (a.getLayer() - a.getRemainingLayer()));
		settlerNameLabel.setText("  " + CommandDatabase.getSettlerID(s) + "  ");
		// craft teleport/robot button
		TeleportResourceRegistry telResReg = new TeleportResourceRegistry();
		RobotResourceRegistry robResReg = new RobotResourceRegistry();
		
		telResReg.register(inv);
		robResReg.register(inv);
		
		craftTeleportButton.setEnabled(telResReg.isSuccessful() ? true : false);
		craftRobotButton.setEnabled(robResReg.isSuccessful() ? true : false);
		
		
		// Kioltjuk az előző anyag megjelenítést
		for (JButton b : inventoryItemButton) {
			b.setEnabled(false);
			b.setIcon(null);
		}
		// Kioltjuk az előző teleport megjelenítést
		for (JButton b : teleportItemButton) {
			b.setEnabled(false);
			b.setIcon(null);
		}
		
		List<Material> materialList = inv.getMaterials();
		int matCount = materialList.size();
		
		// Az anyagokat, mint item fölrajzoljuk. Ha nincs exposeolva a mag, vagy nem üres, akkor nem lehet elhelyezni őket, kiszürkülnek.
		for (int i = 0; i < matCount; i++) {
			ViewControlDatabase.getMaterialViewControl(materialList.get(i)).drawAsItem(inventoryItemButton[i]);
			if (!exposed || a.getCore() != null)
				inventoryItemButton[i].setEnabled(false);
		}
		
		List<Teleport> teleportList = s.getTeleports();
		int teleportCount = teleportList.size();
		
		// A telepesnél lévő teleportokat mint item fölrajzoljuk.
		for (int i = 0; i < teleportCount; i++) {
			ViewControlDatabase.getTeleportViewControl(teleportList.get(i)).drawAsItem(teleportItemButton[i]);
		}
	}

}
