package kellekek;

import java.util.Vector;

import javax.swing.ImageIcon;

public class Huszar extends Babu{
	public Huszar(Mezo pos,String col) {
		super(pos,col);
		ertek = 3;
		if(szin == "sotet")
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-knight-horse-figure-1-3966.png");
		else
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-knight-horse-figure-3959.png");

	}
	public void hova_lephet() {
		lephet.clear();
		int egy = 1;
		int ketto = 2;
		for(int i = 0 ; i < 4 ; i++)
		{
			if(hol_van.getVer() + egy <8 && hol_van.getHor() + ketto < 8 && hol_van.getVer() + egy >= 0 && hol_van.getHor() + ketto >= 0)
			{	
				lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() + egy,hol_van.getHor() + ketto));
			}
			if(hol_van.getVer() + ketto < 8 && hol_van.getHor() + egy < 8 && hol_van.getVer() + ketto >= 0 && hol_van.getHor() + egy >= 0)
			{
				lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() + ketto,hol_van.getHor() + egy));
			}
			
		if(i%2 == 0)
			ketto *= -1;
		else
			egy *= -1;
	
		
		}

		
	}
	public Vector<Mezo> HogyJutszIde(Mezo b)
	{
		if(tamad.contains(b.getRajta()))
		{
			Vector<Mezo> tmp = new Vector<>();
			tmp.add(hol_van);
			return tmp;
		}
		return null;
		
	}

}
