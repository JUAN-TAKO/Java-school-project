package Vues;

import Utils.MessageType;
import Utils.TypeTuile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.ComboBoxModel;
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
    
    public VueSelection(ArrayList<TypeTuile> t){
        ArrayList<String> nomTuiles = new ArrayList<>();
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
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
        for (TypeTuile tbis : t) {
            nomTuiles.add(tbis.toString());
        }
        
        JPanel panelCentre = new JPanel(new GridLayout(1,2));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(panelCS1 = new JPanel(new GridLayout(1,2)));
        panelCS1.add(new JLabel("Tuiles accessibles : ", JLabel.RIGHT));
        listeDeroulanteSelection = new JComboBox(nomTuiles.toArray());
        panelCS1.add(listeDeroulanteSelection);
        listeDeroulanteSelection.setSelectedIndex(0);
        
        // =================================================================================
        // SUD
        JPanel panelBas = new JPanel(new GridLayout(1,3)) ;
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        boutonRetour = new JButton("Retour");
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MessageType.ANNULER);
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
                notifyObservers(MessageType.VALIDER);
                clearChanged();
            }
        });
        
        panelBas.add(boutonValider);
        
        listeDeroulanteSelection.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
    }

    public void afficher(){
       this.window.setVisible(true);
    }
    
    public void hide() {
        window.dispose();
    }
    
    public static void main(String [] args) {
        ArrayList<TypeTuile> test = new ArrayList<>();
        test.add(TypeTuile.PORTE_OR);
        test.add(TypeTuile.FORET_POURPRE);
        test.add(TypeTuile.PONT_ABIMES);
        VueSelection selection = new VueSelection(test);
        selection.afficher();
   }
}
