/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author damien
 */
public class VueFinale extends Observable{
    private final JFrame window;
    
    private JPanel panelNord;
    private JPanel panelEst;
    private JPanel panelOuest;
    private JPanel panelSud;
    private JPanel panelCentre;
    
    private JPanel panelTresor;
    
    private PanelImage panelCristal;
    private PanelImage panelPierre;
    private PanelImage panelCalice;
    private PanelImage panelZephyr;
    
    private JButton boutonQuitter;
    private JButton boutonMenu;
    
    private JLabel resultat;
    
    public VueFinale(ArrayList<Boolean> b){
        Font f = new Font("arial", 0, 30);
        Font fBouton = new Font("arial", 0, 20);
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE - VICTOIRE");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        // =================================================================================
        // NORD
        Boolean gagne = true;
        panelNord = new JPanel() ;
        mainPanel.add(panelNord, BorderLayout.NORTH);
        for (Boolean bool : b) {
            gagne &= bool;
            if (bool) {
                
            }
        }
        
        if (gagne) {
            resultat = new JLabel("VOUS AVEZ GAGNÉ!");
        }else{
            resultat = new JLabel("VOUS AVEZ PERDU!");
        }
        
        resultat.setFont(f);
        resultat.setForeground(Color.green);
        panelNord.add(resultat);
        panelNord.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // =================================================================================
        // OUEST 
        panelOuest = new JPanel();
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
        // =================================================================================
        // EST
        panelEst = new JPanel();
        mainPanel.add(panelEst, BorderLayout.EAST);
        
        // =================================================================================
        // CENTRE
        panelCentre = new JPanel(new GridLayout(1,4,2,2));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        
        
//        for (int i = 0; i < 4; i++) {
//            panelTresor = new JPanel();
//            panelTresor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//            panelCentre.add(panelTresor);
//        }
        
        // =================================================================================
        // SUD
        panelSud = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelSud, BorderLayout.SOUTH);
        boutonQuitter = new JButton("Quitter");
        boutonQuitter.setFont(fBouton);
        
        panelSud.add(boutonQuitter);
        
        panelSud.add(new JPanel());
        
        boutonMenu = new JButton("Retour Menu");
        boutonMenu.setFont(fBouton);
        panelSud.add(boutonMenu);
        
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
     
    public void hide() {
        window.dispose();
    }
     
    public static void main(String[] args) { 
//        VueFinale vueVic = new VueFinale();
//        vueVic.afficher();
    }
}
