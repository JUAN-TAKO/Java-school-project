package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
 
public class VueAventurier extends Observable{
     
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnBouger  ;
    private final JButton btnAssecher;
    private final JButton btnSpecial;
    private final JButton btnPasser;
    private JTextField position;
    private Color couleur;
   
   
    
    public VueAventurier(String nomJoueur, String nomAventurier, Color c) {

        couleur = c;
        this.window = new JFrame();
        window.setSize(350, 200);
        //le titre = nom du joueur 
        window.setTitle(nomJoueur);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel ("Position actuelle", SwingConstants.CENTER));
        position = new  JTextField(30); 
        position.setHorizontalAlignment(CENTER);
        position.setEditable(false);
        panelCentre.add(position);


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Se deplacer") ;
        this.btnAssecher = new JButton( "Assecher");
        this.btnSpecial = new JButton("Speciale") ;
        this.btnPasser = new JButton("Passer Tour") ;
        
        
        this.panelBoutons.add(btnBouger);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnSpecial);
        this.panelBoutons.add(btnPasser);

        btnBouger.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.DEPLACER));
                clearChanged();
            }
        
        });
        btnAssecher.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.ASSECHER));
                clearChanged();
            }
        
        });
        btnSpecial.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.SPECIAL));
                clearChanged();
            }
        
        });
        btnPasser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.PASSER));
                clearChanged();
            }
        
        });
    } 
    
    public void setPosition(String pos) {
        this.position.setText(pos);
    }
    public void setActive(boolean a){
        if(a){
            mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
            this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
            panelAventurier.setBackground(couleur);    
        }
        else{
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)) ;
            this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY));
            panelAventurier.setBackground(Color.GRAY);
        }
        for(int i = 0; i < panelBoutons.getComponentCount(); i++){
            panelBoutons.getComponent(i).setEnabled(a);
        }
        
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
}