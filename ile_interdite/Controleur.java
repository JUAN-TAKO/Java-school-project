package ile_interdite;

import Generateurs.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Modele.*;
import Vues.*;
import Utils.*;
import java.awt.Color;

public class Controleur implements Observer{
    private VueParametres vueParametres;
	
    private Grille grille;
    private ArrayList<Aventurier> aventuriers;
    private ArrayList<VueAventurier> vuesAventuriers;
        
    private int indexAventurierCourant;
    private int action;
    private int tour;
    private Message lastMessage; 
    
    public Controleur(){

        vuesAventuriers.add(new VueAventurier("Dami1", "Pilote", Color.BLUE));
        vuesAventuriers.add(new VueAventurier("Tibo", "Navigateur", Color.GREEN));
        vuesAventuriers.add(new VueAventurier("Delf1", "Messager", Color.RED));
        vuesAventuriers.add(new VueAventurier("Juan", "Ingenieur", Color.ORANGE));

        vueParametres = new VueParametres();

        Generateur g = new GrillePredefinie();
        grille = new Grille(g);
        tour = 0;
        indexAventurierCourant = 0;
        action = 0;
    }
	
    public void start(){

    }
    public Aventurier getAventurierCourant(){
        return aventuriers.get(indexAventurierCourant);
    }


    public void actionSuivante(){
        action++;
        if(action >= 4){
            action = 0;
            aventurierSuivant();
        }
    }
    public void aventurierSuivant(){
        indexAventurierCourant++;
        if(indexAventurierCourant >= aventuriers.size()){
            tourSuivant();
        }
        afficherAventurier();

    }
    public void afficherAventurier(){
        Aventurier av = getAventurierCourant();
        for(VueAventurier v : vuesAventuriers){
            v.setActive(false);
        }
        vuesAventuriers.get(indexAventurierCourant).setActive(true);
    }

    public void tourSuivant(){
        tour++;
        indexAventurierCourant = 0;
    }

    @Override
    public void update(Observable o, Object arg){
        Message m = (Message)arg;
        MessageTuile mt = (MessageTuile)arg;

        switch(m.getType()){
            case BTN_DEPLACER:  //clic sur le bouton déplacer
                typeAction = 0;
                ArrayList<Tuile> l = getAventurierCourant().getTuilesAccessiblesDeplacement(grille);
                ArrayList<TypeTuile> typeTuiles;
                
                for(Tuile t : l){
                    nomTuiles.add(t.getType());
                }
                
                vueSelection.afficher(typeTuiles);
                
                if(getAventurierCourant().instanceof(Ingenieur)){
                    vueSelection.afficher(typeTuiles);
                }
                
                actionSuivante;
                break;
            case BTN_ASSECHER:  //clic sur le bouton assecher
                typeAction = 1;
                ArrayList<Tuile> l = getAventurierCourant().getTuilesAccessiblesAssechement(grille);
                ArrayList<String> nomTuiles;
                
                for(Tuile t : l){
                    nomTuiles.add(t.getType().toString() + "(" + t.getX() + " ; " + t.getY());
                }
                
                vueSelection.afficher(nomTuiles);
                
                actionSuivante();
                break;
            case BTN_SPECIAL:   //clic sur le bouton spécial
                typeAction = 2;
                
                break;
            case BTN_PASSER:    //clic sur le bouton passer
                aventurierSuivant();
                break;

            case VALIDER_SELECTION:   //effectuer l'action sur la case selectionnée

                switch(typeAction){
                    case 0:
                        getAventurierCourant().setPosition(grille.getTuileById(mi.getId()));
                        break;	
                    case 1:
                        grille.getTuileById(mi.getId()).setEtat(Etat.SECHE);
                        break;
                    case 2:
                        break;

                }
                break;
        }
    }
}	