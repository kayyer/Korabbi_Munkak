package kellekek;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class Beallitasok extends JFrame{
	private JComboBox<Object> Fiok1;
	private JComboBox<Object> Fiok2;
	private JButton new_player = new JButton("Új játékos");
	private JPanel profiles = new JPanel();
	private Fiokok felh = new Fiokok();
	private JPanel profile1 = new JPanel();
	private JPanel profile2 = new JPanel();
	private JPanel time = new JPanel(new GridLayout(1,4));
	private  CheckboxGroup cbg = new CheckboxGroup();
	private JToggleButton kezdoMod = new JToggleButton(new ImageIcon("chess//off.png"));
	private JPanel kezdo = new JPanel(new GridLayout(1,1));
	private JPanel be2 = new JPanel(new GridLayout(1,2));
	private JButton mentes = new JButton(new ImageIcon(new ImageIcon("chess//mentesgomb.png").getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH)));
	private JButton vissza = new JButton(new ImageIcon(new ImageIcon("chess//visszagomb.png").getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH)));
	private JPanel opciok = new JPanel(new GridLayout(3,1));
	private static boolean gyakorloMod = false;
	private static int sakkOra = -1;
	private static Profil player1 = new Profil("Jatekos");
	private static Profil player2 = new Profil("Jatekos");
	private HashMap<String,Profil> nevek = new HashMap<>();

	
	public Beallitasok() {
		try {
		felh.load();
		}catch(Exception e) {
			System.out.println("Hiba");
		}
		ArrayList<String> felhasznalok = new ArrayList<>();
		felhasznalok.add("Jatekos");
		felhasznalok.add("Bot");
		felhasznalok.addAll(felh.list());
		Fiok1 = new JComboBox<Object>(felhasznalok.toArray());
		Fiok2 = new JComboBox<Object>(felhasznalok.toArray());
	
		for(Profil p : felh.getFiokok().keySet())
		{
			nevek.put(p.getNev(),p);
		}
		
		
		profile1.add(Fiok1);
		profile1.setBorder(BorderFactory.createTitledBorder("JATEKOS_1"));
		profile2.add(Fiok2);
		profile2.setBorder(BorderFactory.createTitledBorder("JATEKOS_2"));
		profiles.add(profile1,BorderLayout.WEST);
		profiles.add(profile2,BorderLayout.EAST);
		profiles.add(new_player,BorderLayout.SOUTH);
		profiles.setBorder(BorderFactory.createTitledBorder("JÁTÉKOS BEÁLLÍTÁSOK"));
		
		time.add(new Checkbox("Nincs",cbg,true));
		time.add(new Checkbox("05:00",cbg,false));
		time.add(new Checkbox("10:00",cbg,false));
		time.add(new Checkbox("15:00",cbg,false));
		time.setBorder(BorderFactory.createTitledBorder("IDÕ LIMIT"));
		
		kezdo.setBorder(BorderFactory.createTitledBorder("GYAKORLÓ MÓD KI/BE"));
		kezdoMod.setBorderPainted(false);
		kezdoMod.setContentAreaFilled(false);
		kezdo.add(kezdoMod);
		

		opciok.add(profiles);
		opciok.add(time);
		opciok.add(kezdo);
		
		vissza.setPreferredSize(new Dimension(30,30));
		vissza.setActionCommand("clicked");
		vissza_AL  val = new vissza_AL();
		vissza.addActionListener(val);
		
		new_player.setActionCommand("clicked");
		new_player_AL npa = new new_player_AL();
		new_player.addActionListener(npa);
		
		mentes.setActionCommand("clicked");
		mentes_AL mal = new mentes_AL();
		mentes.addActionListener(mal);
		
		be2.add(vissza);
		be2.add(mentes);
		
		kezdoMod.setActionCommand("clicked");
		kezdoMod_AL kal = new kezdoMod_AL();
		kezdoMod.addActionListener(kal);
		
		add(opciok,BorderLayout.CENTER);
		add(be2,BorderLayout.SOUTH);

		setTitle("Beállítások");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(220,430);
		setLocationRelativeTo(null);
		
	
	}
	
	class vissza_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked"))
			{
				Menu menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		}
		
	}
	class new_player_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked"))
			{
				
				String s = (String)JOptionPane.showInputDialog(null,"Új felhasználó neve :","Új felhasználó",JOptionPane.PLAIN_MESSAGE,null,null,"");
				if(s != null && s.length() > 0)
				{
					if(!s.equals("Bot") && !s.equals("Jatekos"))
					{
					try {
					Profil ujP = new Profil(s);
					felh.add(ujP);
					Fiok1.addItem(s);
					Fiok2.addItem(s);
					nevek.put(s,ujP);
					felh.save();
					}catch(Exception e) {
						System.out.println("Hiba2");
					}
					}
				}
			}
		}
	}
	class kezdoMod_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked")) {
				if(kezdoMod.isSelected()) {
					kezdoMod.setIcon(new ImageIcon("chess/on.png"));
					gyakorloMod = true;
				}
				else {
					kezdoMod.setIcon(new ImageIcon("chess/off.png"));
					gyakorloMod = false;
				}
			}
		}
	}
	class mentes_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked"))
			{
				
				if(((String)Fiok1.getSelectedItem()).equals("Bot"))
					player1 = new Bot();
				else if(((String)Fiok1.getSelectedItem()).equals("Jatekos"))
					player1 = new Profil("Jatekos");
				else
					player1 = nevek.get((String)Fiok1.getSelectedItem());
				if(((String)Fiok2.getSelectedItem()).equals("Bot"))
					player2 = new Bot();
				else if(((String)Fiok2.getSelectedItem()).equals("Jatekos"))
					player2 = new Profil("Jatekos");
				else
					player2 = nevek.get((String)Fiok2.getSelectedItem());
				
				switch(cbg.getSelectedCheckbox().getLabel()){
				case "Nincs" :
						player1.setHatra(-1);
						player2.setHatra(-1);
						break;
				case "05:00" :
						player1.setHatra(5*3);
						player2.setHatra(5*3);
						break;
				case "10:00" : 
						player1.setHatra(10*60);
						player2.setHatra(10*60);
						break;
				case "15:00" :
						player1.setHatra(15*60);
						player2.setHatra(15*60);
			}
			
				

				
				
			}
		}
	}
	
	public static boolean getGyakorloMod() {
		return gyakorloMod;
	}
	public static Profil getPlayer1() {
		return player1;
	}
	public static Profil getPlayer2() {
		return player2;
	}
}
