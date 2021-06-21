package kellekek;

import java.util.*;
import java.io.*;
public class Profil implements Serializable {
	protected String nev;
	protected Vector<Babu> vilagos;
	protected Vector<Babu> sotet;
	private String soronlevo;
	protected String oldal;
	public Profil(String n) {
		nev = n;
	}
	
	public void Reset() {
		vilagos = null;
		sotet = null;
	}
	public void fill(Vector<Babu> v, Vector<Babu> s, String sor,String old) {
		vilagos = v;
		sotet = s;
		soronlevo = sor;
		oldal = old;
	}
	public void fill(Vector<Babu> v, Vector<Babu> s, String sor) {
		vilagos = v;
		sotet = s;
		soronlevo = sor;
	}
	public Vector<Babu> getVilagos() {
		return vilagos;
	}
	public Vector<Babu> getSotet() {
		return sotet;
	}
	public String getNev() {
		return nev;
	}
	public String getSoron() {
		return soronlevo;
	}
	public String getOldal() {
		return oldal;
	}
	public void setOldal(String old) {
		oldal = old;
	}
	public void fill(Vector<Babu> vilagos,Vector<Babu> sotet)
	{
		this.vilagos = vilagos;
		this.sotet = sotet;
	}
	public void activateBot() {}
	
	public void ellenfel(Profil p) {
		if(p.oldal.equals("vilagos"))
			oldal = "sotet";
		else
			oldal = "vilagos";
	}
}
