package Aventuriers;

import Modele.*;
import Utils.*;
import java.awt.Color;

public class Messager extends Aventurier{
    
    Messager(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_ARGENT;
    }
    public Color getColor(){
        return Color.CYAN;
    }
}