package Aventuriers;

import Utils.*;
import Modele.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class Pilote extends Aventurier{
    
    public Pilote(String nomJoueur){
        super(nomJoueur, "Pilote");
    }
    // Le pilote peut se déplacer ou il veut sur la carte une fois par tour pour une action
    // ces méthodes définisse ce déplacement spéficique.
    protected void checkDeplacement(Grille g, HashSet<Tuile> tuilesAccessibles, int x, int y) {
	Tuile t;
	t = g.at(x, y);
        if(t != null && t.getEtat() != Etat.COULEE)
            tuilesAccessibles.add(t);
    }

    public ArrayList<Tuile> getTuilesAccessiblesDeplacement(Grille g){
        HashSet<Tuile> tuilesAccessibles = new HashSet<>();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                checkDeplacement(g, tuilesAccessibles, j, i);
            }
        }
        tuilesAccessibles.remove(getPosition());
	return new ArrayList<Tuile>(tuilesAccessibles);
    }

     // Définition de la tuile de départ et de la couleur du pion.
    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.HELIPORT;
    }
    public Pion getPion(){
        return Pion.BLEU;
    }
}