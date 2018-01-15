package Modele;

import Utils.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Grille {
    private ArrayList<Tuile> tuiles;
    private HashMap<TypeTuile, Tuile> tuilesMap; 
    private int size;
    
    public Grille(Generateur generateur) {
        tuiles = new ArrayList<>();
        tuilesMap = new HashMap<>();
        generateur.generate(this);
    }
    public Tuile at(int x, int y){
        int index = y*size + x;
        if(index < tuiles.size() && index >= 0){
            return tuiles.get(index);
        }
        return null;
    }
    public Tuile getTuileByType(TypeTuile type){
        return tuilesMap.get(type);
    }
    public void setSize(int s){
        size = s;
    }
    public int getLength(){
        return size*size;
    }
    public void add(TypeTuile t, Etat e){
        Tuile tuile = new Tuile((tuiles.size())%size, (tuiles.size())/size, t, e);
        tuiles.add(tuile);
        tuilesMap.put(tuile.getType(), tuile);
    }
    public void addEmpty(){
        tuiles.add(null);
    }
}
