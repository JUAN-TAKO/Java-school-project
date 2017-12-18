package Aventuriers;

import Modele.*;
import Utils.*;

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