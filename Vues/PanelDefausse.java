/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author damien
 */
public class PanelDefausse extends JPanel{
    
    private PanelImage panelCentre;
    private String path;
    
    public PanelDefausse(String s){
        super(new BorderLayout());
        path = s;
        
        panelCentre = new PanelImage(getImagePath(), 0);
        add(panelCentre, BorderLayout.CENTER);
    }
    
    public String getImagePath(){
        return path + "_cache.png";
    }
    
    public static void main(String [] args) {
        JFrame  window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Defausse");
        
        PanelDefausse vueDefausse = new PanelDefausse("src/Images/cache/inondation");
        
        window.add(vueDefausse);
       
        window.setVisible(true);
   }
    
}
