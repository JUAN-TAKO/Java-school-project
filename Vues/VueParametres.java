package Vues;

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
import Utils.*;

public class VueParametres extends Observable{
    private final JFrame window ;
    private final JComboBox listeDeroulante;
    
    private JPanel panelC1;
    private JPanel panelC2;
    
    private JButton boutonValider;
    private JButton boutonQuitter;
    
    
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
        panelC1.add(new JLabel("nombre de joueur : ", JLabel.RIGHT));
        listeDeroulante = new JComboBox(NombreJoueurs.values());
        panelC1.add(listeDeroulante);
        listeDeroulante.setSelectedIndex(0);
        
        // titre selection des pseudos
        panelCentre.add(new JLabel("SELECTION DES PSEUDOS", JLabel.CENTER));
        
        //Choix des pseudos
        panelCentre.add(panelC2 = new JPanel(new GridLayout(4,2)));
        
        for (int i = 1; i < 5; i++) {
            panelC2.add(new JLabel("JOUEUR " + i + " :", JLabel.RIGHT));
            panelC2.add(new JTextField());
        }
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonQuitter = new JButton("Quitter");
        boutonQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.ANNULER);
                clearChanged();
            }
        });
        
        panelBas.add(boutonQuitter);
        panelBas.add(new JLabel()) ;
        boutonValider = new JButton("Valider");
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.VALIDER);
                clearChanged();
            }
        });
        
        panelBas.add(boutonValider);
        
        listeDeroulante.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNbr();
            }
        });
        updateNbr();
    }
    private void updateNbr(){
        for (int i = 0; i < 8; i+=2) {
            if(i < (listeDeroulante.getSelectedIndex() + 2) * 2){
                panelC2.getComponent(i).setVisible(true);
                panelC2.getComponent(i+1).setVisible(true);
            }
            else{
                panelC2.getComponent(i).setVisible(false);
                panelC2.getComponent(i+1).setVisible(false);
            }
            
        }
    }
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    public static void main(String [] args) {
        VueParametres parametres = new VueParametres();
        parametres.afficher();
   }
}