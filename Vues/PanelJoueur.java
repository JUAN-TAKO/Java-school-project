package Vues;

import Utils.CarteTirage;
import Aventuriers.Explorateur;
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
    private JPanel mainPanel;
    private int coin;
    
    public PanelJoueur(int c, String nomJoueur, Pion pionJoueur){
        super(new BorderLayout());
        coin = c;
        panelJoueur = new JPanel(new BorderLayout());
        //condition ternaire. la syntaxe est : "variable = condition ? retourQuandVrai : retourQuandFaux;"
        String pos = coin >= 2 ? BorderLayout.NORTH : BorderLayout.SOUTH; // si coin >= 2 (en bas), pos = NORTH; sinon pos = SOUTH
        
        panelJoueur.add(new JLabel(nomJoueur, SwingConstants.CENTER), pos);
        PanelImage pi = new PanelImage(pionJoueur.getJoueur());
        panelJoueur.add(pi, BorderLayout.CENTER);
        listePanelsCartes = new ArrayList<>();
        updateCartes(null);
                     
    }
    
    public void updateCartes(ArrayList<CarteTirage> cartes){
        Boolean gauche = (coin % 2 == 0);
        int nbCartes;
        if(mainPanel != null)
            remove(mainPanel);
        mainPanel = new JPanel(new GridLayout(1, 8));
        if(cartes == null){
            nbCartes = 0;
        }
        else{
            nbCartes = cartes.size();
        }
        if(gauche){
            mainPanel.add(panelJoueur);
            for(int i = 0; i < 7; i++){
                if(i < nbCartes)
                    mainPanel.add(new PanelImage(cartes.get(i).getImage()));
                else{          
                    mainPanel.add(new PanelImage());       
                }
            }
        }
        else{
            for(int i = 7; i > 0; i--){
                if(i >= nbCartes)
                    mainPanel.add(new PanelImage());
                else
                    mainPanel.add(new PanelImage(cartes.get(i-1).getImage()));
            }
            mainPanel.add(panelJoueur);
        }
        add(mainPanel, BorderLayout.CENTER);
    }
    
    public static void main(String [] args) {
        JFrame  window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(900, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Joueur");    
        
        Explorateur joueur = new Explorateur("tibo");
        PanelJoueur vueJoueur = new PanelJoueur(0, joueur.getNom(), joueur.getPion());
        ArrayList<CarteTirage> cartes = new ArrayList<>();
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CRISTAL);
        cartes.add(CarteTirage.SABLE);
        window.add(vueJoueur);
        vueJoueur.updateCartes(cartes);
        window.setVisible(true);
   }
    
    
}