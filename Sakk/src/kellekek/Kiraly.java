package kellekek;

import javax.swing.ImageIcon;
import java.util.*;

public class Kiraly extends Babu{
	public Kiraly(Mezo pos,String col) {
		super(pos,col);
		ertek = 10;
		if(szin == "sotet")
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-camel-bishop-figure-1-3956.png");
		else
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-camel-bishop-figure-3952.png");
	}
	public void hova_lephet() {
		lephet.clear();
		int egy = 1;
		int ver = 0;
		if(szin.equals("vilagos"))
			ver = 7;
		for(int i = 0 ; i < 2 ; i++)
		{
			if(hol_van.getVer() + egy < 8  && hol_van.getVer() + egy >= 0 )
			{	
				lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() + egy,hol_van.getHor()));
			}
			if(hol_van.getHor() + egy < 8 &&  hol_van.getHor() + egy >= 0)
			{
				lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer(),hol_van.getHor() + egy));
			}
			if(hol_van.getVer() + egy < 8 && hol_van.getHor() + egy < 8 && hol_van.getVer() + egy >= 0 && hol_van.getHor() + egy >= 0)
			{
				lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() + egy,hol_van.getHor() + egy));
			}
			if(hol_van.getVer() - egy < 8 && hol_van.getHor() + egy < 8 && hol_van.getVer() - egy >= 0 && hol_van.getHor() + egy >= 0)
			{
				lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() - egy,hol_van.getHor() + egy));
			}
	
			egy *= -1;
	
		
		}
		if(megNemLepett)
		{
			Vector<Mezo> tmp = new Vector<>();
			for(int i = 1; i < 4 ; i++)
			{
				tmp.add(hol_van.getSakkTabla().getMezo(ver,i));
			}
			if(hol_van.getSakkTabla().sancolasTiszta(tmp,szin) && hol_van.getSakkTabla().getMezo(ver, 0).getRajta() != null && hol_van.getSakkTabla().getMezo(ver, 0).getRajta().megNemLepett)
			{
				lephet.add(hol_van.getSakkTabla().getMezo(ver, 2));
			}
			tmp.clear();
			for(int i = 5; i < 7 ; i++)
			{
				tmp.add(hol_van.getSakkTabla().getMezo(ver,i));
			}
			if(hol_van.getSakkTabla().sancolasTiszta(tmp,szin) && hol_van.getSakkTabla().getMezo(ver, 7).getRajta() != null && hol_van.getSakkTabla().getMezo(ver, 7).getRajta().megNemLepett)
			{
				lephet.add(hol_van.getSakkTabla().getMezo(ver, 6));
			}
		}
	}
	public boolean lephetHelp(Mezo tmp) {
		for(Babu i : hol_van.getSakkTabla().getEllenfel(szin))
		{
			if(i.tamadjaMezo(tmp))
			{
				return false;
			}
		}
		if(tmp.getRajta() == null)
		{
			lephet.add(tmp);
			return true;
		}
		tamadHelp(tmp);
		return false;
	}
	public void valakiElment(Mezo m) {
			hova_lephet();
	}
	
	public void Tamadja(Babu b) {
		koztuk = b.HogyJutszIde(hol_van);	
		
		if(hol_van.getSakkTabla().setSakk(szin))
			
		tamadjak.add(b);
	}
	public Vector<Mezo> HogyJutszIde(Mezo b){
		return null;
	}
	public void lep(Mezo hova) {
	

		hol_van.getSakkTabla().nincsEnPassant(szin);
		vedi.clear();
		hol_van.setRajta(null);
		hol_van.setIcon(null);
		hol_van = null;
		
		hol_van = hova;
		for(Babu i : tamad) {
			i.TamadoEl(this);
		}
		tamad.clear();
		tamadjak.clear();
		hol_van.getSakkTabla().kiralyHOL(this);
		
		for(Babu i : kit_gatol)
		{
			i.hova_lephet();
		}
		kit_gatol.clear();
		hol_van.getSakkTabla().leptek(hol_van);
		felrak();
		hova_lephet();		
		
		if(megNemLepett)
		{
			int ver = 0;
			if(szin.equals("vilagos"))
				ver = 7;
			if(hova == hol_van.getSakkTabla().getMezo(ver, 2))
				hol_van.getSakkTabla().getMezo(ver, 0).getRajta().lep(hol_van.getSakkTabla().getMezo(ver, 3));
			if(hova == hol_van.getSakkTabla().getMezo(ver, 6))
			{
				hol_van.getSakkTabla().getMezo(ver, 7).getRajta().lep(hol_van.getSakkTabla().getMezo(ver, 5));
			}	
		}
		
		megNemLepett = false;

	}
	
	public void felrak() {
		hol_van.setRajta(this);
		hol_van.setIcon(icon);
		hol_van.getSakkTabla().kiralyHOL(this);
	}
	

}
