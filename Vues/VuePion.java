package Vues;

import Utils.Message;
import Utils.MessageType;
import Utils.Pion;
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
import java.util.ArrayList;
import javax.swing.*;

public class VuePion extends Observable{
    private final JFrame window ;
    
    private JButton boutonOui;
    private JButton boutonNon;
    
    
    public VuePion(ArrayList<Pion> listesPion, ArrayList<String> listesNom){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 100);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Donner Carte");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(new JLabel("CHOISISSEZ UN JOUEUR : ")) ;
        
        // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel();
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
        // =================================================================================
        // EST
        JPanel panelEst = new JPanel();
        mainPanel.add(panelEst, BorderLayout.EAST);
        
        // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel(new GridLayout(2,listesPion.size(),0,10));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        for(Pion p : listesPion){
            panelCentre.add(new PanelImage(p.getJoueur() , 0));
        }
        for(String n : listesNom){
            JLabel l = new JLabel(n);
            l.setHorizontalAlignment(SwingConstants.CENTER);
            panelCentre.add(l);
            
        }
        
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        
        
        
               
    }
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
}