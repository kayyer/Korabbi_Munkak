package kellekek;

import java.util.*;


public class Bot extends Profil {

	private Random rand = new Random();
	
	public Bot() {
		super("Bot");
	}

	public void activateBot() {		
	
		if (oldal.equals("vilagos"))
			randomLepes(vilagos);
		else
			randomLepes(sotet);

	}

	public void randomLepes(Vector<Babu> tmp) {
		Vector<Babu> tudLepni = new Vector<>();
		for (Babu i : tmp) {
			if (i.lephet.size() > 0)
				tudLepni.add(i);
		}
	
		int meret = tudLepni.size() - 1;
		int index = 0;
		if (meret > 0)
			index = rand.nextInt(meret);
		Babu kivalasztott = tudLepni.elementAt(index);
		meret = kivalasztott.lephet.size() - 1;
		if(meret > 0)
			meret = rand.nextInt(meret);
		Mezo kivMezo = kivalasztott.lephet.get(meret);
		botLep(kivalasztott, kivMezo);

	}


	public void botLep(Babu kivel, Mezo hova) {
		if(hova.getRajta() != null)
		{
			hova.getSakkTabla().leutottek(hova.getRajta());
		
		}
		hova.setRajta(kivel);
		
		if(Beallitasok.getGyakorloMod())
		{
			if(hova.getSakkTabla().getSekk())
				hova.getSakkTabla().setSekk(false);
			
		}

		kivel.lep(hova);
		if(!hova.getSakkTabla().getSakk().equals(""))
		{
			hova.getSakkTabla().setSakk("");
		}
		
		if (Mezo.getSoronLevo().equals("vilagos"))
		{	
			Mezo.setSoronLevo("sotet");

		}
		else
			Mezo.setSoronLevo("vilagos");
		
		hova.getSakkTabla().vegeVan(Mezo.getSoronLevo());

	}

	
}
