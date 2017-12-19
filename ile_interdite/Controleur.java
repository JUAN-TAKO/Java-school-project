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
    private VueParametres vueParametres; //paramètres de début de partie (nb de joueurs, noms etc)
	
    private Grille grille; 
    private ArrayList<Aventurier> aventuriers; //liste des aventuriers
    private ArrayList<VueAventurier> vuesAventuriers; //liste des vues associées aux aventuriers
        
    private int indexAventurierCourant; 
    private int action; //nombre d'actions effectuées par le joueur courant
    private int tour; //nombre de tours de jeu
    private Message lastMessage;  //sauvegarde du dernier message reçu par le controleur
    
    public Controleur(){
        vuesAventuriers = new ArrayList<>();
        
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


    public void actionSuivante(){ //gere le nombre d'actions de l'aventurier courant et le passage au joueur suivant si le joueur a effectué ses trois actions
        action++;
        if(action >= 3){
            aventurierSuivant();
        }
    }
    public void aventurierSuivant(){
        action = 0;
        indexAventurierCourant++;
        if(indexAventurierCourant >= aventuriers.size()){
            tourSuivant();
        }
        afficherAventurier();

    }
    public void afficherAventurier(){ //désactive les vues des aventuriers dont ce n'est pas le tour, et active celle de l'aventurier courant
        for(VueAventurier v : vuesAventuriers){
            v.setActive(false);
        }
        vuesAventuriers.get(indexAventurierCourant).setActive(true);
    }

    public void tourSuivant(){
        tour++;
        indexAventurierCourant = 0; //on revient au premier aventurier pour commencer un nouveau tour de jeu
    }

    @Override
    public void update(Observable o, Object arg){ //gère la réception des messages des vues aventuriers
        Message m = (Message)arg;
        MessageTuile mt = (MessageTuile)arg; //interprète le message reçu comme un message tuile 
        ArrayList<Tuile> l;
        ArrayList<TypeTuile> typeTuiles;
        
        switch(m.getType()){
            case DEPLACER:  //clic sur le bouton déplacer
                l = getAventurierCourant().getTuilesAccessiblesDeplacement(grille);
                typeTuiles = new ArrayList<>();
                for(Tuile t : l){
                    typeTuiles.add(t.getType());
                }
                
                vueSelection.afficher(typeTuiles);
                
                
                actionSuivante();
                break;
            case ASSECHER:  //clic sur le bouton assecher
                l = getAventurierCourant().getTuilesAccessiblesAssechement(grille);
                typeTuiles = new ArrayList<>();
                
                for(Tuile t : l){
                    typeTuiles.add(t.getType());
                }
                
                vueSelection.afficher(typeTuiles);
                
                if(getAventurierCourant() instanceof Ingenieur){
                    vueSelection.afficher(typeTuiles);
                }
                
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
        if(m.getType() != MessageType.VALIDER_SELECTION){
          lastMessage = m;  
        }
    }
}	