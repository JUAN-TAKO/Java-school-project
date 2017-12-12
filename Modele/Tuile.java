package modele.ileinterdite;

public class Tuile {
    private int x;
    private int y;
    private String nom;
    private Tresor tresor;
    private Etat etat;

    Tuile(int x, int y, String nom, Tresor tresor){
        setX(x);
        setY(y);
        setNom(nom);
        setTresor(tresor);
        setEtat(Etat.SECHE);
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
