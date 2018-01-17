/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author juan
 */
public class PanelImage extends JPanel{
    private ImageIcon image;
    private ImageIcon scaled;
    private JLabel imageLabel;
    private int fit;
    public PanelImage(String path, int f){
        super(new BorderLayout());
        fit = f;
        imageLabel = new JLabel("", JLabel.CENTER);
        image = ImagePool.getImageIcon(path);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeIcon();
            }
        });
        add(imageLabel, BorderLayout.CENTER);
        
    }
    public PanelImage(){
        super(new BorderLayout());
        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);
    }
    private void resizeIcon(){
        if(image != null){
            int wi = image.getIconWidth();
            int hi = image.getIconHeight();
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
            scaled = new ImageIcon(image.getImage().getScaledInstance(wn, hn, java.awt.Image.SCALE_AREA_AVERAGING));
            imageLabel.setIcon(scaled);
            revalidate();
            repaint();
        }
    }
    public static void main(String args[]){
        JFrame window = new JFrame();
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        window.add(mainPanel);
        window.setSize(1150, 700);
        mainPanel.add(new PanelImage("src/Images/cartes/Calice.png", 0));
        mainPanel.add(new PanelImage("src/Images/cartes/Cristal.png", 0));
        mainPanel.add(new PanelImage("src/Images/cartes/Pierre.png", 0));
        
        window.setVisible(true);
    }
}
