package Aventuriers;

import Modele.*;
import Utils.*;
import java.awt.Color;

public class Messager extends Aventurier{
    
    public Messager(String nomJoueur){
        super(nomJoueur, "Messager");
    }

     // Définition de la tuile de départ et de la couleur du pion.
    @Override
    public TypeTuile getTuileDepart() {
        return TypeTuile.PORTE_ARGENT;
    }
    public Pion getPion(){
        return Pion.GRIS;
    }
    
    
    
}