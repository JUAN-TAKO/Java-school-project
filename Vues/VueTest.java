/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Utils.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author juan
 */
public class VueTest extends JPanel{
    private BufferedImage combined;
    private JLabel testLabel;
    public VueTest(ArrayList<Pion> pions){
        try {
            testLabel = new JLabel();
            float margin = 0.15f;
            ArrayList<BufferedImage> imagesPions = new ArrayList<>();
            int[] margins = new int[4]; 
            //charge les images
            BufferedImage imageTuile = ImageIO.read(new File("src/Images/tuiles/Heliport.png"));         
            for( Pion p : pions){
                imagesPions.add(p.getIcon());
            }
            
            BufferedImage combined = new BufferedImage(imageTuile.getWidth(), imageTuile.getHeight(), BufferedImage.TYPE_INT_ARGB);
            
            int w = imagesPions.get(1).getWidth();
            int h = imagesPions.get(0).getHeight();
            margins[0] = (int)(margin * (float)imageTuile.getWidth()); //marge gauche
            margins[1] = imageTuile.getWidth() - w; //marge droite
            margins[2] = (int)(margin * (float)imageTuile.getHeight()); //marge haut
            margins[3] = imageTuile.getHeight() - margins[2] - h; //marge bas
            
            //dessine les images sur la tuile
            Graphics g = combined.getGraphics();
            g.drawImage(imageTuile, 0, 0, null);
            for(int i = 0; i < imagesPions.size(); i++){
                System.out.println(margins[i]);
                g.drawImage(imagesPions.get(i), margins[i % 2], margins[2+(i / 2)], null);
            }
          
            ImageIcon icon1 = new ImageIcon(combined);
            testLabel.setIcon(icon1);
            add(testLabel);
        } catch (IOException ex) {
            System.out.println("AieAieAie !");
        }
    }
    public static void main(String args[]){
        JFrame window = new JFrame();
        ArrayList<Pion> pions = new ArrayList<>();
        pions.add(Pion.GRIS);
        pions.add(Pion.NOIR);
        pions.add(Pion.ROUGE);
        pions.add(Pion.VERT);
  
        VueTest v = new VueTest(pions);
        window.add(v);
        window.setVisible(true);
    }
}
