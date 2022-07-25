package kellekek;
import javax.swing.*;

import kellekek.Menu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Menu extends JFrame {
	
	private JButton new_game = new JButton("Új játék");
	private JButton old_game = new JButton("Játék folytatása");
	private JButton settings = new JButton("Beállítások");

	public Menu(){
		setTitle("MENU");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200,249);
		setLayout(new GridLayout(3,1));
		add(new_game);
		add(old_game);
		add(settings);
		setLocationRelativeTo(null);
		new_game.setActionCommand("clicked");
		new_game_AL ng = new new_game_AL();
		new_game.addActionListener(ng);
		settings.setActionCommand("clicked");
		settings_AL sg = new settings_AL();
		settings.addActionListener(sg);
		old_game.setActionCommand("clicked");
		old_game_AL og = new old_game_AL();
		old_game.addActionListener(og);
	}
	
	class new_game_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked"))
			{
				Random rand = new Random();
				if(rand.nextInt(1) == 1)
				{
					Beallitasok.getPlayer1().setOldal("vilagos");
					Beallitasok.getPlayer2().setOldal("sotet");
				}
				else {
					Beallitasok.getPlayer1().setOldal("vilagos");
					Beallitasok.getPlayer2().setOldal("sotet");
				}
				Tabla tabla = new Tabla();
				tabla.BabuBeallitas();
				tabla.babukFelrakasa();
				tabla.setVisible(true);
				setVisible(false);
				tabla.botJatszma();

			}
		}
	}
	class settings_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked"))
			{
				Beallitasok set = new Beallitasok();
				set.setVisible(true);
				setVisible(false);
			}
		}
	}
	class old_game_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked"))
			{
				Profil f1= Beallitasok.getPlayer1();
				Profil f2= Beallitasok.getPlayer2();
				boolean mehet = false;
				if(!f1.getNev().equals("Bot") && !f1.getNev().equals("Jatekos"))
				{
					try {
					Fiokok.loadMeccs(f1);
					}catch(Exception e) {
						System.out.println("Nincs mentesi fajl");
					}
					if(f1.getVilagos() != null)
						mehet = true;
				}
				if(!f2.getNev().equals("Bot") && !f2.getNev().equals("Jatekos"))
				{
					try {
					Fiokok.loadMeccs(f2);
					}catch(Exception e) {
						System.out.println("Nincs mentesi fajl");
					}
					if(f2.getVilagos() != null)
						mehet = true;

				}
				if(mehet)
				{
					Tabla tabla = new Tabla();
					tabla.regiJatekBetoltese();
					tabla.babukFelrakasa();
					tabla.setVisible(true);
					setVisible(false);
				}
			
			
			}
		}
	}
	
	

}