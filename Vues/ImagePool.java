/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author juan
 */
public class ImagePool{
    private static HashMap<String, BufferedImage> images;
    public static ImageIcon getImageIcon(String path){
        return new ImageIcon(getImage(path));
    }
    public static BufferedImage getImage(String path){
        
        if(images == null){
            images = new HashMap<>();
        }
        BufferedImage r = images.get(path);
        if(r == null){
            try {
                images.put(path, ImageIO.read(new File(path)));
                r = images.get(path);
            } catch (IOException ex) {
                System.out.println("AieAieAie ! " + path + " n'existe pas !");
            }
        }
        return r;
    }
}
