package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueParametres{
    private final JFrame window ;
    private final JComboBox listeDeroulante;
    JPanel panelC1;
    JPanel panelC2;
    
    
    public VueParametres(){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("PARAMETRES");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(new JLabel("BIENVENUE AVENTURIERS")) ;
        
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
        JPanel panelCentre = new JPanel(new GridLayout(3,1));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        // selection du nombre de joueur
        panelCentre.add(panelC1 = new JPanel(new GridLayout(1,2)));
        panelC1.add(new JLabel("nombre de joueur : "));
        listeDeroulante = new JComboBox(nombreJoueur.values());
        panelC1.add(listeDeroulante);
        listeDeroulante.setSelectedIndex(0);
        
        // titre selection des pseudos
        panelCentre.add(new JLabel("SELECTION DES PSEUDOS", JLabel.CENTER));
        
        //Choix des pseudos
        panelCentre.add(panelC2 = new JPanel(new GridLayout(4,2)));
        for (int i = 1; i < listeDeroulante.getSelectedIndex() + 3; i++) {
            panelC2.add(new JLabel("JOUEUR " + i + " :", JLabel.CENTER));
            panelC2.add(new JTextField());
        }
//        panelC2.add(new JLabel("JOUEUR 1 :", JLabel.CENTER));
//        panelC2.add(new JTextField());
//        panelC2.add(new JLabel("JOUEUR 2 :", JLabel.CENTER));
//        panelC2.add(new JTextField());
//        panelC2.add(new JLabel("JOUEUR 3 :", JLabel.CENTER));
//        panelC2.add(new JTextField());
//        panelC2.add(new JLabel("JOUEUR 4 :", JLabel.CENTER));
//        panelC2.add(new JTextField());
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        panelBas.add(new JButton("Retour"));
        panelBas.add(new JLabel()) ;
        panelBas.add(new JButton("Valider"));
        
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public static void main(String [] args) {
        VueParametres parametres = new VueParametres();
        parametres.afficher();
   }
}