package Vues;

import Aventuriers.Explorateur;
import Cartes.*;
import Modele.Aventurier;
import Modele.Tuile;
import Utils.Pion;
import Utils.*;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class PanelJoueur extends JPanel{

    private ArrayList<PanelImage> listePanelsCartes;
    private JPanel panelJoueur;
    public PanelJoueur(int coin, String nomJoueur, Pion pionJoueur){
        
        super(new GridLayout(1, 8));
        
        panelJoueur = new JPanel(new BorderLayout());
        
        //condition ternaire. la syntaxe est : "variable = condition ? retourQuandVrai : retourQuandFaux;"
        int pos = coin >= 2 ? SwingConstants.NORTH : SwingConstants.SOUTH; // si coin >= 2 (en bas), pos = NORTH; sinon pos = SOUTH
        
        panelJoueur.add(new JLabel(nomJoueur), pos);
        panelJoueur.add(new PanelImage(pionJoueur.getJoueur()), SwingConstants.CENTER);
        listePanelsCartes = new ArrayList<>();
        
  
        update();
                     
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
                    p.setBackground(Color.DARK_GRAY);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    p.setBackground(null);
                }
                 
            });
    }
    
    public void afficher() {
         this.setVisible(true);
    }
     
   

    
    public static void main(String [] args) {
        JFrame  window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Joueur");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
            
        
        Explorateur joueur = new Explorateur("tibo");
        PanelJoueur vueJoueur = new PanelJoueur(joueur, "src/Images/personnages/explorateur.png");
        window.add(vueJoueur);
        window.setVisible(true);
   }
    
    
}