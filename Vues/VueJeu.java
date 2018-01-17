package Vues;

import Generateurs.GrilleAleatoire;
import Modele.Grille;
import Modele.Tuile;
import Utils.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
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
    private JPanel panelNord;
    private JPanel panelSud;
    private JPanel panelEst;
    private JPanel panelOuest;
    
    PanelImage defausseInondation;
    PanelImage defausseTirage;
    
    private JLabel labelActionsRestantes;
    private JPanel panelCentreSud;
    
    private PanelGrille grille;
    private PanelNiveau niveau;
    private PanelTresor panelTresor;
    
    private PanelJoueur[] panelsJoueurs = new PanelJoueur[4];
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
        panelsJoueurs[1].updateCartes(cartes);
        panelsJoueurs[2].updateCartes(cartes);
        panelsJoueurs[3].updateCartes(cartes);
        // =================================================================================
        // NORD
        panelNord = new JPanel(new BorderLayout());
        mainPanel.add(panelNord, BorderLayout.NORTH);
        
        panelNord.add(panelsJoueurs[0], BorderLayout.WEST);
        panelNord.add(panelsJoueurs[1], BorderLayout.EAST);
        
        JPanel panelCentreNord = new JPanel(new GridLayout(3,1));
        panelNord.add(panelCentreNord, BorderLayout.CENTER);
        
        labelActionsRestantes = new JLabel("", JLabel.CENTER);
        panelCentreNord.add(new JLabel("Actions Restantes", JLabel.CENTER));
        panelCentreNord.add(labelActionsRestantes);
        panelCentreNord.add(new JButton("Passer tour"));
        
        setNbAction("2");
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
        
        JPanel panelCentre = new JPanel(new GridBagLayout());
        grille = new PanelGrille(types);
        mainPanel.add(grille, BorderLayout.CENTER);
        grille.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        grille.setEtat(2, Etat.INONDEE);
        grille.setEtat(7, Etat.INONDEE);
        grille.setEtat(17, Etat.INONDEE);
        grille.setEtat(26, Etat.INONDEE);
        
        // =================================================================================
        // SUD
        panelSud = new JPanel(new BorderLayout());
        mainPanel.add(panelSud, BorderLayout.SOUTH);
        panelSud.add(panelsJoueurs[2], BorderLayout.WEST);
        panelCentreSud = new JPanel();
        panelSud.add(panelCentreSud);
        
        panelSud.add(panelsJoueurs[3], BorderLayout.EAST);
        niveau = new PanelNiveau(3, 1);
        defausseInondation = new PanelImage("src/Images/cache/inondation_cache.png", 2);
        panelEst = new JPanel(new BorderLayout());
        panelEst.add(niveau, BorderLayout.CENTER);
        panelEst.add(defausseInondation, BorderLayout.SOUTH);
        mainPanel.add(panelEst, BorderLayout.EAST);
        
        ArrayList<Boolean> tf = new ArrayList<>();
        tf.add(true);
        tf.add(true);
        tf.add(true);
        tf.add(true);
        
        panelTresor = new PanelTresor(tf);
        defausseTirage = new PanelImage("src/Images/cache/tresor_cache.png", 2);
        panelOuest = new JPanel(new BorderLayout());
        panelOuest.add(panelTresor, BorderLayout.CENTER);
        panelOuest.add(defausseTirage, BorderLayout.SOUTH);
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
        mainPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });
        
    }
    public void resize(){
        int w = mainPanel.getWidth();
        if(w != 0){
            int w2 = (int)((float)w * 0.30f);

            for(int i = 0; i < 4; i++){
                panelsJoueurs[i].setWidth(w2);
            }
            
            int h = panelsJoueurs[0].getH() + 6;
            panelNord.setPreferredSize(new Dimension(w, h));
            panelSud.setPreferredSize(new Dimension(w, h));
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    public void setNbAction(String s){
        labelActionsRestantes.setText(s);
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
