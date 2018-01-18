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

public class VueJeu{
    private final JFrame window ;
    
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
    
    private JButton boutonAssecher;
    private JButton boutonDeplacer;
    private JButton boutonActionSpeciale;
    private JButton boutonSacSable;
    private JButton boutonHelico;
    private JButton boutonRecuptresor;
    
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
    
    
    public VueJeu(ArrayList<String> noms, ArrayList<Pion> pions, ArrayList<TypeTuile> types){
    
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(new Dimension(d.width, d.height - 30));
        window.setTitle("Plateau de Jeu");
        window.setResizable(true);
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        observable = new CompositionObservable();
        
        //Creation d'une barre de menu  et ajout d'un menu déroulant dans la barre de menu
        window.setJMenuBar(mb);
        menu.setText("Menu");
        
        //déclaration des différents choix du menu
        quitter.setText("Quitter");
        regles.setText("Règles du jeu");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                System.out.println("coucou");
                observable.notifyObservers(new Message(MessageType.QUITTER));
                observable.clearChanged();
            }
        });
        
        regles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                System.out.println("damien");
                observable.notifyObservers(new Message(MessageType.REGLES));
                observable.clearChanged();
            }
        });
        
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
        
        
        // =================================================================================
        // CENTRE
        
        
        //JPanel panelCentre = new JPanel(new GridBagLayout());
        grille = new PanelGrille(types, observable);
        mainPanel.add(grille, BorderLayout.CENTER);
        grille.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // =================================================================================
        // SUD
        panelSud = new JPanel(new BorderLayout());
        mainPanel.add(panelSud, BorderLayout.SOUTH);
        panelSud.add(panelsJoueurs[2], BorderLayout.WEST);
        panelCentreSud = new JPanel(new GridLayout(3,3));
        panelSud.add(panelCentreSud, BorderLayout.CENTER);
        
        panelSud.add(panelsJoueurs[3], BorderLayout.EAST);
        defausseInondation = new PanelImage("src/Images/cache/inondation_cache.png", 2, observable, MessageType.VOIR_INONDATION);
               
        niveau = new PanelNiveau(1);
        
        boutonAssecher = new JButton("Assecher");
        boutonAssecher.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                observable.notifyObservers(new Message(MessageType.ASSECHER));
                observable.clearChanged();
            }
        });
        
            
        
        boutonDeplacer = new JButton("Deplacer");
        boutonDeplacer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                observable.notifyObservers(new Message(MessageType.DEPLACER));
                observable.clearChanged();
            }
        });
        
        boutonActionSpeciale = new JButton("Action Speciale");
        boutonActionSpeciale.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                observable.notifyObservers(new Message(MessageType.ACTION_SPECIALE));
                observable.clearChanged();
            }
        });
        
        boutonSacSable = new JButton("Sac de Sable");
        boutonSacSable.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                observable.notifyObservers(new Message(MessageType.HELICO));
                observable.clearChanged();
            }
        });
        
        boutonHelico = new JButton("Hélicoptère");
        boutonHelico.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                observable.notifyObservers(new Message(MessageType.REGLES));
                observable.clearChanged();
            }
        });
        
        boutonRecuptresor = new JButton("Récupérer Trésor");
        boutonRecuptresor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setChanged();
                observable.notifyObservers(new Message(MessageType.REGLES));
                observable.clearChanged();
            }
        });
        
        panelCentreSud.add(boutonAssecher);
        panelCentreSud.add(boutonDeplacer);
        panelCentreSud.add(boutonActionSpeciale);
        panelCentreSud.add(boutonSacSable);
        panelCentreSud.add(boutonHelico);
        panelCentreSud.add(boutonRecuptresor);
        
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
        mainPanel.revalidate();
        mainPanel.repaint();
        
    }
    public void setEtatTuile(int index, Etat etat){
        grille.setEtat(index, etat);
        resize();
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
    
    public void visible(boolean b){
        if(b){
            this.window.setVisible(false);
        }else{
            this.window.setVisible(true);
        }
    }
    
    public void setNbAction(String s){
        labelActionsRestantes.setText(s);
        labelActionsRestantes.revalidate();
        labelActionsRestantes.repaint();
    }
    
    public void setNiveau(int n){
        niveau.setNiveau(n);
    }
    public void setAventurier(int index, ArrayList<Pion> pions){
        grille.setPions(index, pions);
    }
    
    public void choisirEtatsBoutons(ArrayList<Boolean> listes){
        for(int i = 0 ; i < listes.size() ; i++){
            if(listes.get(i)){
                switch(i){
                    case 0 :
                        boutonAssecher.setEnabled(true);
                        break;
                    case 1 :
                        boutonDeplacer.setEnabled(true);
                        break;
                    case 2 :
                        boutonActionSpeciale.setEnabled(true);
                        break;
                    case 3 :
                        boutonSacSable.setEnabled(true);
                        break;
                    case 4 :
                        boutonHelico.setEnabled(true);
                        break;
                    case 5 :
                        boutonRecuptresor.setEnabled(true);
                        break;
                }
            }else{
                switch(i){
                    case 0 :
                        boutonAssecher.setEnabled(false);
                        break;
                    case 1 :
                        boutonDeplacer.setEnabled(false);
                        break;
                    case 2 :
                        boutonActionSpeciale.setEnabled(false);
                        break;
                    case 3 :
                        boutonSacSable.setEnabled(false);
                        break;
                    case 4 :
                        boutonHelico.setEnabled(false);
                        break;
                    case 5 :
                        boutonRecuptresor.setEnabled(false);
                        break;
                }
            }
        }
    }
    
    public void updateCartes(int index, ArrayList<CarteTirage> cartes){
        panelsJoueurs[index].updateCartes(cartes);
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
        //VueJeu jeu = new VueJeu(noms, pions);
        //jeu.afficher();
   }
}
