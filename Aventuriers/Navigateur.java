package Aventuriers;

import Modele.*;
import Utils.*;
import java.awt.Color;

public class Navigateur extends Aventurier{
    
    public Navigateur(String nomJoueur){
        super(nomJoueur, "Navigateur");
    }

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_OR;
    }
    public Color getColor(){
        return Color.YELLOW;
    }
}