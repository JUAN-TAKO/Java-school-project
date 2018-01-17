package Vues;

import Generateurs.GrilleAleatoire;
import Modele.Grille;
import Modele.Tuile;
import Utils.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueJeu{
    private final JFrame window ;
    
    Grille g = new Grille(new GrilleAleatoire());
    private JPanel mainPanel;
    //Déclaration pour le panel Nord
    private JPanel panelNord;
    private PanelJoueur[] panelsJoueurs = new PanelJoueur[4];
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
    private JPanel panelVideCentre;
    
    
    //déclaration pour le panel Centre
    private PanelGrille panelCentre;
    
    public VueJeu(ArrayList<String> noms, ArrayList<Pion> pions){
        
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        window.setTitle("Plateau de Jeu");
        
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        //On crée les panels joueurs
        for(int i = 0; i < 4; i++){
            if(i < noms.size()){
                panelsJoueurs[i] = new PanelJoueur(i, noms.get(i), pions.get(i));
            }
            else{
                panelsJoueurs[i] = new PanelJoueur(i);
            }
        }
        ArrayList<CarteTirage> cartes = new ArrayList<>();
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CRISTAL);
        cartes.add(CarteTirage.SABLE);
        panelsJoueurs[0].updateCartes(cartes);
        
        // =================================================================================
        // NORD
        panelNord = new JPanel(new BorderLayout());
        mainPanel.add(panelNord, BorderLayout.NORTH);
        

        panelActionPasser = new JPanel(new GridLayout(2,1));
        panelNord.add(panelsJoueurs[0], BorderLayout.WEST);
        panelNord.add(panelActionPasser, BorderLayout.CENTER);
        panelNord.add(panelsJoueurs[1], BorderLayout.EAST);
        
        panelActionRestantes = new JPanel(new GridLayout(1,2));
        panelBoutonAction = new JPanel();
        boutonPasserTour = new JButton("Passer tour");
        panelActionPasser.add(panelActionRestantes);
        panelActionPasser.add(panelBoutonAction);
        
        panelBoutonAction.add(boutonPasserTour);
        
        labelActionText = new JLabel("Actions Restantes", JLabel.CENTER);
        labelNbAction = new JLabel("", JLabel.CENTER);
        panelActionRestantes.add(labelActionText);
        panelActionRestantes.add(labelNbAction);
        
        setNbAction("2");
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
        
        
        mainPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                //resize();
            }
        });
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
        panelVideCentre = new JPanel();
        panelSud.add(panelsJoueurs[2]);
        panelSud.add(panelVideCentre);
        panelSud.add(panelsJoueurs[3]);
        
    }
//    public void resize(){
//        int w = mainPanel.getWidth();
//        int w2 = (int)((float)w * 0.45f);
//        
//        for(int i = 0; i < 4; i++){
//            panelsJoueurs[i].setPreferredSize(new Dimension(w2, 10));
//        }
//        mainPanel.revalidate();
//        mainPanel.repaint();
//    }
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
        ArrayList<String> noms = new ArrayList<>();
        noms.add("Dédé");
        noms.add("Tibo");
        noms.add("Dami1");
        noms.add("Juan");
        ArrayList<Pion> pions = new ArrayList<>();
        pions.add(Pion.BLEU);
        pions.add(Pion.JAUNE);
        pions.add(Pion.ROUGE);
        pions.add(Pion.VERT);
        VueJeu jeu = new VueJeu(noms, pions);
        jeu.afficher();
   }
}
