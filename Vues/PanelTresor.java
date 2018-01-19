/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Aventuriers.Explorateur;
import Utils.CarteTirage;
import Utils.Tresor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author damien
 */
public class PanelTresor extends JPanel{
    
    private JPanel mainPanel;
    private JPanel panelCentre;
    private ArrayList<Boolean> b;
    
    public PanelTresor(){
        super(new BorderLayout());
        b = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            b.add(false);
        }
        
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        panelCentre = new JPanel(new GridLayout(2,2,0,2));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        for (int i = 0; i < 4; i++) {
            Boolean bool = b.get(i);
            PanelImage panelTresor;
            if (bool) {
                panelTresor = new PanelImage(Tresor.values()[i].getTresor(), 0);
            }else{
                panelTresor = new PanelImage(Tresor.values()[i].getTresorGris(), 0);
            }
            panelCentre.add(panelTresor);
        }
        
    }
    
    
    public void setTresor(int index, Boolean bool){
        b.set(index, bool);
        
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        panelCentre = new JPanel(new GridLayout(2,2,0,2));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        for (int i = 0; i < 4; i++) {
            Boolean bo = b.get(i);
            PanelImage panelTresor;
            if (bo) {
                panelTresor = new PanelImage(Tresor.values()[i].getTresor(), 0);
            }else{
                panelTresor = new PanelImage(Tresor.values()[i].getTresorGris(), 0);
            }
            panelCentre.add(panelTresor);
        }
    }
}
