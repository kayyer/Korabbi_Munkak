package kellekek;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
public class Tabla extends JFrame {
	
	
	private  Mezo[][] mezok = new Mezo[8][8];
	private  Vector<Babu> vilagos = new Vector<>(16);
	private  Vector<Babu> sotet = new Vector<>(16);
	private  String Sakk = "";
	private  boolean sekk = false;
	private  Profil fiok1;
	private  Profil fiok2;
	private Timer botLepteto;
	private boolean botMod = false;
	
	
	
	private JPanel tabla = new JPanel(new GridLayout(8,8));
	private JPanel infoSor = new JPanel();
	private  JTextField kiiro = new JTextField(9);
	private JPanel kozep = new JPanel(new GridLayout(1,3));
	private JTextField jatekos1Nev = new JTextField();
	private JTextField jatekos2Nev = new JTextField();
	private JPanel oldalsoSzamok = new JPanel(new GridLayout(8,1));
	private JPanel alsoBetuk = new JPanel(new GridLayout(1,8));
	private JTextField[] szamok = new JTextField[8];
	private JTextField[] betuk = new JTextField[8];
	private JButton vissza = new JButton(new ImageIcon("chess//visszagomb.png"));
	
	
	public void initComponents() {
		fiok1 = Beallitasok.getPlayer1();
		fiok2 = Beallitasok.getPlayer2();
		jatekos1Nev.setText(fiok1.getNev());
		jatekos2Nev.setText(fiok2.getNev());
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				mezok[i][j] = new Mezo(i,j,this);
				mezok[i][j].setBorder(null);
				if((i%2 == 0 && j%2 == 0) || (i%2 != 0 && j%2 != 0) )
				{
						mezok[i][j].setBackground(Color.WHITE);
				}
				else {
					mezok[i][j].setBackground(Color.BLACK);
				}	
				tabla.add(mezok[i][j]);
			}
		}
		
	
	
		add(tabla,BorderLayout.CENTER);
		
		for(int i = 8; i > 0 ; i--)
		{
			szamok[8-i] = new JTextField();
			szamok[8-i].setText(String.valueOf(i));
			szamok[8-i].setEditable(false);
			szamok[8-i].setHorizontalAlignment(JTextField.CENTER);
		
			oldalsoSzamok.add(szamok[8-i]);
		}
		
		for(int i = 0; i < 8 ; i++)
		{
			char tmp = 'A';
			tmp += i;
			betuk[i] = new JTextField();
			betuk[i].setText(String.valueOf(tmp));
			betuk[i].setEditable(false);
			betuk[i].setHorizontalAlignment(JTextField.CENTER);
			alsoBetuk.add(betuk[i]);
			
		}
		oldalsoSzamok.setPreferredSize(new Dimension(14,400));
		alsoBetuk.setPreferredSize(new Dimension(400,14));
		
		
		add(oldalsoSzamok,BorderLayout.WEST);
		add(alsoBetuk,BorderLayout.SOUTH);
		vissza.setPreferredSize(new Dimension(24,24));
		
		
		vissza.setBorderPainted(false);
		vissza.setContentAreaFilled(false);
		vissza.setActionCommand("clicked");
		vissza_AL val = new vissza_AL();
		vissza.addActionListener(val);
		
		
		kiiro.setEditable(false);
		jatekos1Nev.setEditable(false);
		jatekos2Nev.setEditable(false);
		
		kiiro.setHorizontalAlignment(JTextField.CENTER);
		jatekos1Nev.setHorizontalAlignment(JTextField.CENTER);
		jatekos2Nev.setHorizontalAlignment(JTextField.CENTER);
		
		kozep.add(jatekos1Nev,BorderLayout.LINE_START);
		kozep.add(kiiro,BorderLayout.CENTER);
		kozep.add(jatekos2Nev,BorderLayout.EAST);
		infoSor.add(vissza,BorderLayout.EAST);
		infoSor.add(kozep,BorderLayout.CENTER);
		add(infoSor,BorderLayout.PAGE_START);
	
		
		
		
		}
	
	public Tabla() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(414,444);
		setResizable(false);
		setLocationRelativeTo(null);

		
		initComponents();
		addWindowListener(new mentesZaraskor());
	}
	public class mentesZaraskor extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			mentes();
			System.exit(0);
		}
	}
	public void babukFelrakasa() {
		for(Babu i : vilagos)
		{
			i.felrak();
		}
		for(Babu i : sotet)
		{
			i.felrak();	
		}
		for(Babu i : vilagos)
		{
			i.hova_lephet();
		}
		for(Babu i : sotet)
		{
			i.hova_lephet();
		}
		if(fiok1.getNev().equals("Bot"))
			fiok1.fill(vilagos, sotet, "vilagos");
		if(fiok2.getNev().equals("Bot"))
			fiok2.fill(vilagos, sotet, "vilagos");
		
	}
	public Vector<Babu> getVilagos(){
		return vilagos;
	}
	public Vector<Babu> getSotet(){
		return sotet;
	}

	
	
	public void regiJatekBetoltese() {
		Profil betolt = new Profil("");
		if((!fiok1.getNev().equals("Bot") && !fiok1.getNev().equals("Jatekos") && fiok1.getVilagos() != null) && (!fiok2.getNev().equals("Bot") && !fiok2.getNev().equals("Jatekos")&& fiok1.getVilagos() != null))
		{
			String opt[] = new String[] {fiok1.getNev(),fiok2.getNev()};
			int valasz = JOptionPane.showOptionDialog(null,"Melyik felhasználó mentését használjam?","Mentés",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, opt, opt[0]);
			switch(valasz) {
			case 0 : betolt = fiok1; 
					 fiok2.ellenfel(fiok1);
					 break;
			case 1 : betolt = fiok2;
					 fiok1.ellenfel(fiok2);
					 break;
			}
	
		}
		else if(!fiok1.getNev().equals("Bot") && !fiok1.getNev().equals("Jatekos")&& fiok1.getVilagos() != null)
		{
			betolt = fiok1;
			fiok2.ellenfel(fiok1);
		}
		else {
			betolt = fiok2;
			fiok1.ellenfel(fiok2);
		}
		vilagos = betolt.getVilagos();
		sotet = betolt.getSotet();
		Mezo.setSoronLevo(betolt.getSoron());
		for(Babu i : vilagos)
			i.setHolvan(getMezo(i.getHolvan().getVer(), i.getHolvan().getHor()));
		for(Babu i : sotet)
			i.setHolvan(getMezo(i.getHolvan().getVer(), i.getHolvan().getHor()));

	

	}
	public void BabuBeallitas(){
		String szin = "sotet";
		int sor = 1;
		for(int i = 0 ; i < 16 ; i++)
		{
			if(szin.equals("sotet")) {
				sotet.add(new Gyalog(mezok[sor][i%8],szin));
			}
			else {
				vilagos.add(new Gyalog(mezok[sor][i%8],szin));

			}
			if(i == 7 )
			{
				szin = "vilagos";
				sor = 6;
			}
			
		}
		szin = "sotet";
		sor = 0;
		for(int i = 0; i < 8; i++) {
			if(i == 0 || i ==  7)
			{
				sotet.add(new Bastya(mezok[0][i],"sotet"));
				vilagos.add(new Bastya(mezok[7][i],"vilagos"));
			}
			else if(i == 1 || i == 6)
			{
				sotet.add(new Huszar(mezok[0][i], "sotet"));
				vilagos.add(new Huszar(mezok[7][i],"vilagos"));
			}
			else if(i == 2 || i == 5)
			{
				sotet.add(new Futo(mezok[0][i], "sotet"));
				vilagos.add(new Futo(mezok[7][i],"vilagos"));
			}
			else if(i == 3)
			{
				sotet.add(new Vezer(mezok[0][i], "sotet"));
				vilagos.add(new Vezer(mezok[7][i],"vilagos"));
			}
			else if(i == 4)
			{
				sotet.add(new Kiraly(mezok[0][i], "sotet"));
				vilagos.add(new Kiraly(mezok[7][i],"vilagos"));
			}
			
		}

		

	}
	
	public void leutottek(Babu b) {
		b.getHolvan().setRajta(null);
		for(Babu tmp : b.get_kit_gatol())
		{
			tmp.hova_lephet();
		}
		if(b.szin == "vilagos")
			vilagos.remove(b);
		else
			sotet.remove(b);
		b.getHolvan().setIcon(null);
	}

	public  Mezo getMezo(int ver,int hor) {
		if(ver < 8 && ver >= 0 && hor < 8  && hor >= 0)
			return mezok[ver][hor];
		return null;
	}
	public  void leptek(Mezo m) {
		for(Babu i : vilagos)
		{
			i.valakiElment(m);
		}
		for(Babu i : sotet)
		{
			i.valakiElment(m);
		}
	}
	public  Vector<Babu> getEllenfel(String szin){
		if(szin.equals("vilagos"))
			return sotet;
		else if( szin.equals("sotet"))
			return vilagos;
		return null;
				
	}
	public  String getSakk()
	{
		return Sakk;
	}
	
	public  void setSakk(String szin) {
		Sakk = szin;
		if(Sakk.equals(""))
		{
			kiiro.setText("");

		}
		else
			kiiro.setText("SAKK");

		if(szin.equals("vilagos") || szin.equals(""))
		{
			for(Babu i : vilagos)
			{
				i.hova_lephet();
			}
		}
		if(szin.equals("sotet") || szin.equals(""))
		{
			for(Babu i : sotet)
			{
				i.hova_lephet();
				
			}
		}

	}
	
	public  void ujBabu(Babu ot) {
		if(ot.getSzin().equals("vilagos"))
		{
			ot.setKiralyomHelye(vilagos.elementAt(0).getKiralyomHelye());
			vilagos.add(ot);
		}
		else {
			ot.setKiralyomHelye(vilagos.elementAt(0).getKiralyomHelye());
			sotet.add(ot);
		}
	}
	
	public  void Promotion(Gyalog engem) {
		if(botMod)
		{
			 Vezer ujV = new Vezer(engem.getHolvan(),engem.getSzin());
	 		 leutottek(engem);
	 		 ujBabu(ujV);
	 		 ujV.lep(ujV.getHolvan());
	 		 return;
		}
		ImageIcon[] lehetosegek;
		if(engem.getSzin().equals("vilagos"))
		{
		lehetosegek = new ImageIcon[] {new ImageIcon("chess//png_24//games-battle-wazir-chess-figure-queen-piece-3954.png"),
									   new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-move-rook-1-3971.png"),
									   new ImageIcon("chess//png_24//games-battle-chess-checkmate-figure-king-gambit-3-3970.png"),
									   new ImageIcon("chess//png_24//games-battle-checkmate-chess-knight-horse-figure-3959.png")};
		}
		else {
			lehetosegek = new ImageIcon[] {new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-queen-wazir-3968.png"),
										   new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-move-rook-2-3973.png"),
										   new ImageIcon("chess//png_24//games-battle-chess-checkmate-figure-king-gambit-2-3965.png"),
										   new ImageIcon("chess//png_24//games-battle-checkmate-chess-knight-horse-figure-1-3966.png")};
		}
			
		int valasz = JOptionPane.showOptionDialog(null, "Mire cserélnéd le a gyalogod?", "Elõléptetés",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, lehetosegek, lehetosegek[0]);
		switch(valasz) {
		case 0 : Vezer ujV = new Vezer(engem.getHolvan(),engem.getSzin());
		 		 leutottek(engem);
		 		 ujBabu(ujV);
		 		 ujV.lep(ujV.getHolvan());
		 		 break;
		case 1 : Bastya ujB = new Bastya(engem.getHolvan(),engem.getSzin());
		 		 leutottek(engem);
		 		 ujBabu(ujB);
		 		 ujB.lep(ujB.getHolvan());
		 		 break;
		case 2 : Futo ujF = new Futo(engem.getHolvan(),engem.getSzin());
				 leutottek(engem);
				 ujBabu(ujF);
				 ujF.lep(ujF.getHolvan());
				 break;
		case 3 : Huszar ujH = new Huszar(engem.getHolvan(),engem.getSzin());
				 leutottek(engem);
				 ujBabu(ujH);
				 ujH.lep(ujH.getHolvan());
				 break;
		}
	
		
		
		
		}
	
	public  void nincsEnPassant(String szin)
	{
		if(szin.equals("vilagos"))
		{
			for(Babu i : vilagos)
				i.enPassantNemMehet();
		}
		else {
			for(Babu i: sotet)
				i.enPassantNemMehet();
		}
	}
	
	public  boolean sancolasTiszta(Vector<Mezo> hol,String szin) {
		boolean mehet = true;
		if(szin.equals("vilagos"))
		{
			for(Mezo m : hol)
			{
				for(Babu s : sotet)
				{
					if(m.getRajta() != null || s.tamadjaMezo(m))
					{
						mehet = false;
					}
				}
			}
		}
		else
		{
			for(Mezo m : hol)
			{
				for(Babu v : vilagos)
				{
					if(m.getRajta() != null || v.tamadjaMezo(m))
					{
						mehet = false;
					}
				}
			}
		}
		return mehet;
	}
	public  void kiralyHOL(Babu b) {
		if(b.getSzin().equals("vilagos"))
		{
			for(Babu i : vilagos)
			{
				i.setKiralyomHelye(b.getHolvan());
			}
		}
		else {
			for(Babu i : sotet)
			{
				i.setKiralyomHelye(b.getHolvan());
			}
		}
	}
	public  boolean getSekk() {
		return sekk;
	}
	public  void setSekk(boolean b)
	{
		sekk = b;
		if(sekk)
			kiiro.setText("SEKK");
		else {
			kiiro.setText("");
		}
	}
	
	class vissza_AL implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().contentEquals("clicked"))
			{
				
				String lehetosegek[] = new String[] {"Nem","Igen"};
				int valasz = JOptionPane.showOptionDialog(null, "Szeretnél menteni mielõtt kilépnél?", "Vissza",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, lehetosegek, lehetosegek[0]);
				if(valasz == 0) {
					visszaallit();
					Menu menu = new Menu();
					menu.setVisible(true);
					setVisible(false);
					
				}
				else if(valasz == 1)
				{
					mentes();
					visszaallit();
					Menu menu = new Menu();
					menu.setVisible(true);
					setVisible(false);
				}
			}

		}
	}
	public void mentes() {
		if(!fiok1.getNev().equals("Bot") && !fiok1.getNev().equals("Jatekos"))
		{
			fiok1.fill(vilagos,sotet,Mezo.getSoronLevo());
			try {
			Fiokok.saveMeccs(fiok1);
			}catch(Exception e) {
				System.out.println("Meccs elmentese sikertelen");
			}
		}
	
		if(!fiok2.getNev().equals("Bot") && !fiok2.getNev().equals("Jatekos"))
		{
			fiok2.fill(vilagos,sotet,Mezo.getSoronLevo());
			try {
			Fiokok.saveMeccs(fiok2);
			}catch(Exception e) {
				System.out.println("Meccs elmentese sikertelen");
			}
		}
	}
	
	
	public  Profil getFiok1() {
		return fiok1;
	}
	public  Profil getFiok2() {
		return fiok2;
	}
	
	public  void botJatszma() {
		if(fiok1.getNev().equals("Bot") && fiok2.getNev().equals("Bot"))
		{
			botMod = true;
			fiok1.fill(vilagos, sotet, "vilagos");
			fiok2.fill(vilagos, sotet, "vilagos");
			botLepteto = new Timer(2000,new botAL());
			botLepteto.start();
		
			
		}
	}
	public class botAL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(fiok1.getOldal().equals(Mezo.getSoronLevo()))
			{
				fiok1.activateBot();
			}
			else
			{	
				fiok2.activateBot();
			}
	}
	}
	public boolean getBotMod() {
		return botMod;
	}
	public void visszaallit() {
		if(botMod)
			botLepteto.stop();
		
		Mezo.setBack();
		Babu.setBackKoztuk();
	}
	public void vegeVan(String szin) {
		boolean jatekVege = true;
		Vector<Babu> jatekos_aki_jon;
		if(szin.equals("vilagos"))
		{
			jatekos_aki_jon = vilagos;
		}
		else
		{
			jatekos_aki_jon = sotet;
		}
		for(Babu i : jatekos_aki_jon)
		{
			if(i.lephet.size() > 0)
				jatekVege = false;
		}
		if(jatekVege)
		{
			String gyoztes = new String();
			
			if(Sakk.equals(""))
			{
				gyoztes = "Senki sem";
			}
			else {
				if((fiok1.getNev() == "Bot" || fiok1.getNev() =="Jatekos") && (fiok2.getNev() == "Bot" || fiok2.getNev() =="Jatekos"))
				{
					if(Sakk.equals("vilagos"))
					{
						gyoztes = "Sötét";
					}
					else
						gyoztes = "Világos";
				}
				else
				{
					if(fiok1.getOldal().equals(Sakk))
						gyoztes = fiok2.getNev();
					else
						gyoztes = fiok1.getNev();
				}
			}
			Object lehetosegek[] = {"OK"};
			int valasz = JOptionPane.showOptionDialog(null, "A játéknak vége. " + gyoztes + " nyert", "Vége",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, lehetosegek, lehetosegek[0]);
			if(valasz == 0)
			{
				visszaallit();
				Menu menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		}
	}
	

	}
	
