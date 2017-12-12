package Modele;

public class Tuile {
    private int x;
    private int y;
    private String nom;
    private Tresor tresor;
    private Etat etat;

    Tuile(int cx, int cy, String nomT, Tresor tresor){
        x = cx;
        y = cy;
        nom = nomT;
        this.tresor = tresor;
        etat = Etat.SECHE;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
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
