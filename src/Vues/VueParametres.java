package Vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Utils.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class VueParametres extends Observable{
    private final JFrame window ;
    private final JComboBox listeDeroulante;
    
    private JPanel panelNbJoueur;
    private JPanel panelNomJoueur;
    
    private JPanel panelDifficulte;
    private JPanel panelNovice;
    private JPanel panelNormal;
    private JPanel panelElite;
    private JPanel panelLegendaire;
    
    private JPanel fleche;
    
    private ArrayList<JLabel> labelFleches;
    
    private JButton boutonValider;
    private JButton boutonQuitter;
    
    private ImageIcon flecheRouge = new ImageIcon("src/Images/flecheRouge.png");
    
    
    public VueParametres(){
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 400);
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
        JPanel panelCentre = new JPanel(new GridLayout(7,1));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        // selection du nombre de joueur
        panelCentre.add(panelNbJoueur = new JPanel(new GridLayout(1,2)));
        panelNbJoueur.add(new JLabel("nombre de joueur : ", JLabel.RIGHT));
        listeDeroulante = new JComboBox();
        listeDeroulante.addItem(2);
        listeDeroulante.addItem(3);
        listeDeroulante.addItem(4);
        panelNbJoueur.add(listeDeroulante);
        listeDeroulante.setSelectedIndex(0);
        
        // titre selection des pseudos
        panelCentre.add(new JLabel("SELECTION DES PSEUDOS", JLabel.CENTER));
        
        //Choix des pseudos
        panelCentre.add(panelNomJoueur = new JPanel(new GridLayout(4,2)));
        
        for (int i = 1; i < 5; i++) {
            panelNomJoueur.add(new JLabel("NOM JOUEUR " + i + " :", JLabel.RIGHT));
            panelNomJoueur.add(new JTextField());
        }
        
        
        panelCentre.add(new JLabel("NIVEAU DE DIFFICULTE :", JLabel.CENTER));
        
        panelCentre.add(panelDifficulte = new JPanel(new GridLayout(1,4)));
        
        panelDifficulte.add(panelNovice = new JPanel());
        panelDifficulte.add(panelNormal = new JPanel());
        panelDifficulte.add(panelElite = new JPanel());
        panelDifficulte.add(panelLegendaire = new JPanel());
            
        addListener(panelNovice,0);
        addListener(panelNormal,1);
        addListener(panelElite,2);
        addListener(panelLegendaire,3);
        
        panelNovice.setBackground(Color.decode("#43A5D9"));
        panelNormal.setBackground(Color.decode("#028FD9"));
        panelElite.setBackground(Color.decode("#006DA6"));
        panelLegendaire.setBackground(Color.decode("#013B59"));
        
        panelCentre.add(fleche = new JPanel(new GridLayout(1,4)));
        
        labelFleches = new ArrayList<JLabel>();
        
        for(int i=0 ; i<4 ; i++){
            JLabel l = new JLabel();
            fleche.add(l);
            labelFleches.add(l);            
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
                notifyObservers(new Message(MessageType.QUITTER));
                clearChanged();
            }
        });
        
        panelBas.add(boutonQuitter);
        
        panelBas.add(new JLabel()) ; // Label du milieu vide
        
        boutonValider = new JButton("Valider");
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listeNoms = new ArrayList<>();
                for(int i = 1; i < (listeDeroulante.getSelectedIndex() + 2) * 2; i += 2){
                    listeNoms.add(((JTextField)panelNomJoueur.getComponent(i)).getText());
                }
                
                setChanged();
                notifyObservers(new MessageNoms(MessageType.VALIDER_PARAMETRES, listeNoms));
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
    private void updateNbr(){ //affiche le nombre de champs noms à remplir en fonction du nombre de joueurs sélectionnés
        for (int i = 0; i < 8; i+=2) {
            if(i < (listeDeroulante.getSelectedIndex() + 2) * 2){
                panelNomJoueur.getComponent(i).setVisible(true);
                panelNomJoueur.getComponent(i+1).setVisible(true);
            }
            else{
                panelNomJoueur.getComponent(i).setVisible(false);
                panelNomJoueur.getComponent(i+1).setVisible(false);
            }
            
        }
    }
    
    public void addImage(int index){        
        labelFleches.get(index).setIcon(flecheRouge);
    }
    
    public void addListener(JPanel p, int index){
         p.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                  setChanged();
                    MessageIndex m = new MessageIndex(MessageType.CHOISIR_DIFFICULTE, index);
                    notifyObservers(m);
                    clearChanged();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
                
            });
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    public static void main(String[] args) { 
        VueParametres vueParam = new VueParametres();
        vueParam.afficher();
    }
}