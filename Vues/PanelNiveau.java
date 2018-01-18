package Vues;

import Utils.Etat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import Utils.Parameters;
import Vues.ImagePool;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
 
public class PanelNiveau extends JPanel{
    
    private int niveau;
    private BufferedImage combined;
    private JLabel labelImage;
    private ImageIcon scaled;
    private int fit;
        
    public PanelNiveau(int niveauInitial, int f) {
        fit = f;
        niveau = niveauInitial;
        labelImage = new JLabel();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeIcon();
            }
        });
        redraw();
        add(labelImage);
    }
    public void setNiveau(int n){
        niveau = n;
        System.out.println("niveau : " + niveau);
        redraw();
    }
    private void resizeIcon(){
        if(getWidth() != 0){
            int wi = combined.getWidth();
            int hi = combined.getHeight();
            float ratioImage = (float)wi / (float)hi;
            int wp = getWidth();
            int hp = getHeight();
            float ratioPanel = (float)wp / (float)hp;
            int wn, hn;
            if((fit == 1 || ratioPanel > ratioImage) && fit != 2){
                hn = hp;
                wn = (int)((float)hp * ratioImage);
            }
            else{
                wn = wp;
                hn = (int)((float)wp / ratioImage);
            }

            scaled = new ImageIcon(combined.getScaledInstance(wn, hn, java.awt.Image.SCALE_AREA_AVERAGING));
            labelImage.setIcon(scaled);
            revalidate();
            repaint();
        }
    }
    
    public void redraw(){
        float step = 0.0825f;
        float offset = 0.05f;
        BufferedImage imageNiveau = ImagePool.getImage("src/Images/Niveau.png");
        BufferedImage selector = ImagePool.getImage("src/Images/stick.png");
        combined = new BufferedImage(imageNiveau.getWidth(), imageNiveau.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics g = combined.getGraphics();
        g.drawImage(imageNiveau, 0, 0, null);
        int posX = 0;
        int posY = (int)((offset + (float)(9-niveau) * step) * (float)imageNiveau.getHeight());
        g.drawImage(selector, posX, posY, null);
        if(getWidth() != 0){
            resizeIcon();
        }
    }
    public float getRatio(){
        int wi = combined.getWidth();
        int hi = combined.getHeight();
        return (float)hi / (float)wi;
    }
    public static void main(String[] args){   
        PanelNiveau vueNiveau = new PanelNiveau(0, 0);
        JFrame window = new JFrame();
        window.add(vueNiveau);
        window.setSize(400, 900);
        window.setVisible(true);
        for(int i = 1; i < 10; i++){
            System.out.println("Appuyez sur une touche");
            try {
                System.in.read();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            vueNiveau.setNiveau(i);
        }
    }    
}