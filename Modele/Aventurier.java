package Modele;

import Utils.CarteTirage;

import Utils.*;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Aventurier extends Observable{
    private Tuile position;
    private String nom;
    private String nomRole;
    private int[] cartes;
    private final int nbActionMax;

    public Aventurier(String nom, String nomRole){
        this.nom = nom;
        this.nomRole = nomRole;
        cartes = new int[7];
        nbActionMax = 3;
    }
    
    //vérifie qu'une tuile est accessible
    protected void checkDeplacement(Grille g, ArrayList<Tuile> tuilesAccessibles, int x, int y) {
        Tuile t;
        t = g.at(getPosition().getX() + x, getPosition().getY() + y);
        if(t != null && t.getEtat() != Etat.COULEE){
            tuilesAccessibles.add(t);
        }
    }
    //vérifie qu'une tuile est asséchable
    protected void checkAssechement(Grille g, ArrayList<Tuile> tuilesAccessibles, int x, int y) {
        Tuile t;
        t = g.at(getPosition().getX()+x, getPosition().getY()+y);
        if(t != null && t.getEtat() == Etat.INONDEE){
            tuilesAccessibles.add(t);
        }
    }
    
    public ArrayList<Tuile> getTuilesAccessiblesDeplacement(Grille g){
        ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        checkDeplacement(g, tuilesAccessibles, 0, 1);
        checkDeplacement(g, tuilesAccessibles, 1, 0);
        checkDeplacement(g, tuilesAccessibles, 0, -1);
        checkDeplacement(g, tuilesAccessibles, -1, 0);

        return tuilesAccessibles;
    }

    public ArrayList<Tuile> getTuilesAccessiblesAssechement(Grille g){
        ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        
        checkAssechement(g, tuilesAccessibles, 0, 0);
        checkAssechement(g, tuilesAccessibles, 0, 1);
        checkAssechement(g, tuilesAccessibles, 1, 0);
        checkAssechement(g, tuilesAccessibles, 0, -1);
        checkAssechement(g, tuilesAccessibles, -1, 0);

        return tuilesAccessibles;
    }
  public ArrayList<Tuile> getTuilesSpeciales(Grille g){
      return new ArrayList<>();
  }
//    public abstract void actionSpeciale(Grille g);
	
    public String getNom(){
        return nom;
    }
    public String getNomRole(){
        return nomRole;
    }
    public abstract Pion getPion();
    /**
     * @return the position
     */
    public Tuile getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Tuile position) {
        this.position = position;
    }
    
    public abstract TypeTuile getTuileDepart();  
    
    public void addCarte(CarteTirage c){
        cartes[c.ordinal()]++;
    }
    public void removeCarte(CarteTirage c){
        removeCarte(c, 1);
    }
    public void removeCarte(CarteTirage c, int nbCartes){
        cartes[c.ordinal()] -= nbCartes;
        if(cartes[c.ordinal()] < 0){
            cartes[c.ordinal()] = 0;
        }
    }
    public int getCartes(CarteTirage c){
        return cartes[c.ordinal()];
    }
    public int getNbCartes(){
        int nb = 0;
        for(int i = 0; i < 6; i++){
            nb += cartes[i];
        }
        return nb;
    }

    /**
     * @return the nbActionMax
     */
    public int getNbActionMax() {
        return nbActionMax;
    }
}
