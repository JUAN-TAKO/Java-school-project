package Vues;

import Cartes.CartesTirage;
import Modele.Aventurier;
import Vues.PanelImage;
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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class VueJoueur{
    private final JFrame window ;
    private JPanel panelHaut;
    private JPanel panelOuest;
    private JPanel panelEst;
    private JPanel panelCentre;
    private JPanel panelBas;
    
    private JPanel imagePanel;
    private int indexSelect;    
    private ArrayList<JPanel> cartesJoueur;
    
    
    public VueJoueur(String imageJoueur){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Joueur");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        // =================================================================================
        // OUEST 
        panelOuest = new JPanel(new GridLayout(2,1));
        mainPanel.add(panelOuest, BorderLayout.WEST);
        panelOuest.add(new PanelImage(imageJoueur));
        panelOuest.add(new JLabel("Joueur 1"));
        
        // =================================================================================
        // EST
        panelEst = new JPanel();
        mainPanel.add(panelEst, BorderLayout.EAST);       
        

        // =================================================================================
        // CENTRE
        panelCentre = new JPanel(new GridLayout(1,7));
        mainPanel.add(panelCentre, BorderLayout.CENTER);

        cartesJoueur = new ArrayList<>();
        
        for(int i=0 ; i<5 ; i++){
            imagePanel = new PanelImage("src/Images/cartes/Calice.png");
            panelCentre.add(imagePanel);
            addListener(imagePanel, i);
            cartesJoueur.add(imagePanel);
            update();
        }
        
        ajouterCarte("src/Images/tresors/SacsDeSable.png", 5);
        ajouterCarte("src/Images/Helicoptere.png", 6);
        
                
        // =================================================================================
        // SUD
        panelBas = new JPanel() ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
                     
    }
    
    public void ajouterCarte(String chemin, int index){
        imagePanel = new PanelImage(chemin);
        panelCentre.add(imagePanel);
        addListener(imagePanel, index);
        cartesJoueur.add(imagePanel);
        update();
    }
    
    public void retirerCarte(String chemin, int index){
        panelCentre.remove(new PanelImage(chemin));
        cartesJoueur.remove(imagePanel);
        imagePanel.remove(index);        
        update();
    }
    
    public void update(){
        imagePanel.revalidate();
        imagePanel.repaint();
    }
    
    public void addListener(JPanel p, int index){
        p.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("coucou");
                    retirerCarte(p.toString(), index);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                     
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
                 
            });
    }

    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    public static void main(String [] args) {
        VueJoueur joueur = new VueJoueur("src/Images/personnages/explorateur.png");
        joueur.afficher();
   }
    
    
}