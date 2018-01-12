package Vues;

import Utils.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author damien
 */
public class VueSelection extends Observable{
    private JFrame window ;
    private JComboBox listeDeroulanteSelection;
    
    private JPanel panelCS1;
    
    private JButton boutonValider;
    private JButton boutonRetour;
    private ArrayList<TypeTuile> typeTuiles;
    private MessageType returnMessage;
    
    public VueSelection(ArrayList<TypeTuile> t, ArrayList<String> coords, MessageType returnMess){
        ArrayList<String> nomTuiles = new ArrayList<>();
        typeTuiles = t;
        returnMessage = returnMess;
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.setSize(400, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("SELECTION DE TUILE");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel) ;
        
        // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(new JLabel("Choisissez intelligemment")) ;
        
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
        for (int i = 0; i < typeTuiles.size(); i++) {
            nomTuiles.add(typeTuiles.get(i).toString() + coords.get(i));
        }
        
        JPanel panelCentre = new JPanel(new GridLayout(1,2));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(panelCS1 = new JPanel(new GridLayout(1,2)));
        panelCS1.add(new JLabel("Tuiles accessibles : ", JLabel.RIGHT));
        listeDeroulanteSelection = new JComboBox(nomTuiles.toArray());
        panelCS1.add(listeDeroulanteSelection);
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonRetour = new JButton("Annuler");
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(MessageType.ANNULER_SELECTION));
                clearChanged();
            }
        });
        
        panelBas.add(boutonRetour);
        panelBas.add(new JLabel()) ;
        boutonValider = new JButton("Valider");
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new MessageTuile(returnMessage, typeTuiles.get(listeDeroulanteSelection.getSelectedIndex())));
                clearChanged();
            }
        });
        
        panelBas.add(boutonValider);
        
        window.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            setChanged();
            notifyObservers(new Message(MessageType.ANNULER_SELECTION));
            clearChanged();
        }
    });
    }

    public void afficher(){
       this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
}
