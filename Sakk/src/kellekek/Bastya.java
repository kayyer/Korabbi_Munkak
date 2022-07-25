package kellekek;

import javax.swing.ImageIcon;

import java.util.*;

public class Bastya extends Babu {
	public Bastya(Mezo pos,String col) {
		super(pos,col);
		if(col == "sotet")
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-move-rook-2-3973.png");
		else
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-move-rook-1-3971.png");

			
			
	}
	public void hova_lephet() {
		lephet.clear();
		boolean elore = true;
		boolean hatra = true;
		boolean jobbra = true;
		boolean balra = true;
		for(int i = 1 ; i < 8 ; i++)
		{
			if(i+hol_van.getVer()<8 && elore)
			{	
				elore = lephetHelp(hol_van.getSakkTabla().getMezo(i+hol_van.getVer(),hol_van.getHor()));
			}
			if(hol_van.getVer() - i >= 0 && hatra)
			{
				hatra  = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor()));
			}
			if(i+hol_van.getHor() < 8 && jobbra)
			{
				jobbra = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor()+i));
				
			}
			if(hol_van.getHor() - i >= 0 && balra)
			{
				balra = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer(),hol_van.getHor() - i));
			}
		}
	
	}
		public Vector<Mezo> HogyJutszIde(Mezo b)
		{
			Vector<Mezo> utvonal = new Vector<>();
			boolean tiszta = true;
			if(b.getVer() == hol_van.getVer())
			{
					int kul = hol_van.getHor() - b.getHor();
					for(int i = 0; i < Math.abs(kul) ; i++)
					{
						if( kul < 0)
						{
							if(i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() + i).getRajta() != null))
									tiszta = false;
							utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() + i));
						}
							
						else if(kul > 0)
						{
							if( i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() - i).getRajta() != null))
								tiszta = false;
							utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() - i));	
						}
					}
			}
			else if(b.getHor() == hol_van.getHor())
			{
				int kul = hol_van.getVer() - b.getVer();
				for(int i = 0; i < Math.abs(kul) ; i++)
				{
					if( kul < 0)
					{
						if(i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer() + i, hol_van.getHor()).getRajta() != null))
							tiszta = false;
						utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer() + i, hol_van.getHor()));
					}
					else if(kul > 0)
					{
						if(i != 0 && (hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor()).getRajta() != null))
							tiszta = false;
						utvonal.add(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor()));
					}
				}
			}
			else {
				return null;
			}
			
			if(tiszta)
				return utvonal;
			return null;
		}

	
	


}
