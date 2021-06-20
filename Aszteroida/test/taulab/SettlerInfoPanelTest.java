package taulab;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;

import tau.asteroidgame.Asteroid;
import tau.asteroidgame.Coal;
import tau.asteroidgame.Settler;
import tau.asteroidgame.Teleport;
import tau.asteroidgame.UserInterface.MainFrame;
import tau.asteroidgame.UserInterface.SettlerInfoPanel;

public class SettlerInfoPanelTest {
	public static void main(String[] args) {
		Coal c = new Coal();
		
		 try {
             MainFrame.starGuardianFont = Font.createFont(Font.TRUETYPE_FONT, c.getClass().getResourceAsStream("/StarGuardMidCase.ttf"));
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
             //register the font
             ge.registerFont(MainFrame.starGuardianFont);
         } catch (IOException | FontFormatException e) {
             e.printStackTrace();
         }
		
		JFrame jf = new JFrame();
		SettlerInfoPanel sip = new SettlerInfoPanel();
		jf.add(sip);
		jf.setVisible(true);
		jf.setSize(new Dimension(600, 200));
		
		Asteroid a = new Asteroid(5);
		Settler s = new Settler(a);
		Teleport t = new Teleport(s);
		
		sip.update(s);
	}

}