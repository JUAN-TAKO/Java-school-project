package Vues;

import Utils.CarteTirage;
import Aventuriers.Explorateur;
import Modele.Aventurier;
import Modele.Tuile;
import Utils.Pion;
import Utils.*;
import Vues.PanelImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.SwingConstants;

public class PanelJoueur extends JPanel{

    private JPanel panelJoueur;
    private JPanel mainPanel;
    private int coin;
    private CompositionObservable obs;
    private Pion pion;
    
    public PanelJoueur(int c, String nomJoueur, Pion pionJoueur, CompositionObservable o){
        super(new BorderLayout());
        setBackground(Color.white);
        coin = c;
        obs = o;
        pion = pionJoueur;
        panelJoueur = new JPanel(new BorderLayout());
        //condition ternaire. la syntaxe est : "variable = condition ? retourQuandVrai : retourQuandFaux;"
        String pos = coin >= 2 ? BorderLayout.NORTH : BorderLayout.SOUTH; // si coin >= 2 (en bas), pos = NORTH; sinon pos = SOUTH
        JLabel labJ = new JLabel(nomJoueur, SwingConstants.CENTER);
        panelJoueur.add(labJ, pos);
        PanelImage pi = new PanelImage(pionJoueur.getJoueur(), 2);
        panelJoueur.add(pi, BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                setSize(new Dimension(getWidth(), mainPanel.getHeight()));
            }
        });    
        updateCartes(null);
                     
    }
    public void setWidth(int w){
        int wa = getWidth();
        int ha = getHeight();
        float ratio = (float)ha / (float)wa;
        int h = (int)(ratio * (float)w);
        this.setPreferredSize(new Dimension(w, h));
    }
    public int getH(){
        return ((PanelImage)mainPanel.getComponent(1)).getH();
    }
    public PanelJoueur(int c){
        super(new BorderLayout());
        coin = c;
        panelJoueur = new JPanel(new BorderLayout());
        //condition ternaire. la syntaxe est : "variable = condition ? retourQuandVrai : retourQuandFaux;"
        String pos = coin >= 2 ? BorderLayout.NORTH : BorderLayout.SOUTH; // si coin >= 2 (en bas), pos = NORTH; sinon pos = SOUTH
        
        panelJoueur.add(new JLabel(), pos);
        PanelImage pi = new PanelImage();
        panelJoueur.add(pi, BorderLayout.CENTER);
    }
    private void addListener(JPanel p, CarteTirage ret, CompositionObservable o){
        p.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                obs.setChanged();
                obs.notifyObservers(new MessageDefausser(ret, pion));
                obs.clearChanged();
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
    public void updateCartes(ArrayList<CarteTirage> cartes){
        Boolean gauche = (coin % 2 == 0);
        int nbCartes;
        if(mainPanel != null)
            remove(mainPanel);
        mainPanel = new JPanel(new GridLayout(1, 8, 5, 0));
        if(cartes == null){
            nbCartes = 0;
        }
        else{
            nbCartes = cartes.size();
        }
        if(gauche){
            mainPanel.add(panelJoueur);
            for(int i = 0; i < 7; i++){
                if(i < nbCartes){
                    PanelImage p = new PanelImage(cartes.get(i).getImage(), 2);
                    addListener(p, cartes.get(i), obs);
                    mainPanel.add(p);
                }
                else{          
                    mainPanel.add(new PanelImage());       
                }
            }
        }
        else{
            for(int i = 6; i > -1; i--){
                if(i >= nbCartes)
                    mainPanel.add(new PanelImage());
                else
                    mainPanel.add(new PanelImage(cartes.get(i).getImage(), 2));
            }
            mainPanel.add(panelJoueur);
        }
        add(mainPanel, BorderLayout.CENTER);
        
    }
    
    public static void main(String [] args) {
        JFrame  window = new JFrame();
        CompositionObservable o = new CompositionObservable();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(900, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Joueur");    
        
        Explorateur joueur = new Explorateur("tibo");
        PanelJoueur vueJoueur = new PanelJoueur(0, joueur.getNom(), joueur.getPion(), o);
        ArrayList<CarteTirage> cartes = new ArrayList<>();
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CALICE);
        cartes.add(CarteTirage.TRESOR_CRISTAL);
        cartes.add(CarteTirage.SABLE);
        window.add(vueJoueur);
        vueJoueur.updateCartes(cartes);
        window.setVisible(true);
   }
    
    
}
