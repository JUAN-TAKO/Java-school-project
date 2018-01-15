package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class VueJoueur{
    private final JFrame window ;
    private JPanel panelHaut;
    private JPanel panelOuest;
    private JPanel panelEst;
    private JPanel panelCentre;
    private JPanel panelBas;
    
    
     private ImageIcon explorateur = new ImageIcon("src/Images/personnages/explorateur.png");
     
     private ImageIcon baseCalice = new ImageIcon("src/Images/cartes/Calice.png");
     private ImageIcon baseCristal = new ImageIcon("src/Images/cartes/Cristal.png");
     private ImageIcon baseZephyr = new ImageIcon("src/Images/cartes/Zephyr.png");
     private ImageIcon basePierre = new ImageIcon("src/Images/cartes/Pierre.png");
     
     private ImageIcon calice;
     private ImageIcon cristal;
     private ImageIcon zephyr;
     private ImageIcon pierre;
    
    
    
    public VueJoueur(){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Joueur");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        // =================================================================================
        // OUEST 
        panelOuest = new JPanel(new GridLayout(2,1));
        mainPanel.add(panelOuest, BorderLayout.WEST);
        panelOuest.add(new JLabel(explorateur));
        panelOuest.add(new JLabel("Joueur 1"));
        
        // =================================================================================
        // EST
        panelEst = new JPanel(new GridLayout(1,5,0,10));
        mainPanel.add(panelEst, BorderLayout.EAST);

        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        // =================================================================================
        // CENTRE
        panelCentre = new JPanel();
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        
        panelCentre.add(new JLabel());        
                
        // =================================================================================
        // SUD
        panelBas = new JPanel() ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
                     
    }
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    
    private void loadImages(){
        JLabel l = ((JLabel)(panelEst.getComponent(0)));
        int minSize = Math.min(l.getWidth(), l.getHeight());
        calice = resizeIcon(baseCalice, minSize);
        update();
    }
    
    
    
    private ImageIcon resizeIcon(ImageIcon ii, int width){
        return new ImageIcon(ii.getImage().getScaledInstance(width, width, java.awt.Image.SCALE_AREA_AVERAGING));
    }
    
    public void update(){
        JLabel c = ((JLabel)(panelCentre.getComponent()));
        c.setHorizontalAlignment(SwingConstants.CENTER);
        c.setVerticalAlignment(SwingConstants.CENTER);
        //c.setIcon(getIcon(s));
        panelCentre.revalidate();
        panelCentre.repaint();
    }
    
    public void updateCase(int i, Symbole s){
        JLabel c = ((JLabel)(panelCentre.getComponent(i)));
        c.setHorizontalAlignment(SwingConstants.CENTER);
        c.setVerticalAlignment(SwingConstants.CENTER);
        //c.setIcon(getIcon(s));
    }
    
    
    public static void main(String [] args) {
        VueJoueur joueur = new VueJoueur();
        joueur.afficher();
   }
    
    
}