/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Delphine Clary
 */
public class VueDefaite{
    
    private JFrame window;
    
    private JPanel main;
    
    public VueDefaite(){
        
        window = new JFrame();
        window.setSize(500,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        
        main = new JPanel(new BorderLayout());
        window.add(main);
        
        
    }
    
    
    
}
