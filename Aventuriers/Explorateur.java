package Aventuriers;

import Modele.*;
import Utils.*;
import java.awt.Color;
import java.util.ArrayList;

public class Explorateur extends Aventurier{
    
    Explorateur(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }

    @Override
    public void seDeplacer(Grille g){
		ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        Tuile t;
        checkDeplacement(g, tuilesAccessibles, 0, 1);
        checkDeplacement(g, tuilesAccessibles, 1, 0);
        checkDeplacement(g, tuilesAccessibles, 1, 1);
        checkDeplacement(g, tuilesAccessibles, 0, -1);
        checkDeplacement(g, tuilesAccessibles, -1, 0);
        checkDeplacement(g, tuilesAccessibles, -1, -1);
        checkDeplacement(g, tuilesAccessibles, 1, -1);
        checkDeplacement(g, tuilesAccessibles, -1, 1);

	setChanged();
	MessageTuiles m = new MessageTuiles(MessageType.SELECT_DEPLACEMENT, tuilesAccessibles);
	notifyObservers(m);
        clearChanged();
        
        finAction();
	}

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_FER;
    }
    public Color getColor(){
        return Color.GREEN;
    }
}
