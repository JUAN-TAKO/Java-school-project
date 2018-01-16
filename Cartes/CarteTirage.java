/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartes;
/**
 *
 * @author claryd
 */
import Vues.ImagePool;
import java.awt.image.BufferedImage;
public enum CarteTirage {
    
    TRESOR_CALICE,
    TRESOR_PIERRE,
    TRESOR_STATUE,
    TRESOR_CRISTAL,
    MONTEE_DES_EAUX,
    HELICOPTERE,
    SABLE;
    private BufferedImage image;
    CarteTirage(String imagePath){
        image = ImagePool.getImage(imagePath);
    }
}
