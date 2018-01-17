package Vues;

import Generateurs.GrilleAleatoire;
import Modele.Grille;
import Modele.Tuile;
import Utils.Etat;
import Utils.TypeTuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueJeu{
    private final JFrame window ;
    
    Grille g = new Grille(new GrilleAleatoire());
    
    //Déclaration pour le panel Nord
    private JPanel panelNord;
    private JPanel panelJoueur1;
    private JPanel panelJoueur2;
    private JPanel panelActionPasser;
    
    private JPanel panelActionRestantes;
    private JPanel panelBoutonAction;
    private JButton boutonPasserTour;
    
    private JLabel labelActionText;
    private JLabel labelNbAction;
    
    //déclaration pour le panel Ouest
    private JPanel panelOuest;
    private JPanel panelVideNord;
    private JPanel panelVideSud;
    private JPanel panelVideEst;
    private JPanel panelVideOuest;
    
    private JPanel panelTresorRecup;
    
    //déclaration pour le panel Est
    private JPanel panelEst;
    private PanelNiveau panelNiveau;
    
    //déclaration pour le panel Sud
    private JPanel panelSud;
    private JPanel panelJoueur3;
    private JPanel panelJoueur4;
    private JPanel panelVideCentre;
    
    
    //déclaration pour le panel Centre
    private PanelGrille panelCentre;
    
    public VueJeu(){
        
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        window.setTitle("Plateau de Jeu");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        panelNord = new JPanel(new BorderLayout());
        mainPanel.add(panelNord, BorderLayout.NORTH);
        
        panelJoueur1 = new JPanel();
        panelActionPasser = new JPanel(new GridLayout(2,1));
        panelJoueur2 = new JPanel();
        panelNord.add(panelJoueur1, BorderLayout.WEST);
        panelNord.add(panelActionPasser, BorderLayout.CENTER);
        panelNord.add(panelJoueur2, BorderLayout.EAST);
        
        panelActionRestantes = new JPanel(new GridLayout(1,2));
        panelBoutonAction = new JPanel();
        boutonPasserTour = new JButton("Passer tour");
        panelActionPasser.add(panelActionRestantes);
        panelActionPasser.add(panelBoutonAction);
        
        panelBoutonAction.add(boutonPasserTour);
        
        labelActionText = new JLabel("Action(s) Restante(s)", JLabel.CENTER);
        labelNbAction = new JLabel();
        panelActionRestantes.add(labelActionText);
        panelActionRestantes.add(labelNbAction);
        
        // =================================================================================
        // OUEST 
        panelOuest = new JPanel(new BorderLayout());
        mainPanel.add(panelOuest, BorderLayout.WEST);
        panelVideEst = new JPanel();
        panelVideNord = new JPanel() ;
        panelVideSud = new JPanel();
        panelVideOuest = new JPanel();
        panelTresorRecup = new JPanel();
        panelOuest.add(panelVideEst, BorderLayout.EAST);
        panelOuest.add(panelVideOuest, BorderLayout.WEST);
        panelOuest.add(panelVideNord, BorderLayout.NORTH);
        panelOuest.add(panelVideSud, BorderLayout.SOUTH);
        panelOuest.add(panelTresorRecup, BorderLayout.CENTER);
        
        
        // =================================================================================
        // EST
        panelNiveau = new PanelNiveau(3, 1);
        mainPanel.add(panelNiveau, BorderLayout.EAST);
        

        // =================================================================================
        // CENTRE
        ArrayList<TypeTuile> types = new ArrayList<>();
        for(int i = 0; i < g.length(); i++){
            Tuile t = g.get(i);
            if(t == null){
                types.add(null);
            }else{
                types.add(t.getType());
            }
        }
        
        panelCentre = new PanelGrille(types);
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        panelCentre.setEtat(2, Etat.INONDEE);
        panelCentre.setEtat(7, Etat.INONDEE);
        panelCentre.setEtat(17, Etat.INONDEE);
        panelCentre.setEtat(26, Etat.INONDEE);
        
        // =================================================================================
        // SUD
        panelSud = new JPanel(new GridLayout(1,3));
        mainPanel.add(panelSud, BorderLayout.SOUTH);
        
        panelJoueur3 = new JPanel();
        panelJoueur4 = new JPanel();
        panelVideCentre = new JPanel();
        panelSud.add(panelJoueur3);
        panelSud.add(panelVideCentre);
        panelSud.add(panelJoueur4);
        
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    public void setNbAction(String s){
        labelNbAction.setText(s);
    }
    
    public static void main(String [] args) {
        VueJeu jeu = new VueJeu();
        jeu.afficher();
   }
}