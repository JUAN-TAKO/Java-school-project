package Aventuriers;

import Modele.*;
import Messages.*;
import java.util.ArrayList;

public class Explorateur extends Aventurier{
    
    Explorateur(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }

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
}
