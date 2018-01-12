/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author juan
 */
public enum Pion {
    ROUGE(Color.RED, "src/Images/pions/pionRouge.png"),
    BLEU(Color.BLUE, "src/Images/pions/pionBleu.png"),
    VERT(Color.GREEN, "src/Images/pions/pionVert.png"),
    JAUNE(Color.YELLOW, "src/Images/pions/pionJaune.png"),
    NOIR(Color.BLACK, "src/Images/pions/pionNoir.png"),
    GRIS(Color.GRAY, "src/Images/pions/pionGris.png");
    
    private Color couleur;
    private BufferedImage image;
    Pion(Color c, String icon){
        couleur = c;
        try {
            image = ImageIO.read(new File(icon));
        } catch (IOException ex) {
            System.out.println("AAHAAA hha ahhahaah hhhhhh \n" + ex.getMessage());
        }
    }
    public Color getColor(){
        return couleur;
    }
    public BufferedImage getIcon(){
        return image;
    }
}