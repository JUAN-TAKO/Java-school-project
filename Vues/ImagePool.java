/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author juan
 */
public class ImagePool{
    private static HashMap<String, ImageIcon> images;
    public static ImageIcon getImageIcon(String path){
        if(images == null){
            images = new HashMap<>();
        }
        ImageIcon r = images.get(path);
        if(r == null){
            images.put(path, new ImageIcon(path));
            r = images.get(path);
        }
        return r;
    }
    public static Image getImage(String path){
        return getImageIcon(path).getImage();
    }
}
