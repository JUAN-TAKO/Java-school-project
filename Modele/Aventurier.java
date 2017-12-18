package Modele;

import Utils.*;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Aventurier extends Observable{
    private Tuile position;
    private String nom;
    private String nomRole;
        
    public Aventurier(String nom, String nomRole){
        this.nom = nom;
        this.nomRole = nomRole;
    }
	
    protected void checkDeplacement(Grille g, ArrayList<Tuile> tuilesAccessibles, int x, int y) {
        Tuile t;
        t = g.at(getPosition().getX()+x, getPosition().getY()+y);
        if(t != null && t.getEtat() != Etat.COULEE){
            tuilesAccessibles.add(t);
        }
    }
    protected void checkAssechement(Grille g, ArrayList<Tuile> tuilesAccessibles, int x, int y) {
        Tuile t;
        t = g.at(getPosition().getX()+x, getPosition().getY()+y);
        if(t != null && t.getEtat() == Etat.INNONDEE){
            tuilesAccessibles.add(t);
        }
    }
    
    public void seDeplacer(Grille g){
        ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        checkDeplacement(g, tuilesAccessibles, 0, 1);
        checkDeplacement(g, tuilesAccessibles, 1, 0);
        checkDeplacement(g, tuilesAccessibles, 0, -1);
        checkDeplacement(g, tuilesAccessibles, -1, 0);

        setChanged();
        MessageTuiles m = new MessageTuiles(MessageType.SELECT_DEPLACEMENT, tuilesAccessibles);
        notifyObservers(m);
        clearChanged();

        finAction();
    }

    public void assecher(Grille g){
        ArrayList<Tuile> tuilesAccessibles = new ArrayList<>();
        
        checkAssechement(g, tuilesAccessibles, 0, 0);
        checkAssechement(g, tuilesAccessibles, 0, 1);
        checkAssechement(g, tuilesAccessibles, 1, 0);
        checkAssechement(g, tuilesAccessibles, 0, -1);
        checkAssechement(g, tuilesAccessibles, -1, 0);

        setChanged();
        MessageTuiles m = new MessageTuiles(MessageType.SELECT_ASSECHER, tuilesAccessibles);
        notifyObservers(m);
        clearChanged();

        finAction();
    }
	
    public void actionSpeciale(Grille g){}
    
    protected void finAction(){
        setChanged();
        Message m = new Message(MessageType.ACTION);
        notifyObservers(m);
        clearChanged();
    }
	
    public String getNom(){
        return nom;
    }
    public String getNomRole(){
        return nomRole;
    }
    public abstract Color getColor();
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
}