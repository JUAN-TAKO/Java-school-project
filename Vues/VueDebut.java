package Vues;

import Utils.MessageType;
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
//import Utils.*;

public class VueDebut extends Observable{
    private final JFrame window ;
    
    
    private JButton boutonJouer;
    private JButton boutonQuitter;
    
    
    public VueDebut(){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        mainPanel.add(new PanelImage("src/Images/imageDebut2.jpg", 0));
        
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,9)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonQuitter = new JButton("Quitter");
        boutonQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.QUITTER);
                clearChanged();
            }
        });
        
        panelBas.add(boutonQuitter);
        
        panelBas.add(new JLabel()) ; // Label du milieu vide
        
        boutonJouer = new JButton("Jouer");
        boutonJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.JOUER);
                clearChanged();
            }
        });
        
        panelBas.add(boutonJouer);
       
        
        
               
    }
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    public void desactive(){
        boutonJouer.setEnabled(false);
        boutonQuitter.setEnabled(false);
    }
    
    public void active(){
        boutonJouer.setEnabled(true);
        boutonQuitter.setEnabled(true);
    }
    
    public static void main(String [] args) {
        VueDebut vueDebut = new VueDebut();
        vueDebut.afficher();
   }
    
    
}