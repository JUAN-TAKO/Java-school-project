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
    
    public PanelGrille(ArrayList<TypeTuile> cases){
        super(new GridLayout(6, 6));
        for(int i = 0; i < cases.size(); i++){
            TypeTuile t = cases.get(i);
            if(t == null){
                add(new PanelTuile());
            }else{
                add(new PanelTuile(t));
            }
        }
    }
    public void setEtat(int index, Etat e){
        PanelTuile pt = ((PanelTuile)getComponent(index));
        pt.setEtat(e);
        pt.redraw();
    }
    public void setPions(int index, ArrayList<Pion> pions){
        PanelTuile pt = ((PanelTuile)getComponent(index));
        pt.setPions(pions);
        pt.redraw();
    }
    public void redraw(int index){
        ((PanelTuile)getComponent(index)).redraw();
    }
    public static void main(String args[]){
        Grille g = new Grille(new GrilleAleatoire());
        JFrame window = new JFrame();
        ArrayList<TypeTuile> types = new ArrayList<>();
        for(int i = 0; i < g.length(); i++){
            Tuile t = g.get(i);
            if(t == null){
                types.add(null);
            }else{
                types.add(t.getType());
            }
        }
        PanelGrille panelGrille = new PanelGrille(types);
        panelGrille.setEtat(2, Etat.INONDEE);
        panelGrille.setEtat(7, Etat.INONDEE);
        panelGrille.setEtat(17, Etat.INONDEE);
        panelGrille.setEtat(26, Etat.INONDEE);
        ArrayList<Pion> pions = new ArrayList<>();
        pions.add(Pion.BLEU);
        panelGrille.setPions(9, pions);
        window.add(panelGrille);
        window.setSize(1000, 1000);
        window.setVisible(true);
    }
}
