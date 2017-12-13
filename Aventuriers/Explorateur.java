package Aventuriers;

import Modele.*;

public class Explorateur extends Aventurier{
    
    Explorateur(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }

    public void seDeplacer(Grille g){
		ArrayList<Tuile> tuilesAccessibles;
        Tuile t;
        check(g, tuilesAccessibles, 0, 1);
        check(g, tuilesAccessibles, 1, 0);
        check(g, tuilesAccessibles, 1, 1);
        check(g, tuilesAccessibles, 0, -1);
        check(g, tuilesAccessibles, -1, 0);
        check(g, tuilesAccessibles, -1, -1);
        check(g, tuilesAccessibles, 1, -1);
        check(g, tuilesAccessibles, -1, 1);

		setChanged();
		MessageTuiles m = new MessageTuiles(MessageType.TUILES_DEPLACEMENT, tuilesAccessibles);
		notifyObservers(m);
		clearChanged();
	}

	private void check(Grille g, ArrayList<Tuile> tuilesAccessibles, int x, int y) {
		Tuile t;
		t = g.at(getPosition().getX()+x, getPosition().getY()+y);
        if(t != NULL && t.getEtat() != Etat.COULEE)
            tuilesAccessibles.add(t);
	}
}
