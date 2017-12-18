package Aventuriers;

import Utils.*;
import Modele.*;
import java.util.ArrayList;

public class Pilote extends Aventurier{
    
    Pilote(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }
    protected void checkDeplacement(Grille g, ArrayList<Tuile> tuilesAccessibles, int x, int y) {
		Tuile t;
		t = g.at(x, y);
        if(t != null && t.getEtat() != Etat.COULEE)
            tuilesAccessibles.add(t);
    }

    public void seDeplacer(Grille g){
		ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                checkDeplacement(g, tuilesAccessibles, j, i);
            }
        }

		setChanged();
		MessageTuiles m = new MessageTuiles(MessageType.SELECT_DEPLACEMENT, tuilesAccessibles);
		notifyObservers(m);
        clearChanged();
        
        finAction();
	}

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.HELIPORT;
    }
    public Color getColor(){
        return Color.PURPLE;
    }
}