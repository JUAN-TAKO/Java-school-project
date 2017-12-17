package ile_interdite;

import Generateurs.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Modele.*;
import Vues.*;
import Messages.*;

public class Controleur implements Observer{
    private VueParametres vueParametres;
	
    private Grille grille;
    private ArrayList<Aventurier> aventuriers;
    private ArrayList<VueAventurier> vuesAventuriers;
        
    private int indexAventurierCourant;
    private int action;
    private int tour;
    private int typeAction; //0: deplacer, 1: assecher, 2: special

    public Controleur(){

        vuesAventuriers.add(new VueAventurier("Dami1", "Pilote"));
        vuesAventuriers.add(new VueAventurier("Tibo", "Navigateur"));
        vuesAventuriers.add(new VueAventurier("Delf1", "Messager"));
        vuesAventuriers.add(new VueAventurier("Juan", "Ingenieur"));

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




    public void deplacer(int idTuile){
        getAventurierCourant().setPosition(grille.getTuileById(idTuile));
    }

    public void assecher(int idTuile){
        grille.getTuileById(idTuile).setEtat(Etat.SECHE);
    }
	
    @Override
    public void update(Observable o, Object arg){
        Message m = (Message)arg;
        MessageId mi = (MessageId)arg;
        MessageTuiles mt = (MessageTuiles)arg;

        switch(m.getType()){
            case BTN_DEPLACER:  //clic sur le bouton déplacer
                getAventurierCourant().seDeplacer(grille);
                break;
            case BTN_ASSECHER:  //clic sur le bouton assecher
                getAventurierCourant().assecher(grille);
                break;
            case BTN_SPECIAL:   //clic sur le bouton spécial

                break;
            case BTN_PASSER:    //clic sur le bouton passer

                break;
            case SELECT_DEPLACEMENT:   //fenêtre de selection de la tuile pour le déplacement
                typeAction = 0;	

                break;
            case SELECT_ASSECHER:      //fenêtre de selection de la tuile pour l'assèchement
                typeAction = 1;


                break;
            case SELECT_SPECIAL:       //fenêtre de selection de la tuile pour l'action spéciale
                typeAction = 2;

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
            case ACTION:
                    actionSuivante();
                    break;
        }
    }
}	