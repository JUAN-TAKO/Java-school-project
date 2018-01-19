package Vues;

import Utils.Message;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class VueConfirm extends Observable{
    private final JFrame window ;
    
    private JButton boutonOui;
    private JButton boutonNon;
    
    
    public VueConfirm(){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.setSize(400, 100);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Confirmation");
        
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.NON));
                clearChanged();
            }
        });
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(new JLabel("ETES-VOUS SUR DE VOULOIR QUITTER ?")) ;
        
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
        JPanel panelCentre = new JPanel(new GridLayout(5,1,0,10));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonNon = new JButton("Non");
        boutonNon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.NON));
                clearChanged();
            }
        });
        
        panelBas.add(boutonNon);
        
        panelBas.add(new JLabel()) ; // Label du milieu vide        
        
        boutonOui = new JButton("Oui");
        boutonOui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.OUI));
                clearChanged();
            }
        });
        
        panelBas.add(boutonOui);  
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    } 
}