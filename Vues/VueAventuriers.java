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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
 
public class VueAventuriers extends Observable{
     
    private final JPanel panelBoutons ;
    private final JPanel panelAventuriers;
    private final JFrame window;
    private final JPanel mainPanel;
    private final JButton btnBouger  ;
    private final JButton btnAssecher;
    private final JButton btnSpecial;
    private final JButton btnPasser;
    private ArrayList<JTextField> positions;
    private ArrayList<Color> couleurs;
    private ArrayList<JLabel> labelsRoleAventurier;
   
    
    public VueAventuriers(ArrayList<String> noms, ArrayList<String> roles, ArrayList<Color> c) {
        couleurs = c;
        this.window = new JFrame();
        window.setSize(300*noms.size(), 300);
        //le titre = nom du joueur 
        window.setTitle("Ile Interdite");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion
        
        labelsRoleAventurier = new ArrayList<>();
        panelAventuriers = new JPanel(new GridLayout(1, noms.size()));
        panelAventuriers.setBorder(new MatteBorder(0, 0, 15, 0, Color.LIGHT_GRAY));
        mainPanel.add(panelAventuriers, BorderLayout.NORTH);
        
        positions = new ArrayList<JTextField>(); 
        
        
        for(int i = 0; i < noms.size(); i++){
            JPanel a = new JPanel(new GridLayout(4, 1));
            JLabel b = new JLabel(roles.get(i),SwingConstants.CENTER );
            b.setOpaque(true);
            JTextField p = new JTextField(30);
            p.setHorizontalAlignment(CENTER);
            p.setEditable(false);
            positions.add(p);
            a.setBorder(new MatteBorder(0, 3, 0, 3, Color.LIGHT_GRAY));
            
            
            a.add(new JLabel(noms.get(i),SwingConstants.CENTER ));
            a.add(b);
            a.add(new JLabel ("Position actuelle", SwingConstants.CENTER));
            a.add(p);
            
            labelsRoleAventurier.add(b);
            panelAventuriers.add(a);
        }
        
        panelAventuriers.setOpaque(false);
        
        
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        
        mainPanel.add(this.panelAventuriers, BorderLayout.CENTER);

        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        panelBoutons.setBorder(new MatteBorder(0, 50, 0, 50, Color.LIGHT_GRAY));
        this.panelBoutons.setOpaque(false);
        Dimension d = new Dimension(-1, 75);
        panelBoutons.setPreferredSize(d);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Se deplacer") ;
        btnBouger.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        this.btnAssecher = new JButton( "Assecher");
        btnAssecher.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        this.btnSpecial = new JButton("Speciale") ;
        btnSpecial.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        this.btnPasser = new JButton("Passer Tour") ;
        btnPasser.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        
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
    
    public void afficher() {
        this.window.setVisible(true);
    }

    public void setActive(int index){
        for(int i = 0; i < labelsRoleAventurier.size(); i++){
            labelsRoleAventurier.get(i).setBackground(Color.GRAY);
        }
        mainPanel.setBorder(BorderFactory.createLineBorder(couleurs.get(index), 2)) ;
        labelsRoleAventurier.get(index).setBackground(couleurs.get(index));
    }

    public void setPosition(int index, String pos) {
        positions.get(index).setText(pos);
    }

    public void setBoutonsActives(boolean a) {
        btnBouger.setEnabled(a);
        btnSpecial.setEnabled(a);
    }
}