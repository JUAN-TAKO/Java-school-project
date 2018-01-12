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
import javax.swing.*;

public class VueJoueur{
    private final JFrame window ;
    
     private ImageIcon explorateur = new ImageIcon("src/Images.personnages/explorateur.png");
     
     private ImageIcon calice = new ImageIcon("src/Images.cartes/Calice.png");
     private ImageIcon cristal = new ImageIcon("src/Images.cartes/Cristal.png");
     private ImageIcon zephyr = new ImageIcon("src/Images.cartes/Zephyr.png");
     private ImageIcon pierre = new ImageIcon("src/Images.cartes/Pierre.png");
    
    
    
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
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel(new GridLayout(2,1));
        mainPanel.add(panelOuest, BorderLayout.WEST);
        panelOuest.add(new JLabel(explorateur));
        panelOuest.add(new JLabel("Joueur 1"));
        
        // =================================================================================
        // EST
        JPanel panelEst = new JPanel(new GridLayout(5,1,0,10));
        mainPanel.add(panelEst, BorderLayout.EAST);
        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        panelEst.add(new JLabel(calice));
        // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel();
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        
        panelCentre.add(new JLabel());        
                
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel() ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
                     
    }
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    
    
    public static void main(String [] args) {
        VueJoueur joueur = new VueJoueur();
        joueur.afficher();
   }
    
    
}