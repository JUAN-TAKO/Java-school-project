package Modele;

import Utils.Tresor;
import Utils.*;

public class Tuile {
    private int x;
    private int y;
    private TypeTuile type;
    private Etat etat;
    private int id;

    public Tuile(int cx, int cy, TypeTuile t){
        x = cx;
        y = cy;
        id = 6*cy + cx;
        type = t;
        etat = Etat.SECHE;
    }
    public Tuile(int cx, int cy, TypeTuile t, Etat e){
        x = cx;
        y = cy;
        id = 6*cy + cx;
        type = t;
        etat = e;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getId(){
        return id;
    }
    public String getNom() {
        return type.toString();
    }
    public TypeTuile getType(){
        return type;
    }
    public Tresor getTresor() {
        return type.getTresor();
    }
    public Etat getEtat() {
        return etat;
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
}
