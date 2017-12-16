package Modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Grille {
    private ArrayList<Tuile> tuiles;
    private HashMap<TypeTuile, Tuile> tuilesMap; 
    private int size;
    
    public Grille(Generateur generateur) {
        tuiles = new ArrayList<>();
        tuilesMap = new HashMap<>();
        generateur.generate(tuiles);
    }
    public Tuile at(int x, int y){
        return tuiles.get(y*size + x);
    }
    public Tuile getTuileById(int id){
        return tuiles.get(id);
    }
    public void addTuile(){
        
    }
}
