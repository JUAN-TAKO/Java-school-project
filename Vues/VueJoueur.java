package Vues;

import Aventuriers.Explorateur;
import Cartes.CartesTirage;
import Modele.Aventurier;
import Modele.Tuile;
import static Utils.Etat.*;
import static Utils.TypeTuile.*;
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
import java.util.HashSet;
import javax.swing.*;

public class VueJoueur extends JPanel{
    private JPanel panelHaut;
    private JPanel panelOuest;
    private JPanel panelEst;
    private JPanel panelCentre;
    private JPanel panelBas;
    
           
    private HashSet<JPanel> imagePanels;
   

    
    
    public VueJoueur(Aventurier joueur, String imageJoueur){
        
        super(new BorderLayout());
        // =================================================================================
        // NORD
        panelHaut = new JPanel() ;
        add(panelHaut, BorderLayout.NORTH);
        
        // =================================================================================
        // OUEST 
        panelOuest = new JPanel(new GridLayout(2,1));
        add(panelOuest, BorderLayout.WEST);
        panelOuest.add(new PanelImage(imageJoueur));
        panelOuest.add(new JLabel("Joueur 1"));
        
        // =================================================================================
        // EST
        panelEst = new JPanel();
        add(panelEst, BorderLayout.EAST);       
        

        // =================================================================================
        // CENTRE
        panelCentre = new JPanel(new GridLayout(1,7));
        add(panelCentre, BorderLayout.CENTER);
        
        imagePanels = new HashSet<>();
        
        for(int i=0 ; i<5 ; i++){
            ajouterCarte("src/Images/cartes/Calice.png", i);
        }
       
        ajouterCarte("src/Images/tresors/SacsDeSable.png", 5);
        ajouterCarte("src/Images/Helicoptere.png", 6);
  
        update();
                
        // =================================================================================
        // SUD
        panelBas = new JPanel() ;
        add(panelBas, BorderLayout.SOUTH);
                     
    }
    
    public void ajouterCarte(String chemin, int index){
        PanelImage p = new PanelImage(chemin);        
        imagePanels.add(p);
        addListener(p,index);
        update();
    }
    
    public void retirerCarte(JPanel p){
       imagePanels.remove(p);
       update();
    }
    
    public void afficherCartes(ArrayList<PanelImage> listes){
        for(int i=0 ; i<5 ; i++){           
            panelCentre.add(listes.get(i));
        }      
        update();
    }
    

    
    public void update(){
        remove(panelCentre);
        panelCentre = new JPanel(new GridLayout(1,7));
        for(JPanel p : imagePanels){               
            panelCentre.add(p);
        }
        add(panelCentre);
        
        panelCentre.revalidate();
        panelCentre.repaint();
    }
    
    public void addListener(JPanel p, int index){
        p.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("coucou");
                    retirerCarte(p);
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
         this.setVisible(true);
     }
     
//     public void hide() {
//         this.dispose();
//     }

    
    public static void main(String [] args) {
        
        JFrame  window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Joueur");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
            
        
        Explorateur joueur = new Explorateur("tibo");
        VueJoueur vueJoueur = new VueJoueur(joueur, "src/Images/personnages/explorateur.png");
        //vueJoueur.afficher();
        window.add(vueJoueur);
        window.setVisible(true);
   }
    
    
}