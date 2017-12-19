package Aventuriers;

import Utils.*;
import Modele.*;
import java.awt.Color;
import java.util.ArrayList;

public class Pilote extends Aventurier{
    
    public Pilote(String nomJoueur){
        super(nomJoueur, "Pilote");
    }
    protected void checkDeplacement(Grille g, ArrayList<Tuile> tuilesAccessibles, int x, int y) {
		Tuile t;
		t = g.at(x, y);
        if(t != null && t.getEtat() != Etat.COULEE)
            tuilesAccessibles.add(t);
    }

    public ArrayList<Tuile> getTuilesAccessiblesDeplacement(Grille g){
		ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                checkDeplacement(g, tuilesAccessibles, j, i);
            }
        }

		return tuilesAccessibles;
	}

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.HELIPORT;
    }
    public Color getColor(){
        return Color.MAGENTA;
    }
}