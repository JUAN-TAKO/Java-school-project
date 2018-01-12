package Aventuriers;

import Modele.*;
import Utils.*;
import java.awt.Color;
import java.util.ArrayList;

public class Explorateur extends Aventurier{
    
    public Explorateur(String nomJoueur){
        super(nomJoueur, "Explorateur");
    }

    @Override
    public ArrayList<Tuile> getTuilesAccessiblesDeplacement(Grille g){
		ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        checkDeplacement(g, tuilesAccessibles, 0, 1);
        checkDeplacement(g, tuilesAccessibles, 1, 0);
        checkDeplacement(g, tuilesAccessibles, 1, 1);
        checkDeplacement(g, tuilesAccessibles, 0, -1);
        checkDeplacement(g, tuilesAccessibles, -1, 0);
        checkDeplacement(g, tuilesAccessibles, -1, -1);
        checkDeplacement(g, tuilesAccessibles, 1, -1);
        checkDeplacement(g, tuilesAccessibles, -1, 1);
        return tuilesAccessibles;
	}
	public ArrayList<Tuile> getTuilesAccessiblesAssechement(Grille g){
		ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
		checkAssechement(g, tuilesAccessibles, 0, 0);
        checkAssechement(g, tuilesAccessibles, 0, 1);
        checkAssechement(g, tuilesAccessibles, 1, 0);
        checkAssechement(g, tuilesAccessibles, 1, 1);
        checkAssechement(g, tuilesAccessibles, 0, -1);
        checkAssechement(g, tuilesAccessibles, -1, 0);
        checkAssechement(g, tuilesAccessibles, -1, -1);
        checkAssechement(g, tuilesAccessibles, 1, -1);
        checkAssechement(g, tuilesAccessibles, -1, 1);
        
        return tuilesAccessibles;
	}

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_FER;
    }
    public Pion getPion(){
        return Pion.VERT;
    }
}
