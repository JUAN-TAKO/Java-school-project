package Generateurs;

import Modele.*;
import Utils.*;
import java.util.ArrayList;

public class GrillePredefinie extends Generateur{
    
	public void generate(Grille tuiles){
            
            tuiles.setSize(6);
            
            tuiles.addEmpty();
            tuiles.addEmpty();
            tuiles.add(TypeTuile.PONT_ABIMES, Etat.SECHE);
            tuiles.add(TypeTuile.PORTE_BRONZE, Etat.INONDEE);
            tuiles.addEmpty();
            tuiles.addEmpty();
            
            tuiles.addEmpty();
            tuiles.add(TypeTuile.CAVERNE_OMBRES, Etat.SECHE);
            tuiles.add(TypeTuile.PORTE_FER, Etat.SECHE);
            tuiles.add(TypeTuile.PORTE_OR, Etat.SECHE);
            tuiles.add(TypeTuile.FALAISES_OUBLI, Etat.SECHE);
            tuiles.addEmpty();
            
            tuiles.add(TypeTuile.PALAIS_CORAIL, Etat.SECHE);
            tuiles.add(TypeTuile.PORTE_ARGENT, Etat.SECHE);
            tuiles.add(TypeTuile.DUNES_ILLUSION, Etat.COULEE);
            tuiles.add(TypeTuile.HELIPORT, Etat.SECHE);
            tuiles.add(TypeTuile.PORTE_CUIVRE, Etat.SECHE);
            tuiles.add(TypeTuile.JARDIN_HURLEMENTS, Etat.SECHE);
            
            tuiles.add(TypeTuile.FORET_POURPRE, Etat.SECHE);
            tuiles.add(TypeTuile.LAGON_PERDU, Etat.INONDEE);
            tuiles.add(TypeTuile.MARAIS_BRUMEUX, Etat.COULEE);
            tuiles.add(TypeTuile.OBSERVATOIRE, Etat.INONDEE);
            tuiles.add(TypeTuile.ROCHER_FANTOME, Etat.COULEE);
            tuiles.add(TypeTuile.CAVERNE_BRASIER, Etat.INONDEE);
            
            tuiles.addEmpty();
            tuiles.add(TypeTuile.TEMPLE_SOLEIL, Etat.SECHE);
            tuiles.add(TypeTuile.TEMPLE_LUNE, Etat.COULEE);
            tuiles.add(TypeTuile.PALAIS_MAREES, Etat.SECHE);
            tuiles.add(TypeTuile.VAL_CREPUSCULE, Etat.SECHE);
            tuiles.addEmpty();
            
            tuiles.addEmpty();
            tuiles.addEmpty();
            tuiles.add(TypeTuile.TOUR_GUET, Etat.SECHE);
            tuiles.add(TypeTuile.JARDIN_MURMURES, Etat.INONDEE);
            tuiles.addEmpty();
            tuiles.addEmpty();
	}
}