package modele.ileinterdite;

import java.util.ArrayList;

public class Grille {
    private ArrayList<Tuile> tuiles;
    private int size;
    Grille(Generateur generateur) {
        tuiles = new ArrayList<>();
        generateur.generate(tuiles);
    }
    public Tuile at(int x, int y){
  		return tuiles.at(y*size + x);
    }
}
