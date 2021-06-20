package kellekek;
import java.util.*;
import java.io.*;

public class Fiokok implements Serializable{
	private static HashMap<Profil,File> fiokok = new HashMap<Profil,File>();
	
	public void add(Profil fiok) throws IOException{
		if(!fiokok.containsKey(fiok))
		{
			File ujMentes = new File("mentes//"+fiok.getNev()+"_save_file.txt");
			if(ujMentes.createNewFile())
			{
				fiokok.put(fiok,ujMentes);
			}
		
		}
	}
	public void save() throws IOException {
		FileOutputStream mentes = new FileOutputStream( "mentes//fiok.txt");
		ObjectOutputStream out = new ObjectOutputStream(mentes);
		out.writeObject(fiokok);
		out.close();
		mentes.close();
	}
	public void load() throws IOException, ClassNotFoundException
	{
		fiokok.clear();
		FileInputStream betoltes = new FileInputStream("mentes/fiok.txt");
		ObjectInputStream in = new ObjectInputStream(betoltes);
		fiokok.putAll((HashMap<Profil,File>) in.readObject());
		in.close();
		betoltes.close();
	}
	public HashMap<Profil,File> getFiokok() {
		return fiokok;
	}
	public static void saveMeccs(Profil p) throws IOException{
		
		FileOutputStream mentes = new FileOutputStream( fiokok.get(p));
		ObjectOutputStream out = new ObjectOutputStream(mentes);
		out.writeObject(p);
		out.close();
		mentes.close();

	}
public static void loadMeccs(Profil p) throws IOException,ClassNotFoundException{
		
	FileInputStream betoltes = new FileInputStream(fiokok.get(p));
	ObjectInputStream in = new ObjectInputStream(betoltes);
	Profil tmp = ((Profil) in.readObject());
	p.fill(tmp.getVilagos(), tmp.getSotet(),tmp.getSoron(),tmp.getOldal());
	in.close();
	betoltes.close();

	}
	public ArrayList<String> list() {
		ArrayList<String> tmp = new ArrayList<>();
		for(Profil key : fiokok.keySet())
		{
			tmp.add(key.getNev());
		}
		return tmp;
	}
	
	
}
