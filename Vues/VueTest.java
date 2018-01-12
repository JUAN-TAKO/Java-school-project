/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

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

/**
 *
 * @author juan
 */
public class VueTest {
    private BufferedImage combined;
    private JLabel testLabel;
    private JFrame window;
    public VueTest(){
        try {
            testLabel = new JLabel();
            window = new JFrame();
            float margin = 0.07f;
            ArrayList<BufferedImage> imagesPions = new ArrayList<>();
            int[] margins = new int[4]; 
            //charge les images
            BufferedImage imageTuile = ImageIO.read(new File("src/Images/tuiles/Heliport.png"));         
            imagesPions.add(ImageIO.read(new File("src/Images/pions/pionBleu.png")));
            imagesPions.add(ImageIO.read(new File("src/Images/pions/pionRouge.png")));
            imagesPions.add(ImageIO.read(new File("src/Images/pions/pionJaune.png")));
            
            BufferedImage combined = new BufferedImage(imageTuile.getWidth(), imageTuile.getHeight(), BufferedImage.TYPE_INT_ARGB);
            
            margins[0] = (int)(margin * (float)imageTuile.getWidth());
            if(imagesPions.size() >= 2)
                margins[1] = imageTuile.getWidth() - margins[0] - imagesPions.get(1).getWidth();
            
            margins[2] = (int)(margin * (float)imageTuile.getHeight());
            if(imagesPions.size() >= 4)
                margins[3] = imageTuile.getHeight() - margins[2] - imagesPions.get(3).getHeight();
            
            //dessine les images sur la tuile
            Graphics g = combined.getGraphics();
            g.drawImage(imageTuile, 0, 0, null);
            for(int i = 0; i < imagesPions.size(); i++){
                g.drawImage(imagesPions.get(i), margins[i % 2], margins[2+(i / 2)], null);
            }
          
            ImageIcon icon1 = new ImageIcon(combined);
            testLabel.setIcon(icon1);
            window.add(testLabel);
        } catch (IOException ex) {
            System.out.println("AieAieAie !");
        }
    }
    public void setVisible(boolean b){
        window.setVisible(b);
    }
    public static void main(String args[]){
        VueTest v = new VueTest();
        v.setVisible(true);
    }
}
