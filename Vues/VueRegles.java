/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Utils.Message;
import Utils.MessageType;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author denicolt
 */
public class VueRegles extends Observable{
    private final JFrame window ;
    
    private JPanel mainPanel;
    private JPanel panelCentre;
    
    private JButton bouton1;
    private JButton bouton2;
    private JButton bouton3;
    private JButton bouton4;
    private JButton bouton5;
    
    private JButton boutonRetour;
    
    
    public VueRegles(){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(540, 870);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Regles");
        
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(new JLabel("REGLES :")) ;
        
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
        panelCentre = new PanelImage("src/Images/regles/regle1.png",2);
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        
         
        // =================================================================================
        // SUD
        //les différents boutons servent à changer de "pages" qui correspondent à différents
        //panels qui permettent d'afficher toutes les règles.
        
        JPanel panelBas = new JPanel(new GridLayout(1,7,10,0)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        
        panelBas.add(new JLabel("Page : "));
        
        bouton1 = new JButton("1");
        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage(0);
            }
        });
        bouton2 = new JButton("2");
        bouton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage(1);
            }
        });
        
        bouton3 = new JButton("3");
        bouton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage(2);
            }
        });
        
        bouton4 = new JButton("4");
        bouton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage(3);
            }
        });
        
        bouton5 = new JButton("5");
        bouton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage(4);
            }
        });
        
        boutonRetour = new JButton("Fermer");
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
        
        
        panelBas.add(bouton1);
        panelBas.add(bouton2);
        panelBas.add(bouton3);
        panelBas.add(bouton4);
        panelBas.add(bouton5);
        panelBas.add(boutonRetour);
        
        


    }
    
    private void updateImage(int index){
        mainPanel.remove(panelCentre);
        switch(index){
            case 0 :
                panelCentre = new PanelImage("src/Images/regles/regle1.png", 2);
                break;
            case 1 :
                panelCentre = new PanelImage("src/Images/regles/regle2.png", 2);
                break;
            case 2 :
                panelCentre = new PanelImage("src/Images/regles/regle3.png", 2);
                break;
            case 3 :
                panelCentre = new PanelImage("src/Images/regles/regle4.png", 2);
                break;
            case 4 :
                panelCentre = new PanelImage("src/Images/regles/regle5.png", 2);
                break;
        }
        mainPanel.add(panelCentre);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    //méthode pour fermer la fenetre
    public void hide() {
        window.dispose();
    }
    
    //méthode pour afficher la fenetre
    public void afficher() {
        this.window.setVisible(true);
    }
}

