/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Utils.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author juan
 */
public class PanelImage extends JPanel implements MouseListener{
    private ImageIcon image;
    private ImageIcon scaled;
    private JLabel imageLabel;
    private int fit;
    private CompositionObservable obs;
    private MessageType message;
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
    
    public PanelImage(String path, int f, CompositionObservable o, MessageType m){
        super(new BorderLayout());
        fit = f;
        obs = o;
        message = m;
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
            if(wn != 0 && hn != 0){
                scaled = new ImageIcon(image.getImage().getScaledInstance(wn, hn, java.awt.Image.SCALE_AREA_AVERAGING));
                imageLabel.setIcon(scaled);
            }
            revalidate();
            repaint();
        }
    }
    
    public float getRatio(){
        int wi = image.getIconWidth();
        int hi = image.getIconHeight();
        return (float)hi / (float)wi;
    }
    
    public int getH(){
        return scaled != null ? scaled.getIconHeight() : 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(obs != null){
            obs.setChanged();
            obs.notifyObservers(new Message(message));
            obs.clearChanged();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
