package kellekek;

import java.util.Vector;

import javax.swing.ImageIcon;

public class Futo extends Babu {
	public Futo(Mezo pos,String col) {
		super(pos,col);
		if(szin == "sotet")
			icon = new ImageIcon("chess//png_24//games-battle-chess-checkmate-figure-king-gambit-2-3965.png");
		else
			icon = new ImageIcon("chess//png_24//games-battle-chess-checkmate-figure-king-gambit-3-3970.png");

	}
	public void hova_lephet() {
		lephet.clear();
		boolean elore = true;
		boolean hatra = true;
		boolean jobbra = true;
		boolean balra = true;		

		for(int i = 1 ; i < 8 ; i++)
		{
			if(i+hol_van.getVer()<8 && i + hol_van.getHor() < 8 && elore)
			{	
				elore = lephetHelp(hol_van.getSakkTabla().getMezo(i+hol_van.getVer(),i+hol_van.getHor()));
			}
			if(hol_van.getVer() - i >= 0 && hol_van.getHor() - i >= 0 && hatra)
			{
				hatra  = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() - i ));
			}
			if(i+hol_van.getHor() < 8 && hol_van.getVer() - i >= 0 &&  jobbra)
			{
				jobbra = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() + i));		
			}
			if(hol_van.getHor() - i >= 0 && hol_van.getVer() + i < 8 && balra)
			{
				balra = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() + i,hol_van.getHor() - i));
			}
		}

	}
	
	public Vector<Mezo> HogyJutszIde(Mezo b){
		Vector<Mezo> utvonal = new Vector<>();
		int kul1 = hol_van.getVer() - b.getVer();
		int kul2 = hol_van.getHor() - b.getHor();
		boolean tiszta = true;
		if(Math.abs(kul1) != Math.abs(kul2))
		{
			return null;
		}
		for(int i = 0; i < Math.abs(kul1) ; i++)
		{

			if( kul1 < 0 && kul2 < 0)
			{
				if(i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer() + i, hol_van.getHor() + i).getRajta() != null))
					tiszta = false;
				utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer() + i, hol_van.getHor() + i));
			}
			else if(kul1 > 0 && kul2 < 0)
			{
				if(i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() + i).getRajta() != null))
					tiszta = false;
				utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() + i));
			}
			else if(kul1 < 0 && kul2 > 0)
			{
				if(i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer() + i, hol_van.getHor() - i).getRajta() != null))
					tiszta = false;
				utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer() + i, hol_van.getHor() - i));
			}
			else if(kul1 > 0 && kul2 > 0 )
			{
				if(i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() - i).getRajta() != null))
					tiszta = false;
				utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() - i));				
			}		
		}
	
	if(tiszta)
		return utvonal;
	return null;
	}
}
