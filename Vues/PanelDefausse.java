/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
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
        
    }
    
    public String getImagePath(){
        return path + "_cache.png";
    }
}
