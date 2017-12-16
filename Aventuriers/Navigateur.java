package Aventuriers;

import Modele.*;

public class Navigateur extends Aventurier{
    
    Navigateur(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_OR;
    }
}