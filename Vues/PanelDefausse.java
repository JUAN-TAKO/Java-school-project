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
}
