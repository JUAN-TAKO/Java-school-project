/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Vues.ImagePool;
import java.awt.Color;
import java.awt.Image;
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
    ROUGE(Color.RED, "src/Images/pions/pionRouge.png", "src/Images/personnages/IngenieurDetour.png"),
    BLEU(Color.BLUE, "src/Images/pions/pionBleu.png", "src/Images/personnages/PiloteDetour.png"),
    VERT(Color.GREEN, "src/Images/pions/pionVert.png", "src/Images/personnages/ExplorateurDetour.png"),
    JAUNE(Color.YELLOW, "src/Images/pions/pionJaune.png", "src/Images/personnages/NavigateurDetour.png"),
    NOIR(Color.BLACK, "src/Images/pions/pionNoir.png", "src/Images/personnages/PlongeurDetour.png"),
    GRIS(Color.GRAY, "src/Images/pions/pionGris.png", "src/Images/personnages/MessagerDetour.png");
    
    private Color couleur;
    private String image;
    private String imageJoueur;
    Pion(Color c, String icon, String pathJoueur){
        couleur = c;
        image = icon;
        imageJoueur = pathJoueur;
    }
    public Color getColor(){
        return couleur;
    }
    public String getIcon(){
        return image;
    }
    public String getJoueur(){
        return imageJoueur;
    }
}
