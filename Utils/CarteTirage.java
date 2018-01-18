/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
/**
 *
 * @author claryd
 */
import Vues.ImagePool;
import java.awt.image.BufferedImage;

public enum CarteTirage {
    
    TRESOR_CRISTAL("src/Images/cartes/Cristal.png"),
    TRESOR_PIERRE("src/Images/cartes/Pierre.png"),
    TRESOR_CALICE("src/Images/cartes/Calice.png"),
    TRESOR_STATUE("src/Images/cartes/Zephyr.png"),
    HELICOPTERE("src/Images/cartes/Helicoptere.png"),
    SABLE("src/Images/cartes/SacsDeSable.png"),
    MONTEE_DES_EAUX("src/Images/cartes/MonteeDesEaux.png");
    
    private String image;
    CarteTirage(String imagePath){
        image = imagePath;
    }
    
    public String getImage(){
        return image;
    }
}
