package Aventuriers;

import Utils.*;
import java.util.ArrayList;
import java.util.HashSet;

import Modele.*;
import java.awt.Color;

public class Plongeur extends Aventurier{
    
    public Plongeur(String nomJoueur){
        super(nomJoueur, "Plongeur");
    }
    //Le plongeur peut traverser des cases coulés pour accéder à n'importe quelle tuiles sèches ou inondés.
    protected void checkTuile(Grille g, HashSet<Tuile> tuilesAccessibles, HashSet<Tuile> tuilesCoulees, Tuile t) {
        ArrayList<Tuile> adjacentes = new ArrayList<>();
        adjacentes.add(g.at(t.getX()+1, t.getY()));
        adjacentes.add(g.at(t.getX()-1, t.getY()));
        adjacentes.add(g.at(t.getX(), t.getY()+1));
        adjacentes.add(g.at(t.getX(), t.getY()-1));

        for(Tuile tu : adjacentes){
            if(tu != null){
                if(tu.getEtat() != Etat.SECHE){
                    if(tuilesCoulees.add(tu)){ //renvoie true si c'est une nouvelle tuile
                        if(tu.getEtat() == Etat.INONDEE){
                            tuilesAccessibles.add(tu);
                        }
                        checkTuile(g, tuilesAccessibles, tuilesCoulees, tu);
                    }
                }
                else{
                    tuilesAccessibles.add(tu);
                }
            }           
        }
    }

    @Override
    public ArrayList<Tuile> getTuilesAccessiblesDeplacement(Grille g){    
	HashSet<Tuile> tuilesAccessibles = new HashSet<>();
        HashSet<Tuile> tuilesCoulees = new HashSet<>();
        tuilesAccessibles.add(getPosition());
        checkTuile(g, tuilesAccessibles, tuilesCoulees, getPosition());
        tuilesAccessibles.remove(getPosition());
        return new ArrayList(tuilesAccessibles);
        
    }

    // Définition de la tuile de départ et de la couleur du pion.
    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_CUIVRE;
    }
    @Override
    public Pion getPion(){
        return Pion.NOIR;
    }
}