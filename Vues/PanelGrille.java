/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Generateurs.GrilleAleatoire;
import Modele.Grille;
import Modele.Tuile;
import Utils.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author juan
 */
public class PanelGrille extends JPanel{
    private CompositionObservable observable;
    public PanelGrille(ArrayList<TypeTuile> cases, CompositionObservable obs){
        super(new GridLayout(6, 6));
        observable = obs;
        for(int i = 0; i < cases.size(); i++){
            TypeTuile t = cases.get(i);
            if(t == null){
                add(new PanelTuile());
            }else{
                add(new PanelTuile(t, observable));
            }
        }
    }
    
    public void setEtat(int index, Etat e){
        PanelTuile pt = ((PanelTuile)getComponent(index));
        pt.setEtat(e);
        revalidate();
        repaint();
    }
    
    public void setPions(int index, ArrayList<Pion> pions){
        PanelTuile pt = ((PanelTuile)getComponent(index));
        pt.setPions(pions);
        pt.redraw();
    }
    
    public void redraw(int index){
        ((PanelTuile)getComponent(index)).redraw();
    }
}
