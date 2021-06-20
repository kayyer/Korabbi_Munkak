package kellekek;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer.*;

public class Bot extends Profil {
	private HashMap<Babu, Mezo> kitTamadjon = new HashMap<>();
	private HashMap<Babu, Mezo> vedettTamadas = new HashMap<>();
	private HashMap<Babu, Mezo> vedekezes = new HashMap<>();
	private Random rand = new Random();
	private int maxErtek = 0;
	private int modMax = 0;
	private int tamadottMax = 0;
	private boolean vanAkitTamadnak = false;

	public Bot() {
		super("Bot");
	}

	public void activateBot() {
		/*

		if (oldal.equals("vilagos")) {
			tamadoTeszt(vilagos);
		} else if (oldal.equals("sotet"))
			tamadoTeszt(sotet);
		if (kiralyTamadas())
			return;
		if (vanAkitTamadnak) {
			if (maxErtek >= tamadottMax) {
				if (kitTamadjon.size() > 0) {
					TamadoLepes(kitTamadjon);
					return;
				}
			} else {
				if (vedekezes.size() > 0) {
					TamadoLepes(vedekezes);
					return;
				}
			}
		} else {
			if (vedettTamadas.size() > 0) {
				TamadoLepes(vedettTamadas);
				return;
			}
		}*/
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
		Mezo kivMezo = kivalasztott.lephet.elementAt(meret);
		botLep(kivalasztott, kivMezo);

	}

	public void TamadoLepes(HashMap<Babu, Mezo> h) {
		int felsohatar = h.size() - 1;
		int index = 0;
		if (felsohatar > 0)
			index = rand.nextInt(felsohatar);
		Object honnan[] = h.keySet().toArray();
		Babu kivalasztott = (Babu) honnan[index];
		botLep(kivalasztott, h.get(kivalasztott));
	}

	public void botLep(Babu kivel, Mezo hova) {
		System.out.println("+++++++");
		System.out.println(kivel.getHolvan().getVer() + " " + kivel.getHolvan().getHor()
				+ "itt van most es ide lepne : " + hova.getVer() + " " + hova.getHor());
		/*
		kivel.getHolvan().setLepne(true);
		kivel.getHolvan().setTmpLepo(kivel.getHolvan());
		javax.swing.Timer timer = new javax.swing.Timer(1000, hova);
		//hova.repaint();
		timer.setRepeats(false);
	
		timer.start();
		*/
		if(hova.getRajta() != null)
		{
			hova.getSakkTabla().leutottek(hova.getRajta());
		}
		hova.setRajta(kivel);
		if(!hova.getSakkTabla().getSakk().equals(""))
		{
			hova.getSakkTabla().setSakk("");
		}
		if(Beallitasok.getGyakorloMod())
		{
			if(hova.getSakkTabla().getSekk())
				hova.getSakkTabla().setSekk(false);
			
		}

		kivel.lep(hova);
		if (hova.getSoronLevo().equals("vilagos"))
		{	
			hova.setSoronLevo("sotet");

		}
		else
			hova.setSoronLevo("vilagos");
		
		hova.getSakkTabla().vegeVan(hova.getSoronLevo());

	}

	public void tamadoTeszt(Vector<Babu> v) {
		vanAkitTamadnak = false;
		for (Babu i : v) {
			System.out.println(i + "   BOT");
			if (i.lephet.size() == 0)
				continue;
			System.out.println("________" + i.getHolvan().getVer() + " " + i.getHolvan().getHor() + "_____________");
			for (Mezo jo : i.lephet)
				System.out.println(jo.getVer() + " " + jo.getHor() + " lephet");
			for (Babu j : i.tamad) {
				if (j.getSzin().equals(oldal))
					continue;
				System.out
						.println("________" + i.getHolvan().getVer() + " " + i.getHolvan().getHor() + "_____________");
				for (Mezo jo : i.lephet)
					System.out.println(j.getHolvan().getVer() + " " + j.getHolvan().getHor() + " tamadhat");
				if (j.getErtek() > maxErtek) {
					kitTamadjon.clear();
					kitTamadjon.put(i, j.getHolvan());
					maxErtek = j.getErtek();
				} else if (j.getErtek() == maxErtek) {
					kitTamadjon.put(i, j.getHolvan());
				}
				if (j.vedi.size() > 0) {
					if (j.tamadjak.size() > 1) {
						if (j.getErtek() > modMax) {
							vedettTamadas.clear();
							vedettTamadas.put(i, j.getHolvan());
							modMax = j.getErtek();
						} else if (j.getErtek() == modMax) {
							vedettTamadas.put(i, j.getHolvan());
						}

					} else {
						int tmpErtek = j.getErtek() - i.getErtek();
						if (tmpErtek > modMax) {
							vedettTamadas.clear();
							vedettTamadas.put(i, j.getHolvan());
							modMax = tmpErtek;
						} else if (tmpErtek == modMax) {
							vedettTamadas.put(i, j.getHolvan());
						}
					}
				}
				if (i.tamadjak.size() > 0) {
					vanAkitTamadnak = true;
					Vector<Mezo> lepesLehetoseg = new Vector<>();
					boolean tiszta = true;
					for (Mezo m : i.lephet) {
						tiszta = true;
						if (oldal.equals("vilagos")) {
							for (Babu s : sotet) {
								if (s.lephet.contains(m)) {
									tiszta = false;
									;
								}
							}
						} else if (oldal.equals("sotet")) {
							for (Babu s : vilagos) {
								if (s.lephet.contains(m)) {
									tiszta = false;
									;
								}
							}
						}
						if (tiszta)
							lepesLehetoseg.add(m);
					}
					if (lepesLehetoseg.size() == 0)
						vanAkitTamadnak = false;
					else {
						if (i.ertek > tamadottMax) {
							vedekezes.clear();
							tamadottMax = i.ertek;
							int index = 0;
							if (lepesLehetoseg.size() > 1)
								index = rand.nextInt(lepesLehetoseg.size() - 1);
							vedekezes.put(i, lepesLehetoseg.elementAt(index));
						} else if (i.ertek == tamadottMax) {
							int index = 0;
							if (lepesLehetoseg.size() > 1)
								index = rand.nextInt(lepesLehetoseg.size() - 1);
							vedekezes.put(i, lepesLehetoseg.elementAt(index));
						}
					}
				}
			}

		}
	}

	public boolean kiralyTamadas() {

		Mezo kiraly;
		Vector<Babu> sajat;
		boolean sikerult = false;
		Mezo jelen;
		if (oldal.equals("vilagos")) {
			sajat = vilagos;
			kiraly = sotet.elementAt(0).getKiralyomHelye();
		} else {
			sajat = sotet;
			kiraly = vilagos.elementAt(0).getKiralyomHelye();
		}
		for (Babu b : sajat) {
			if (b.lephet.size() == 0)
				continue;
			for (Mezo m : b.lephet) {
				jelen = b.getHolvan();
				b.setHolvan(m);
				if(b.HogyJutszIde(kiraly) != null)
				{
					System.out.println("SAKKOS LEPES");
					botLep(b,m);
					sikerult =  true;
				}
				b.setHolvan(jelen);
			}
		}
		
		return sikerult;
	}
}
