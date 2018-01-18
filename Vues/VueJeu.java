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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VueJeu extends Observable{
    private final JFrame window ;
    
    Grille g = new Grille(new GrilleAleatoire());
    private JPanel mainPanel;
    private JPanel panelNord;
    private JPanel panelSud;
    private JPanel panelEst;
    private JPanel panelOuest;
    
    private PanelImage defausseInondation;
    private PanelImage defausseTirage;
    
    private JLabel labelActionsRestantes;
    private JPanel panelCentreSud;
    
    private PanelGrille grille;
    private PanelNiveau niveau;
    private int niveauInitial;
    private PanelTresor panelTresor;
    
    private PanelJoueur[] panelsJoueurs = new PanelJoueur[4];
    private CompositionObservable observable;
    
    private JButton boutonAsseccher;
    private JButton boutonDeplacer;
    private JButton boutonActionSpeciale;
    private JButton boutonCarteSpeciale;
    
    private JMenuBar mb = new JMenuBar();
    private JMenu menu = new JMenu();
    private JMenuItem quitter = new JMenuItem();
    private JMenuItem regles = new JMenuItem();
    private class Tailles{
        public int windowH;
        public int windowW;
        public int panelsJoueursH;
        public int panelsDefaussesW;
        public int grilleS;
        public void recalculer(){
            
        }
    }
    
    
    public VueJeu(ArrayList<String> noms, ArrayList<Pion> pions){
   
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(new Dimension(d.width, d.height - 50));
        window.setTitle("Plateau de Jeu");
        window.setResizable(false);
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        observable = new CompositionObservable();
        
        //Creation d'une barre de menu  et ajout d'un menu déroulant dans la barre de menu
        window.setJMenuBar(mb);
        menu.setText("Menu");
        
        //déclaration des différents choix du menu
        quitter.setText("Quitter");
        regles.setText("Règles du jeu");
        
        //ajout des choix au menu
        menu.add(regles);
        menu.add(quitter);
        mb.add(menu);
        
        //On crée les panels joueurs
        for(int i = 0; i < 4; i++){
            if(i < noms.size()){
                panelsJoueurs[i] = new PanelJoueur(i, noms.get(i), pions.get(i), observable);
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
        
        //JPanel panelCentre = new JPanel(new GridBagLayout());
        grille = new PanelGrille(types, observable);
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
        panelCentreSud = new JPanel(new GridLayout(2,2));
        panelSud.add(panelCentreSud, BorderLayout.CENTER);
        
        panelSud.add(panelsJoueurs[3], BorderLayout.EAST);
        defausseInondation = new PanelImage("src/Images/cache/inondation_cache.png", 2, observable, MessageType.VOIR_INONDATION);
               
        niveau = new PanelNiveau(1);
        
        boutonAsseccher = new JButton("Assecher");
        boutonAsseccher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.ASSECHER);
                clearChanged();
            }
        });
        
        boutonDeplacer = new JButton("Deplacer");
        boutonDeplacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.DEPLACER);
                clearChanged();
            }
        });
        
        boutonActionSpeciale = new JButton("Action Speciale");
        boutonActionSpeciale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.ACTION_SPECIAL);
                clearChanged();
            }
        });
        
        boutonCarteSpeciale = new JButton("Carte Speciale");
        boutonCarteSpeciale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.CARTE_SPECIAL);
                clearChanged();
            }
        });
        
        panelCentreSud.add(boutonAsseccher);
        panelCentreSud.add(boutonDeplacer);
        panelCentreSud.add(boutonActionSpeciale);
        panelCentreSud.add(boutonCarteSpeciale);
        
        // =================================================================================
        // EST
        defausseInondation = new PanelImage("src/Images/cache/inondation_cache.png", 2);

        panelEst = new JPanel(new BorderLayout());
        panelEst.add(niveau, BorderLayout.CENTER);
        panelEst.add(defausseInondation, BorderLayout.SOUTH);
        mainPanel.add(panelEst, BorderLayout.EAST);
        
        
        // =================================================================================
        // OUEST
        panelTresor = new PanelTresor();
        defausseTirage = new PanelImage("src/Images/cache/tresor_cache.png", 2, observable, MessageType.VOIR_TIRAGE);
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
    public void setObserver(Observer observer){
        observable.addObserver(observer);
    }
    public void resize(){
        int w = mainPanel.getWidth();
        if(w != 0){
            int w2 = (int)((float)w * 0.35f);

            for(int i = 0; i < 4; i++){
                panelsJoueurs[i].setWidth(w2);
            }
            
            int h = panelsJoueurs[0].getH() + 6;
            panelNord.setPreferredSize(new Dimension(w, h));
            panelSud.setPreferredSize(new Dimension(w, h));
        }
        int wi = defausseInondation.getWidth();
        panelOuest.setPreferredSize(new Dimension(wi, 10));
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
    
    public void setNiveau(int n){
        niveau.setNiveau(n);
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
