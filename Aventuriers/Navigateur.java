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

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_OR;
    }
    public Pion getPion(){
        return Pion.JAUNE;
    }
    
    /**
     * @return the nbActionMax
     */
    public int getNbActionMax() {
        return nbActionMax;
    }
}