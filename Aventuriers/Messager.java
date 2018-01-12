package Aventuriers;

import Modele.*;
import Utils.*;
import java.awt.Color;

public class Messager extends Aventurier{
    
    public Messager(String nomJoueur){
        super(nomJoueur, "Messager");
    }

    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_ARGENT;
    }
    public Pion getPion(){
        return Pion.GRIS;
    }
}