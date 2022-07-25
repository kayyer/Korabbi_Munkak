package kellekek;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Mezo extends JButton implements ActionListener {
	private int hor;
	private int ver;
	private Babu rajta;
	private static boolean lepne = false;
	private static String soronLevo = "vilagos";
	private static Mezo tmpLepo;
	private Tabla sakkTabla;

	public Mezo(int v, int h, Tabla t) {
		super();
		hor = h;
		ver = v;
		sakkTabla = t;
		this.setActionCommand("clicked");
		this.addActionListener(this);
	}

	public static void setBack() {
		lepne = false;
		soronLevo = "vilagos";
		tmpLepo = null;

	}

	public Tabla getSakkTabla() {
		return sakkTabla;
	}

	public Babu getRajta() {
		return rajta;
	}

	public static String getSoronLevo() {
		return soronLevo;
	}

	public static void setSoronLevo(String s) {
		soronLevo = s;
	}

	public void setHor(int hor) {
		this.hor = hor;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public int getHor() {
		return hor;
	}

	public int getVer() {
		return ver;
	}

	public void setRajta(Babu b) {
		rajta = b;
	}

	public static void setTmpLepo(Mezo m) {
		tmpLepo = m;
	}

	public static void setLepne(boolean b) {
		lepne = b;
	}

	public void actionPerformed(ActionEvent ae) {
		if ( ae.getActionCommand().contentEquals("clicked") ) {
			if(sakkTabla.getBotMod())
				return;
			if (lepne == false && rajta != null && rajta.getSzin().equals(soronLevo) && rajta.lephet.size() != 0)
			{	
				setBorder(BorderFactory.createLineBorder(Color.green));
				if(Beallitasok.getGyakorloMod())
				{
					for(Mezo i : rajta.lephet)
					{
						i.setBorder(BorderFactory.createLineBorder(Color.blue));
					}
				}
				lepne = true;
				tmpLepo = this;

			}
			else if (lepne == true && this != tmpLepo && tmpLepo.rajta.lephet.contains(this) )
			{
				if (rajta != null)
				{
					sakkTabla.leutottek(rajta);
				}
				rajta = tmpLepo.getRajta();
			
				if(Beallitasok.getGyakorloMod())
				{
					if(sakkTabla.getSekk())
						sakkTabla.setSekk(false);
					for(Mezo i : tmpLepo.rajta.lephet)
					{
						i.setBorder(null);
					}
				}
		
				rajta.lep(this);
				
				if(!sakkTabla.getSakk().equals(""))
				{
					sakkTabla.setSakk("");
				}
				
				lepne = false;
				if (soronLevo.equals("vilagos"))
				{	
					soronLevo = "sotet";

				}
				else
					soronLevo = "vilagos";
				tmpLepo.setBorder(null);
				
				sakkTabla.vegeVan(soronLevo);
				
				

				
			}
			else if (Beallitasok.getGyakorloMod() && lepne == true && this == tmpLepo)
			{
				if(Beallitasok.getGyakorloMod())
				{
					for(Mezo i : rajta.lephet)
					{
						i.setBorder(null);
					}
				}
				setBorder(null);
				lepne = false;
				tmpLepo = null;
			}
			
			if(sakkTabla.getFiok1().getNev().equals("Bot") && sakkTabla.getFiok1().getOldal().equals(soronLevo))
			{
				sakkTabla.getFiok1().activateBot();
			}
			if(sakkTabla.getFiok2().getNev().equals("Bot") && sakkTabla.getFiok2().getOldal().equals(soronLevo))
			{
				sakkTabla.getFiok2().activateBot();
			}

		
		}
	}

}
