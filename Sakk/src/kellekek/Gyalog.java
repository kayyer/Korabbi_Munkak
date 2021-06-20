package kellekek;

import java.util.Vector;

import javax.swing.ImageIcon;

public class Gyalog extends Babu {
	private Vector<Mezo> uthet = new Vector<>();
	private boolean enPassant = false;

	public Gyalog(Mezo pos, String col) {
		super(pos, col);
		ertek = 1;
		if (szin == "sotet")
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-pawn-chessboard-1-3963.png");
		else
			icon = new ImageIcon("chess//png_24//games-battle-checkmate-chess-figure-pawn-chessboard-2-3969.png");

	}

	public void hova_lephet() {
		enPassant = false;
		lephet.clear();
		uthet.clear();
		int merre = 1;
		int kezdo = 1;
		boolean masod = false;
		if (szin.equals("vilagos")) {
			kezdo = 6;
			merre *= -1;
		}
		if ((getSzin().equals("vilagos") && hol_van.getVer() == 3) || (getSzin().equals("sotet") && hol_van.getVer() == 4)) {
			if (hol_van.getHor() + 1 < 8) {
				Babu tmpM1 = hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() + 1).getRajta();
				if (tmpM1 != null && !tmpM1.getSzin().equals(szin) && tmpM1.enPassantMehet()) {
					enPassant = true;
					lephet.add(hol_van.getSakkTabla().getMezo(tmpM1.getHolvan().getVer() - merre, tmpM1.getHolvan().getHor()));

				}
			}
			if (hol_van.getHor() - 1 >= 0) {
				Babu tmpM2 = hol_van.getSakkTabla().getMezo(hol_van.getVer(), hol_van.getHor() - 1).getRajta();
				if (tmpM2 != null && !tmpM2.getSzin().equals(szin) && tmpM2.enPassantMehet()) {
					enPassant = true;
					lephet.add(hol_van.getSakkTabla().getMezo(tmpM2.getHolvan().getVer() - merre, tmpM2.getHolvan().getHor()));
				}
			}

		}
		if (hol_van.getVer() + merre >= 0 && hol_van.getVer() + merre < 8) {
			masod = lephetHelp(hol_van.getSakkTabla().getMezo(hol_van.getVer() + merre, hol_van.getHor()));
		}
		Mezo dupla = hol_van.getSakkTabla().getMezo(kezdo + merre * 2, hol_van.getHor());
		if((hol_van.getSakkTabla().getSakk().equals(szin) && dupla.getRajta() == null && koztuk.contains(dupla)))
				masod = true;
		if (masod && hol_van.getVer() == kezdo) {
			Mezo tmp = hol_van.getSakkTabla().getMezo(kezdo + merre * 2, hol_van.getHor());
			if (tmp.getRajta() == null)
				lephet.add(tmp);

		}

	}

	public boolean lephetHelp(Mezo tmp) {
		Vector<Mezo> kiralytVedve = tamadtokHaEnNemLeszek(kiralyomHelye);
		boolean lephete = false;
		if ((!hol_van.getSakkTabla().getSakk().equals(szin) || koztuk.contains(tmp)) && (kiralytVedve == null || kiralytVedve.contains(tmp))) {
			if (tmp.getRajta() == null) {
				lephet.add(tmp);
				lephete = true;
			} else {
				tmp.getRajta().gatolsz(this);
			}

		}
		if (tmp.getHor() - 1 >= 0) {
			Mezo tmp1 = hol_van.getSakkTabla().getMezo(tmp.getVer(), tmp.getHor() - 1);
			if ((!hol_van.getSakkTabla().getSakk().equals(szin) || koztuk.contains(tmp1)) && (kiralytVedve == null || kiralytVedve.contains(tmp1))) {
				uthet.add(tmp1);
				if (tmp1.getRajta() != null)
					tamadHelp(tmp1);
			}
		}

		if (tmp.getHor() + 1 < 8) {
			Mezo tmp2 = hol_van.getSakkTabla().getMezo(tmp.getVer(), tmp.getHor() + 1);
			if ((!hol_van.getSakkTabla().getSakk().equals(szin) || koztuk.contains(tmp2)) && (kiralytVedve == null || kiralytVedve.contains(tmp2))) {
				uthet.add(tmp2);
				if (tmp2.getRajta() != null)
					tamadHelp(tmp2);
			}
		}

		return lephete;
	}

	public void valakiElment(Mezo m) {

		int merre = 1;
		if (szin.equals("vilagos"))
			merre = -1;
		if (lephet.contains(m) || (hol_van.getHor() - 1 >= 0 && hol_van.getSakkTabla().getMezo(hol_van.getVer() + merre, hol_van.getHor() - 1) == m)
				|| (hol_van.getHor() + 1 < 8 && hol_van.getSakkTabla().getMezo(hol_van.getVer() + merre, hol_van.getHor() + 1) == m))
		{
			hova_lephet();
		}
	}

	public boolean tamadjaMezo(Mezo m) {
		if (uthet.contains(m) || (hol_van == m && vedi.size() != 0))
			return true;
		return false;
	}

	public Vector<Mezo> HogyJutszIde(Mezo b) {
		if(tamad.contains(b.getRajta()))
		{
			Vector<Mezo> tmp = new Vector<>();
			tmp.add(hol_van);
			return tmp;
		}
		return null;
	}

	public void lep(Mezo hova) {

		if (enPassant) {
			int merre = 1;
			if (szin.equals("sotet"))
				merre *= -1;
			Babu b = hol_van.getSakkTabla().getMezo(hova.getVer() + merre, hova.getHor()).getRajta();
			if (b != null && b.enPassantMehet()) {
				hol_van.getSakkTabla().leutottek(b);
			}

		}
		hol_van.getSakkTabla().nincsEnPassant(szin);
		if (Math.abs(hova.getVer() - hol_van.getVer()) == 2) {
			enPassantKaphat = true;
			if (hova.getHor() + 1 < 8 && hol_van.getSakkTabla().getMezo(hova.getVer(), hova.getHor() + 1).getRajta() != null) {
				hol_van.getSakkTabla().getMezo(hova.getVer(), hova.getHor() + 1).getRajta().hova_lephet();
			}
			if (hova.getHor() - 1 >= 0 && hol_van.getSakkTabla().getMezo(hova.getVer(), hova.getHor() - 1).getRajta() != null) {
				hol_van.getSakkTabla().getMezo(hova.getVer(), hova.getHor() - 1).getRajta().hova_lephet();
			}
		}
		vedi.clear();
		hol_van.setRajta(null);
		hol_van.setIcon(null);
		hol_van = hova;
		for (Babu i : tamad) {
			i.TamadoEl(this);
		}
		tamad.clear();
		tamadjak.clear();
		Vector<Babu> kit_gatolTMP = new Vector<Babu>();
		kit_gatolTMP.addAll(kit_gatol);
		
		kit_gatol.clear();
		
		for (Babu i : kit_gatolTMP) {
			System.out.println(i + " gatol");
			System.out.println(hol_van.getSakkTabla().getMezo(6, 3).getRajta() == null);
			i.hova_lephet();
		}
		if ((szin.equals("vilagos") && hol_van.getVer() == 0) || (szin.contentEquals("sotet") && hol_van.getVer() == 7))
			hol_van.getSakkTabla().Promotion(this);
		else {
			hova_lephet();
			for(Babu i : tamad)
			{
				i.hova_lephet();
			}
			hol_van.getSakkTabla().leptek(hol_van);
			felrak();
		}
		megNemLepett = false;

	}
	public void gatolsz(Babu engem) {
		if(!kit_gatol.contains(engem))
		{
			kit_gatol.add(engem);
		}
		
		
		
	}

}
