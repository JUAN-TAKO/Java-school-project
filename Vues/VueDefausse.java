/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Utils.CarteTirage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author damien
 */
public class VueDefausse {
    private JFrame window;
    private JPanel panelCentre;
    private JPanel panelNord;
    private JLabel labelTitre;
    
    public VueDefausse(ArrayList<String> defausse){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        panelNord = new JPanel() ;
        mainPanel.add(panelNord, BorderLayout.NORTH);
        labelTitre = new JLabel("");
        panelNord.add(labelTitre) ;
        
        // =================================================================================
        // CENTRE
        panelCentre = new JPanel(new GridLayout(3,8));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        int i = 0;
        while(i < defausse.size()){
            panelCentre.add(new PanelImage(defausse.get(i),0));
            i++;
        }
        if (i < 24) {
            for (int j = i; j < 24; j++) {
                panelCentre.add(new JPanel());
            }
        }
    }
    
    public void setTitres(String s){
        window.setTitle(s);
        labelTitre.setText(s);
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
     
    public void hide() {
        window.dispose();
    }
    
    public static void main(String[] args) { 
        ArrayList<String> defausse = new ArrayList<>();
        
        for (int i = 0; i < 24; i++) {
            defausse.add("src/Images/cartes/Heliport.png");
        }
        
        
        VueDefausse vueDef = new VueDefausse(defausse);
        vueDef.setTitres("Defausse Tresor");
        vueDef.afficher();
    }
    
}
