package kellekek;
import javax.swing.*;
import java.util.*;
import java.io.*;
abstract class Babu implements Serializable {
	protected Mezo hol_van;
	protected Vector<Mezo> lephet = new Vector<>();
	protected int ertek;
	protected String szin;
	protected Vector<Babu> tamadjak = new Vector<>();
	protected Vector<Babu> tamad = new Vector<>();
	protected Vector<Babu> kit_gatol = new Vector<>();
	protected Vector<Babu> vedi = new Vector<>();
	protected Icon icon;
	protected static Vector<Mezo> koztuk = new Vector<>();
	protected boolean enPassantKaphat = false;
	protected boolean megNemLepett = true; 
	protected Mezo kiralyomHelye;
	
	
	public void lep(Mezo hova) {
		lepHelp(hova);
		megNemLepett = false;
	}
	
	public void lepHelp(Mezo hova) {
		hol_van.getSakkTabla().nincsEnPassant(szin);
		vedi.clear();
		hol_van.setRajta(null);
		hol_van.setIcon(null);
		hol_van = hova;
		felrak();
		if(hol_van.getVer() == 1 && hol_van.getHor() == 3)
			System.out.println("TESZT1.0 : " + hol_van.getRajta());
		for(Babu i : tamad) {
			i.TamadoEl(this);
		}
		tamad.clear();
		tamadjak.clear();
		Vector<Babu> kit_gatolTMP = new Vector<Babu>(); 
		kit_gatolTMP.addAll(kit_gatol);
		
		kit_gatol.clear();
		
		for (Babu i : kit_gatolTMP) {
			i.hova_lephet();
		}
		hova_lephet();		
		for(Babu i : tamad)
		{
			i.hova_lephet();
		}
		hol_van.getSakkTabla().leptek(hol_van);
 		
	}
	public void felrak() {
		hol_van.setRajta(this);
		hol_van.setIcon(icon);
	}
	
	
	abstract void hova_lephet();
	public String getSzin() {
		return szin;
	}
	public Babu(Mezo pos,String col) {
		szin = col;
		hol_van = pos;
	}
	public Icon getIcon() {
		return icon;
	}
	
	public void Tamadja(Babu b) {
		tamadjak.add(b);
	}
	public void TamadoEl(Babu b) {
		if(b.szin.equals(szin))
			vedi.remove(b);
		else {
		tamadjak.remove(b);
		}
	}
	public boolean lephetHelp(Mezo tmp) {
		
		if(hol_van.getSakkTabla().getSakk().equals(szin) && !koztuk.contains(tmp))
		{
			return false;
		}
		Vector<Mezo> kiralytVedve = tamadtokHaEnNemLeszek(kiralyomHelye);		
		if(kiralytVedve != null && !kiralytVedve.contains(tmp))
		{
			return false;
		}
		if(tmp.getRajta() == null)
		{
			lephet.add(tmp);
			if(hol_van.getVer() == 3 && hol_van.getHor() == 1)
				System.out.println("IDELEPNE Mert NULLA " + tmp.getVer() + " " + tmp.getHor() );
			return true;
		}
		
		tamadHelp(tmp);
		return false;
	}
	public void gatolsz(Babu engem) {
		if(!kit_gatol.contains(engem))
		{
			kit_gatol.add(engem);

		}
	}
	public void tamadHelp(Mezo tmp) {
		tmp.getRajta().gatolsz(this);
		tamad.add(tmp.getRajta());
		if(tmp.getRajta().getSzin().equals(szin))
		{
			tmp.getRajta().vedi.add(this);
		}
		else {
			lephet.add(tmp);
			tmp.getRajta().Tamadja(this);
		}	
	}
	public void valakiElment(Mezo m) {
		if(lephet.contains(m))
		{
			if(hol_van.getVer() == 3 && hol_van.getHor() == 1)
				System.out.println("TesztV2");
			hova_lephet();
		}
	}
	public boolean tamadjaMezo(Mezo m) {
		if(lephet.contains(m) || (hol_van == m && vedi.size() != 0))
		{
			return true;
		}
		return false;
	}
	public Mezo getHolvan() {
		return hol_van;
	}
	public int getErtek() {
		return ertek;
	}
	public boolean enPassantMehet() {
		return enPassantKaphat;
	}
	public void enPassantNemMehet() {
		enPassantKaphat = false;
	}

	abstract Vector<Mezo> HogyJutszIde(Mezo b);
	
	public Vector<Babu> get_kit_gatol() {
		return kit_gatol;
	}
	public Vector<Mezo>  tamadtokHaEnNemLeszek(Mezo b){
		hol_van.setRajta(null);
		Vector<Mezo> tmp = new Vector<>();
		for(Babu tamado : tamadjak)
		{
			
				tmp = tamado.HogyJutszIde(b);
				if(tmp != null)
				{
					hol_van.setRajta(this);
					return tmp;
				}
		}
		hol_van.setRajta(this);
		return null;	
	}
	public void setKiralyomHelye(Mezo m) {
		kiralyomHelye = m;
	}
	public Mezo getKiralyomHelye() {
		return kiralyomHelye;
	}
	public Vector<Babu> getVedi() {
		return vedi;
	}
	public void setHolvan(Mezo hova) {
		hol_van = hova;
	}
	public void setBackKoztuk() {
		koztuk.clear();
	}
}
	