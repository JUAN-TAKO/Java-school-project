package Aventuriers;

import Utils.*;
import java.util.ArrayList;

import Modele.*;

public class Ingenieur extends Aventurier{
    
    Ingenieur(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }
    public void assecher(Grille g){
		ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
		checkAssechement(g, tuilesAccessibles, 0, 1);
        checkAssechement(g, tuilesAccessibles, 1, 0);
        checkAssechement(g, tuilesAccessibles, 0, -1);
        checkAssechement(g, tuilesAccessibles, -1, 0);
		
		setChanged();
		MessageTuiles m = new MessageTuiles(MessageType.SELECT_ASSECHER, tuilesAccessibles);
		notifyObservers(m);
        clearChanged();
        tuilesAccessibles.clear();

        checkAssechement(g, tuilesAccessibles, 0, 1);
        checkAssechement(g, tuilesAccessibles, 1, 0);
        checkAssechement(g, tuilesAccessibles, 0, -1);
        checkAssechement(g, tuilesAccessibles, -1, 0);

        setChanged();
		notifyObservers(m);
        clearChanged();

        finAction();

	}

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_BRONZE;
    }
    public Color getColor(){
        return Color.RED;
    }
}
