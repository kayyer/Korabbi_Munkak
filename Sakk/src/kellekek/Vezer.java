package kellekek;

import java.util.Vector;

import javax.swing.ImageIcon;

public class Vezer extends Babu {
	public Vezer(Mezo pos, String col) {
		super(pos, col);
		ertek = 9;
		if (szin == "sotet")
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-queen-wazir-3968.png");
		else
			icon = new ImageIcon("chess//png_24//games-battle-wazir-chess-figure-queen-piece-3954.png");

	}

	public void hova_lephet() {
		lephet.clear();
		boolean elore = true;
		boolean hatra = true; 
		boolean jobbra = true;
		boolean balra = true;
		boolean eloreATL = true;
		boolean hatraATL = true;
		boolean jobbraATL = true;
		boolean balraATL = true;
		for (int i = 1; i < 8; i++) {
			if (i + hol_van.getVer() < 8 && elore) {
				elore = lephetHelp(hol_van.getSakkTabla().getMezo(i + hol_van.getVer(), hol_van.getHor()));

			}
			if (hol_van.getVer() - i >= 0 && hatra) {
				
				hatra = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor()));
				if(szin.equals("vilagos"))
				{
		if(!hatra)
			System.out.println("GEG" + i );
		System.out.println("GqG" + i + hatra);
				}
			}
			if (i + hol_van.getHor() < 8 && jobbra) {
				jobbra = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() + i));

			}
			if (hol_van.getHor() - i >= 0 && balra) {
				balra = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() - i));
				
			}

			if (i + hol_van.getVer() < 8 && i + hol_van.getHor() < 8 && eloreATL) {
				eloreATL = lephetHelp(hol_van.getSakkTabla().getMezo(i + hol_van.getVer(), i + hol_van.getHor()));
			}
			if (hol_van.getVer() - i >= 0 && hol_van.getHor() - i >= 0 && hatraATL) {
				hatraATL = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() - i));
			}
			if (i + hol_van.getHor() < 8 && hol_van.getVer() - i >= 0 && jobbraATL) {
				jobbraATL = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() - i, hol_van.getHor() + i));
			}
			if (hol_van.getHor() - i >= 0 && hol_van.getVer() + i < 8 && balraATL) {
				balraATL = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() + i, hol_van.getHor() - i));
			}

		}
	}
	public Vector<Mezo> HogyJutszIde(Mezo b){
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
			int kul1 = hol_van.getVer() - b.getVer();
			int kul2 = hol_van.getHor() - b.getHor();
			if(Math.abs(kul1) != Math.abs(kul2))
				return null;
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
		}
		if(tiszta)
			return utvonal;
		return null;
	}
	public void Tamadja(Babu b) {
		if(Beallitasok.getGyakorloMod())
			hol_van.getSakkTabla().setSekk(true);		
		tamadjak.add(b);
	}
	

}
