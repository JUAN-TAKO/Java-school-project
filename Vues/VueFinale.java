package Vues;

import Utils.Tresor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author damien
 */
public class VueFinale extends Observable{
    private final JFrame window;
    
    private JPanel panelNord;
    private JPanel panelEst;
    private JPanel panelOuest;
    private JPanel panelSud;
    private JPanel panelCentre;
    
    
    
    private JButton boutonQuitter;
    private JButton boutonMenu;
    
    private JLabel resultat;
    private JLabel tresorGagne;
    private int somme = 0;
    
    public VueFinale(ArrayList<Boolean> b){
        Font f = new Font("arial", 0, 30);
        Font fBouton = new Font("arial", 0, 20);
        Font fTresorGagne = new Font("arial", 0, 15);
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("ILE INTERDITE - VICTOIRE");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
       
        
        // =================================================================================
        // NORD
        Boolean gagne = true;
        panelNord = new JPanel(new GridLayout(2,1)) ;
        mainPanel.add(panelNord, BorderLayout.NORTH);
        
        // =================================================================================
        // CENTRE
        panelCentre = new JPanel(new GridLayout(1,4,2,2));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        for (int i = 0; i < 4; i++) {
            Boolean bool = b.get(i);
            gagne &= bool;
            PanelImage panelTresor;
            if (bool) {
                panelTresor = new PanelImage(Tresor.values()[i].getTresor());
                somme++;
            }else{
                panelTresor = new PanelImage(Tresor.values()[i].getTresorGris());
            }
            panelCentre.add(panelTresor);
        }
        
        if (gagne) {
            resultat = new JLabel("VOUS AVEZ GAGNÉ!");
            resultat.setForeground(Color.blue);
            
        }else{
            resultat = new JLabel("VOUS AVEZ PERDU!");
            resultat.setForeground(Color.red);
        }
        
        tresorGagne = new JLabel("Vous avez récupéré " + somme + " trésors");
        resultat.setFont(f);
        tresorGagne.setFont(fTresorGagne);
        panelNord.add(resultat, resultat.CENTER);
        panelNord.add(tresorGagne, tresorGagne.CENTER);
        panelNord.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // =================================================================================
        // OUEST 
        panelOuest = new JPanel();
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
        // =================================================================================
        // EST
        panelEst = new JPanel();
        mainPanel.add(panelEst, BorderLayout.EAST);
        
        
        // =================================================================================
        // SUD
        panelSud = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelSud, BorderLayout.SOUTH);
        boutonQuitter = new JButton("Quitter");
        boutonQuitter.setFont(fBouton);
        
        panelSud.add(boutonQuitter);
        
        panelSud.add(new JPanel());
        
        boutonMenu = new JButton("Retour Menu");
        boutonMenu.setFont(fBouton);
        panelSud.add(boutonMenu);
        
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
     
    public void hide() {
        window.dispose();
    }
     
    public static void main(String[] args) { 
        ArrayList<Boolean> b = new ArrayList<>();
        b.add(false);
        b.add(true);
        b.add(true);
        b.add(false);
        VueFinale vueVic = new VueFinale(b);
        vueVic.afficher();
    }
}
