/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Utils.*;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
public class PanelTuile extends JPanel{
    private BufferedImage combined;
    ArrayList<BufferedImage> imagesPions;
    private JLabel labelTuile;
    private ImageIcon scaled;
    private TypeTuile tuile;
    
    public PanelTuile(TypeTuile t, Etat e, ArrayList<Pion> pions){
        tuile = t;
        labelTuile = new JLabel();
        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeIcon(Math.min(getWidth(), getHeight()-5));
            }
        });
        imagesPions = new ArrayList<>();
        for( Pion p : pions){
            imagesPions.add(p.getIcon());
        }
        update(e);
        add(labelTuile);
    }
    
    public void resizeIcon(int width){
        if(combined != null){
            scaled = new ImageIcon(combined.getScaledInstance(width, width, java.awt.Image.SCALE_AREA_AVERAGING));
            labelTuile.setIcon(scaled);
            revalidate();
            repaint();
        }
        else{
            labelTuile.setIcon(null);
        }
    }
    public void update(Etat e){
        try {
            float margin = 0.15f;
            float rightAdjust = 0.7f;
            float bottomAdjust = 1.1f;
            int[] margins = new int[4];
            BufferedImage imageTuile = null;
            if(e == Etat.SECHE){
                imageTuile = ImageIO.read(new File(tuile.getImagePath()));
            }else if(e == Etat.INNONDEE){
                imageTuile = ImageIO.read(new File(tuile.getImagePathInonde()));  
            }
            combined = null;
            if(imageTuile != null){
                combined = new BufferedImage(imageTuile.getWidth(), imageTuile.getHeight(), BufferedImage.TYPE_INT_ARGB);
                if(imagesPions.size() != 0){
                    int w = imagesPions.get(0).getWidth();
                    int h = imagesPions.get(0).getHeight();
                    margins[0] = (int)(margin * (float)imageTuile.getWidth());
                    margins[1] = imageTuile.getWidth() - margins[0] - (int)(rightAdjust * (float)w);
                    margins[2] = (int)(margin * (float)imageTuile.getHeight());
                    margins[3] = imageTuile.getHeight() - margins[2] - (int)(bottomAdjust * (float)h);
                }
                Graphics g = combined.getGraphics();
                g.drawImage(imageTuile, 0, 0, null);
                for(int i = 0; i < imagesPions.size(); i++){
                    g.drawImage(imagesPions.get(i), margins[i % 2], margins[2+(i / 2)], null);
                }
            }
        } catch (IOException ex) {
            System.out.println("AieAieAie ! " + ex.getMessage());
        }
        
        
    }
    public static void main(String args[]){
        JFrame window = new JFrame();
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        window.add(mainPanel);
        ArrayList<Pion> pions = new ArrayList<>();
        pions.add(Pion.GRIS);
        pions.add(Pion.NOIR);
        pions.add(Pion.ROUGE);
        pions.add(Pion.VERT);
        ArrayList<Pion> pions2 = new ArrayList<>();
        pions2.add(Pion.JAUNE);
        mainPanel.add(new PanelTuile(TypeTuile.CAVERNE_BRASIER, Etat.SECHE, pions));
        mainPanel.add(new PanelTuile(TypeTuile.PORTE_CUIVRE, Etat.INNONDEE, pions2));
        mainPanel.add(new PanelTuile(TypeTuile.OBSERVATOIRE, Etat.COULEE, new ArrayList<>()));
        
        window.setSize(1150, 700);
        
        window.setVisible(true);
    }
}