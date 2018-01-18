package Vues;

import Utils.Message;
import Utils.MessageType;
import static Utils.MessageType.*;
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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
//import Utils.*;

public class VueDebut extends Observable{
    private final JFrame window ;
    
    
    private JButton boutonJouer;
    private JButton boutonQuitter;
    
    
    public VueDebut(){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        mainPanel.add(new PanelAvecImage(700,450 ,"src/Images/imageDebut2.jpg"));
        
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,9)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonQuitter = new JButton("Quitter");
        boutonQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.QUITTER));
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
                notifyObservers(new Message(MessageType.JOUER));
                clearChanged();
            }
        });
        
        panelBas.add(boutonJouer);
       
        
        
               
    }
    
    private class PanelAvecImage extends JPanel {

        private Image image;
        private final Integer width ;
        private final Integer height ;

        public PanelAvecImage(Integer width, Integer height, String imageFile) {
            this.width = width ;
            this.height = height ;
            try {
                this.image = ImageIO.read(new File(imageFile));
            } catch (IOException ex) {
                System.err.println("Erreur de lecture background");
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this.width, this.height, null, this);
            }
        }
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