package Generateurs;

import Modele.*;
import Utils.*;
import java.util.ArrayList;

public class GrillePredefinie extends Generateur{
	public void generate(Grille tuiles){
            tuiles.add(null);
            tuiles.add(null);
            tuiles.add(new Tuile(2, 0,TypeTuile.PONT_ABIMES, Etat.SECHE));
            tuiles.add(new Tuile(3, 0, TypeTuile.PORTE_BRONZE, Etat.INNONDEE));
            tuiles.add(null);
            tuiles.add(null);
            tuiles.add(null);
            tuiles.add(new Tuile(1, 1, TypeTuile.CAVERNE_OMBRES, Etat.SECHE));
            tuiles.add(new Tuile(2, 1, TypeTuile.PORTE_FER, Etat.SECHE));
            tuiles.add(new Tuile(3,1, TypeTuile.PORTE_OR, Etat.SECHE));
            tuiles.add(new Tuile(4,1, TypeTuile.FALAISES_OUBLI, Etat.SECHE));
            tuiles.add(null);
            tuiles.add(new Tuile(0, 2, TypeTuile.PALAIS_CORAIL, Etat.SECHE));
            tuiles.add(new Tuile(1, 2, TypeTuile.PORTE_ARGENT, Etat.SECHE));
            tuiles.add(new Tuile(2,2, TypeTuile.DUNES_ILLUSION, Etat.COULEE));
            tuiles.add(new Tuile(3,2, TypeTuile.HELIPORT, Etat.SECHE));
            tuiles.add(new Tuile(4, 2, TypeTuile.PORTE_CUIVRE, Etat.SECHE));
            tuiles.add(new Tuile(5,2, TypeTuile.JARDIN_HURLEMENTS, Etat.SECHE));
            tuiles.add(new Tuile(0,3, TypeTuile.FORET_POURPRE, Etat.SECHE));
            tuiles.add(new Tuile(1,3,TypeTuile.LAGON_PERDU, Etat.INNONDEE));
            tuiles.add(new Tuile(2,3, TypeTuile.MARAIS_BRUMEUX, Etat.COULEE));
            tuiles.add(new Tuile(3,3, TypeTuile.OBSERVATOIRE, Etat.INNONDEE));
            tuiles.add(new Tuile(4,3, TypeTuile.ROCHER_FANTOME, Etat.COULEE));
            tuiles.add(new Tuile(5,3, TypeTuile.CAVERNE_BRASIER, Etat.INNONDEE));
            tuiles.add(null);
            tuiles.add(new Tuile(1,4, TypeTuile.TEMPLE_SOLEIL, Etat.SECHE));
            tuiles.add(new Tuile(2,4, TypeTuile.TEMPLE_LUNE, Etat.COULEE));
            tuiles.add(new Tuile(3,4, TypeTuile.PALAIS_MAREES, Etat.SECHE));
            tuiles.add(new Tuile(4,4, TypeTuile.VAL_CREPUSCULE, Etat.SECHE));
            tuiles.add(null);
            tuiles.add(null);
            tuiles.add(null);
            tuiles.add(new Tuile(2,5, TypeTuile.TOUR_GUET, Etat.SECHE));
            tuiles.add(new Tuile(3,5, TypeTuile.JARDIN_HURLEMENTS, Etat.INNONDEE));
            tuiles.add(null);
            tuiles.add(null);
	}
}