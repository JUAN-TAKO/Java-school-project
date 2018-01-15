/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

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
    
    public PanelImage(String path){
        imageLabel = new JLabel();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeIcon();
            }
        });
        image = new ImageIcon(path);
        add(imageLabel);
    }
    
    private void resizeIcon(){
        int wi = image.getIconWidth();
        int hi = image.getIconHeight();
        float ratioImage = (float)wi / (float)hi;
        int wp = getWidth();
        int hp = getHeight();
        float ratioPanel = (float)wp / (float)hp;
        int wn, hn;
        if(ratioPanel > ratioImage){
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
    public static void main(String args[]){
        JFrame window = new JFrame();
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        window.add(mainPanel);
        window.setSize(1150, 700);
        mainPanel.add(new PanelImage("src/Images/cartes/Calice.png"));
        mainPanel.add(new PanelImage("src/Images/cartes/Cristal.png"));
        mainPanel.add(new PanelImage("src/Images/cartes/Pierre.png"));
        
        window.setVisible(true);
    }
}
