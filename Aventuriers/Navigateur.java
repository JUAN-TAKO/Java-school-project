package Aventuriers;

import Modele.*;
import Utils.*;
import java.awt.Color;

public class Navigateur extends Aventurier{
    private final int nbActionMax;
    
    public Navigateur(String nomJoueur){
        super(nomJoueur, "Navigateur");
        nbActionMax = 4;
    }

     // Définition de la tuile de départ et de la couleur du pion.
    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_OR;
    }
    public Pion getPion(){
        return Pion.JAUNE;
    }
    
    //Dans notre jeu le navigateur à un nombre d'action différent des autres aventuriers.
    /**
     * @return the nbActionMax
     */
    public int getNbActionMax() {
        return nbActionMax;
    }
}