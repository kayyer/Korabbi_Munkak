package kellekek;

import java.util.*;
import java.io.*;
public class Profil implements Serializable {
	protected String nev;
	protected Vector<Babu> vilagos;
	protected Vector<Babu> sotet;
	private String soronlevo;
	protected String oldal;
	protected int hatra = -1;
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
	public void activateBot() {
		
	}
	public void setHatra(int h) {
		hatra = h;
	}
	public int getHatra() {
		return hatra;
	}
	
}
