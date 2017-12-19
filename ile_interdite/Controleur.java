package ile_interdite;

import Generateurs.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Modele.*;
import Vues.*;
import Utils.*;
import Aventuriers.*;
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
        lastMessage = (Message)arg;
        MessageTuile mt = (MessageTuile)arg;
        ArrayList<Tuile> l;
        ArrayList<TypeTuile> typeTuiles;
        
        switch(lastMessage.getType()){
            case DEPLACER:  //clic sur le bouton déplacer
                l = getAventurierCourant().getTuilesAccessiblesDeplacement(grille);
                typeTuiles = new ArrayList<>();
                
                for(Tuile t : l){
                    typeTuiles.add(t.getType());
                }
                vueSelection vueS = new vueSelection(typeTuiles);
                vueS.afficher();
                
                if(getAventurierCourant() instanceof Ingenieur){
                    vueS = new vueSelection(typeTuiles);
                    vueS.afficher();
                }
                
                actionSuivante();
                break;
            case ASSECHER:  //clic sur le bouton assecher
                l = getAventurierCourant().getTuilesAccessiblesAssechement(grille);
                typeTuiles = new ArrayList<>();
                
                for(Tuile t : l){
                    typeTuiles.add(t.getType());
                }
                
                vueS = new vueSelection(typeTuiles);
                vueS.afficher();
                
                actionSuivante();
                break;
            case SPECIAL:   //clic sur le bouton spécial
                
                
                break;
            case PASSER:    //clic sur le bouton passer
                aventurierSuivant();
                break;

            case VALIDER_SELECTION:   //effectuer l'action sur la case selectionnée

                switch(lastMessage.getType()){
                    case DEPLACER:
                        getAventurierCourant().setPosition(grille.getTuileByType(mt.getTuile()));
                        break;	
                    case ASSECHER:
                        grille.getTuileByType(mt.getTuile()).setEtat(Etat.SECHE);
                        break;
                    case SPECIAL:
                        break;

                }
                break;
        }
    }
}	