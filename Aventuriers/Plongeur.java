package Aventuriers;

import java.util.ArrayList;
import java.util.HashSet;

import Modele.*;
import Messages.*;
public class Plongeur extends Aventurier{
    
    Plongeur(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }
    protected void checkTuile(Grille g, HashSet<Tuile> tuilesAccessibles, HashSet<Tuile> tuilesInnondees, Tuile t) {
        ArrayList<Tuile> adjacentes = new ArrayList<>();
        adjacentes.add(g.at(t.getX()+1, t.getY()));
        adjacentes.add(g.at(t.getX()-1, t.getY()));
        adjacentes.add(g.at(t.getX(), t.getY()+1));
        adjacentes.add(g.at(t.getX(), t.getY()-1));

        for(Tuile tu : adjacentes){
            if(t.getEtat() == Etat.COULEE){
                if(tuilesInnondees.add(tu)){ //renvoie true si c'est une nouvelle tuile
                    checkTuile(g, tuilesAccessibles, tuilesInnondees, tu);
                }
            }
            else{
                tuilesAccessibles.add(tu);
            }
        }
    }

    public void seDeplacer(Grille g){
	HashSet<Tuile> tuilesAccessibles = new HashSet<>();
        HashSet<Tuile> tuilesCoulees = new HashSet<>();
        tuilesAccessibles.add(getPosition());
        checkTuile(g, tuilesAccessibles, tuilesCoulees, getPosition());
        tuilesAccessibles.remove(getPosition());
        ArrayList<Tuile> tacc = new ArrayList(tuilesAccessibles);

	setChanged();
	MessageTuiles m = new MessageTuiles(MessageType.SELECT_DEPLACEMENT, tacc);
	notifyObservers(m);
	clearChanged();
        
        finAction();
    }
}