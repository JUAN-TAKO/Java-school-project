package Modele;

public class Tuile {
    private int x;
    private int y;
    private TypeTuile type;
    private Tresor tresor;
    private Etat etat;
    private int id;

    Tuile(int cx, int cy, TypeTuile t, Tresor tresor){
        x = cx;
        y = cy;
        id = 6*cy + cx;
        type = t;
        this.tresor = tresor;
        etat = Etat.SECHE;
    }
    Tuile(int cx, int cy, TypeTuile t, Etat e){
        x = cx;
        y = cy;
        id = 6*cy + cx;
        type = t;
        this.tresor = Tresor.AUCUN;
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
        return nom;
    }
    public Tresor getTresor() {
        return tresor;
    }
    public Etat getEtat() {
        return etat;
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
}
