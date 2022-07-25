package tau.asteroidgame.UserInterface;

import tau.asteroidgame.Material;
import tau.asteroidgame.Util.CommandDatabase;

import javax.swing.*;
/**
 * Az osztály felelõssége egy esetekben klikkelhetõ nyersanyag kirajzolása egy gombra,
 *  mely parancsát átállítja úgy, hogy az õ által reprezentált nyersanyagot a pillanatnyi
 *  aszteroidára le lehessen tenni
 *
 **/
public class MaterialViewControl extends ViewControl{
    private Material material;
    private ImageIcon materialImage;
    
    public MaterialViewControl(Material mat, ImageIcon img) {
    	material = mat;
    	materialImage = img;
    }
    /**
     * Kirazolja a paraméterben kapott gombra a nyersanyagot reprezentáló képet,
     * majd megnyomhatatlanná teszi (Egy aszteroida magjában lévõ nyersanyag).
     * @param j A gomb amire a képet kell kirajzolnia.
     */
    public void draw(JButton j){
    	j.setEnabled(false);
    	j.setIcon(materialImage);
    }
    
    /**
     * Kirazolja a paraméterben kapott gombra a nyersanyagot reprezentáló képet és
     * megnyomhatóvá teszi, amivel a játék le tudja helyezni az aszteroidára amin éppen áll
     * (Egy telepes táskájában lévõ nyersanyag).
     * @param j A gomb amire a képet kell kirajzolnia.
     */
    public void drawAsItem(JButton j){
    	j.setEnabled(true);
    	j.setIcon(materialImage);
    	String matid;
    	matid = CommandDatabase.getMaterialID(material);
    	j.setActionCommand("placeItem_" + matid);
    }

}
