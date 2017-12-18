package Aventuriers;

import Utils.*;
import java.util.ArrayList;

import Modele.*;

public class Ingenieur extends Aventurier{
    
    Ingenieur(String nomJoueur, String nomRole){
        super(nomJoueur,nomRole);
    }

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_BRONZE;
    }
    public Color getColor(){
        return Color.RED;
    }
}
