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
    
    //récupère la tuile a la position x, y
    public Tuile at(int x, int y){
        int index = y*size + x;
        return get(index);
    }
    
    //récupère la tuile associé au type entré.
    public Tuile getTuileByType(TypeTuile type){
        return tuilesMap.get(type);
    }
    
    // récupère l'index d'une tuile
    public int getIndexTuile(Tuile t){
        return t.getX() + t.getY() * size;
    }
    
    public void setSize(int s){
        size = s;
    }
    
    public int length(){
        return size*size;
    }
    
    public Tuile get(int index){
        if(index < tuiles.size() && index >= 0){
            return tuiles.get(index);
        }
        return null;
    }
    
    //ajoute une tuile à la grille
    public void add(TypeTuile t, Etat e){
        Tuile tuile = new Tuile((tuiles.size())%size, (tuiles.size())/size, t, e);
        tuiles.add(tuile);
        tuilesMap.put(tuile.getType(), tuile);
    }
    
    //vide la grille
    public void addEmpty(){
        tuiles.add(null);
    }
}
