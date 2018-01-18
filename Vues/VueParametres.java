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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class VueParametres extends Observable{
    private final JFrame window ;
    private final JComboBox listeDeroulante;
    private JPanel panelHaut;
    private JPanel panelEst;
    private JPanel panelOuest;
    private JPanel panelBas;
    private JPanel panelCentre;
    
    
    
    private JPanel panelNbJoueur;
    private JPanel panelNomJoueur;
    
    private JPanel panelDifficulte;
    private JPanel panelNovice;
    private JPanel panelNormal;
    private JPanel panelElite;
    private JPanel panelLegendaire;
    
    private JPanel panelFleche;
    
    private int indexSelect;
    private ArrayList<JPanel> listePanelsFleche;
    
    private JButton boutonValider;
    private JButton boutonQuitter;
    
    private ImageIcon flecheRouge = new ImageIcon("src/Images/flecheRouge.png");
    
    
    public VueParametres(){              
        Font fontText = new Font("arial", 0, 15);
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("PARAMETRES");

        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);

        
        
        // =================================================================================
        // NORD
        panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(new JLabel("BIENVENUE AVENTURIERS")) ;
        
        // =================================================================================
        // OUEST 
        panelOuest = new JPanel();
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
        // =================================================================================
        // EST
        panelEst = new JPanel();
        mainPanel.add(panelEst, BorderLayout.EAST);
        
        // =================================================================================
        // CENTRE
        panelCentre = new JPanel(new GridLayout(7,1));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        // selection du nombre de joueur
        panelCentre.add(panelNbJoueur = new JPanel(new GridLayout(1,2)));
        JLabel labelJoueur;
        panelNbJoueur.add(labelJoueur = new JLabel("nombre de joueur : ", JLabel.RIGHT));
        labelJoueur.setFont(fontText);
        
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
        JTextField t;
        JLabel labelNom;
        for (int i = 1; i < 5; i++) {
            panelNomJoueur.add(labelNom = new JLabel("Nom joueur " + i + " :", JLabel.RIGHT));
            panelNomJoueur.add(t = new JTextField());
            t.setFont(fontText);
            labelNom.setFont(fontText);
        }
        
        
        panelCentre.add(new JLabel("NIVEAU DE DIFFICULTE :", JLabel.CENTER));
        
        panelCentre.add(panelDifficulte = new JPanel(new GridLayout(1,4)));
        
        panelDifficulte.add(panelNovice = new JPanel());
        panelDifficulte.add(panelNormal = new JPanel());
        panelDifficulte.add(panelElite = new JPanel());
        panelDifficulte.add(panelLegendaire = new JPanel());
        
        for(int i = 0 ; i < 4 ; i++){
            addListener(returnPanel(i),i);
        }
        
        panelCentre.add(panelFleche = new JPanel(new GridLayout(1,4)));
        listePanelsFleche = new ArrayList<>();
        
        for(int i = 0 ; i < 4 ; i++){
            listePanelsFleche.add(new JPanel());
        }
        selectionner(0);
        
        
        // =================================================================================
        // SUD
        panelBas = new JPanel(new GridLayout(1,3)) ;
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
                for(int i = 1; i < (getListeDeroulante().getSelectedIndex() + 2) * 2; i += 2){
                    listeNoms.add(((JTextField)panelNomJoueur.getComponent(i)).getText());
                }
                
                setChanged();
                notifyObservers(new MessageParametre(MessageType.VALIDER_PARAMETRES, listeNoms, getIndexSelect()));
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
    
    
    
    
    public JPanel returnPanel(int i){
        JPanel temp = new JPanel();
        switch(i){
            case 0 :
                temp =  panelNovice;
                temp.setBackground(Color.decode("#43A5D9"));
                break;
            case 1 :
                temp = panelNormal;
                temp.setBackground(Color.decode("#028FD9"));
                break;
            case 2 :
                temp = panelElite;
                temp.setBackground(Color.decode("#006DA6"));
                break;
            case 3 :
                temp = panelLegendaire;
                temp.setBackground(Color.decode("#013B59"));
                break;
        }
        return temp;
    }
    private void updateNbr(){ //affiche le nombre de champs noms à remplir en fonction du nombre de joueurs sélectionnés
        for (int i = 0; i < 8; i+=2) {
            if(i < (getListeDeroulante().getSelectedIndex() + 2) * 2){
                panelNomJoueur.getComponent(i).setVisible(true);
                panelNomJoueur.getComponent(i+1).setVisible(true);
            }
            else{
                panelNomJoueur.getComponent(i).setVisible(false);
                 panelNomJoueur.getComponent(i+1).setVisible(false);
             }
             
         }
     }
     
    public void selectionner(int index){
        panelCentre.remove(panelFleche);
        panelCentre.add(panelFleche = new JPanel(new GridLayout(1,4)));       
        
        getListePanelsFleche().set(getIndexSelect(), new JPanel());
        getListePanelsFleche().set(index, new PanelImage("src/Images/flecheRouge.png",0));
        
        for(int i=0 ; i<4 ; i++){
            panelFleche.add(getListePanelsFleche().get(i));
        }
        panelFleche.revalidate();
        panelFleche.repaint();
        indexSelect = index;
    }
    
    
     
    public void addListener(JPanel p, int index){
         p.addMouseListener(new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    selectionner(index);
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
    
    /**
     * @return the listeDeroulante
     */
    public JComboBox getListeDeroulante() {
        return listeDeroulante;
    }

    /**
     * @return the indexSelect
     */
    public int getIndexSelect() {
        return indexSelect;
    }

    /**
     * @return the listePanelsFleche
     */
    public ArrayList<JPanel> getListePanelsFleche() {
        return listePanelsFleche;
    }
    
     
     public void afficher() {
         this.window.setVisible(true);
     }
     
     public void hide() {
         window.dispose();
     }
     
     public void desactive(){
        boutonValider.setEnabled(false);
        boutonQuitter.setEnabled(false);
    }
    
    public void active(){
        boutonValider.setEnabled(true);
        boutonQuitter.setEnabled(true);
    }
     
     public static void main(String[] args) { 
         VueParametres vueParam = new VueParametres();
         vueParam.afficher();
     }

    
} 