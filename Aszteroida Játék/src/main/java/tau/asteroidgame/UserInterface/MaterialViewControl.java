package tau.asteroidgame.UserInterface;

import tau.asteroidgame.Material;
import tau.asteroidgame.Util.CommandDatabase;

import javax.swing.*;
/**
 * Az oszt�ly felel�ss�ge egy esetekben klikkelhet� nyersanyag kirajzol�sa egy gombra,
 *  mely parancs�t �t�ll�tja �gy, hogy az � �ltal reprezent�lt nyersanyagot a pillanatnyi
 *  aszteroid�ra le lehessen tenni
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
     * Kirazolja a param�terben kapott gombra a nyersanyagot reprezent�l� k�pet,
     * majd megnyomhatatlann� teszi (Egy aszteroida magj�ban l�v� nyersanyag).
     * @param j A gomb amire a k�pet kell kirajzolnia.
     */
    public void draw(JButton j){
    	j.setEnabled(false);
    	j.setIcon(materialImage);
    }
    
    /**
     * Kirazolja a param�terben kapott gombra a nyersanyagot reprezent�l� k�pet �s
     * megnyomhat�v� teszi, amivel a j�t�k le tudja helyezni az aszteroid�ra amin �ppen �ll
     * (Egy telepes t�sk�j�ban l�v� nyersanyag).
     * @param j A gomb amire a k�pet kell kirajzolnia.
     */
    public void drawAsItem(JButton j){
    	j.setEnabled(true);
    	j.setIcon(materialImage);
    	String matid;
    	matid = CommandDatabase.getMaterialID(material);
    	j.setActionCommand("placeItem_" + matid);
    }

}
