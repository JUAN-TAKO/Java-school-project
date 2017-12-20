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
import java.util.Collections;
import java.util.HashSet;

public class Controleur implements Observer{
    private VueParametres vueParametres; //paramètres de début de partie (nb de joueurs, noms etc)
	
    private Grille grille; 
    private ArrayList<Aventurier> aventuriers; //liste des aventuriers
    private ArrayList<VueAventurier> vuesAventuriers; //liste des vues associées aux aventuriers
    private VueSelection selection;
    private int indexAventurierCourant; 
    private int action; //nombre d'actions effectuées par le joueur courant
    private int tour; //nombre de tours de jeu
    private boolean jokerIngenieur;
    private Message lastMessage;  //sauvegarde du dernier message reçu par le controleur
    
    public Controleur(){
        vuesAventuriers = new ArrayList<>();
        aventuriers = new ArrayList<Aventurier>();

        vueParametres = new VueParametres();
        vueParametres.addObserver(this);

        Generateur g = new GrillePredefinie();
        
        grille = new Grille(g);
        
        tour = 0;
        indexAventurierCourant = 0;
        action = 0;
        jokerIngenieur = false;
    }
	
    public void start(){
        vueParametres.afficher();
    }
    
    public Aventurier getAventurierCourant(){
        return aventuriers.get(indexAventurierCourant);
    }
    public VueAventurier getVueAventurierCourant(){
        return vuesAventuriers.get(indexAventurierCourant);
    }

    public void actionSuivante(){ //gere le nombre d'actions de l'aventurier courant et le passage au joueur suivant si le joueur a effectué ses trois actions
        action++;
        if(action >= 3){
            aventurierSuivant();
        }
    }
    public void aventurierSuivant(){
        action = 0;
        jokerIngenieur = false;
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
        getVueAventurierCourant().setActive(true);
    }

    public void tourSuivant(){
        tour++;
        indexAventurierCourant = 0; //on revient au premier aventurier pour commencer un nouveau tour de jeu
    }
    public void afficherSelection(ArrayList<TypeTuile> typeTuiles, ArrayList<String> coordsTuiles){
        selection = new VueSelection(typeTuiles, coordsTuiles);
        selection.addObserver(this);
        selection.afficher();
    }
    
    public void deplacer(TypeTuile t){
        getAventurierCourant().setPosition(grille.getTuileByType(t));
        getVueAventurierCourant().setPosition(getAventurierCourant().getPosition().getNom() + " (" + getAventurierCourant().getPosition().getX() + " ; " + getAventurierCourant().getPosition().getY() + ")");
    }
    public void assecher(TypeTuile t){
        grille.getTuileByType(t).setEtat(Etat.SECHE);
    }
    
    @Override
    public void update(Observable o, Object arg){ //gère la réception des messages des vues aventuriers
        Message m = (Message)arg;
        ArrayList<Tuile> l;
        ArrayList<TypeTuile> typeTuiles;
        ArrayList<String> coordsTuiles;
        
        
        switch(m.getType()){
            case DEPLACER:  //clic sur le bouton déplacer
                l = getAventurierCourant().getTuilesAccessiblesDeplacement(grille);
                typeTuiles = new ArrayList<>();
                coordsTuiles = new ArrayList<>();
                for(Tuile t : l){
                    typeTuiles.add(t.getType());
                    coordsTuiles.add(" (" + t.getX() + " ; " + t.getY() + ")");
                }
                afficherSelection(typeTuiles, coordsTuiles);
                
                break;
            case ASSECHER:  //clic sur le bouton assecher
                l = getAventurierCourant().getTuilesAccessiblesAssechement(grille);
                typeTuiles = new ArrayList<>();
                coordsTuiles = new ArrayList<>();
                for(Tuile t : l){
                    typeTuiles.add(t.getType());
                    coordsTuiles.add(" (" + t.getX() + " ; " + t.getY() + ")");
                }
                
                afficherSelection(typeTuiles, coordsTuiles);
                
                
                
                break;
            case SPECIAL:   //clic sur le bouton spécial
                
                
                break;
            case PASSER:    //clic sur le bouton passer
                aventurierSuivant();
                break;

            case VALIDER_SELECTION:   //effectuer l'action sur la case selectionnée
                MessageTuile mt = (MessageTuile)arg; //interprète le message reçu comme un message tuile 
                boolean b = (getAventurierCourant() instanceof Ingenieur);
                
                switch(lastMessage.getType()){
                    case DEPLACER:
                        deplacer(mt.getTuile());
                        jokerIngenieur = false;
                        actionSuivante();
                        break;	
                    case ASSECHER:
                        assecher(mt.getTuile());
                        if(!jokerIngenieur){
                            actionSuivante();
                        }
                        jokerIngenieur = (b && !jokerIngenieur);
                        break;
                    case SPECIAL:
                        //jokerIngenieur = false;
                        break;    
                }
                selection.hide();
                break;
                
            case ANNULER_SELECTION:
                selection.hide();
             
                break;
                
            case QUITTER: 
                vueParametres.hide();
                break;
            case VALIDER_PARAMETRES:
                MessageNoms mn = (MessageNoms)arg;
                int randomIndex;
                ArrayList<Integer> indexes = new ArrayList<>();
                for(int i = 0; i < 6; i++){
                    indexes.add(i);
                }
                Collections.shuffle(indexes);
                int i = 0;
                System.out.println(mn.getNoms().size());
                for(String nom : mn.getNoms()){
                    switch(indexes.get(i)){
                        case 0:
                            aventuriers.add(new Explorateur(nom));
                            break;
                        case 1:
                            aventuriers.add(new Ingenieur(nom));
                            break;
                        case 2:
                            aventuriers.add(new Messager(nom));
                            break;
                        case 3:
                            aventuriers.add(new Navigateur(nom));
                            break;    
                        case 4:
                            aventuriers.add(new Pilote(nom));
                            break;
                        case 5:
                            aventuriers.add(new Plongeur(nom));
                            break;
                    }
                    Aventurier a = aventuriers.get(aventuriers.size()-1);
                    a.setPosition(grille.getTuileByType(a.getTuileDepart()));
                    VueAventurier v = new VueAventurier(a.getNom(), a.getNomRole(), a.getColor());
                    v.setPosition(a.getPosition().getNom() + " (" + a.getPosition().getX() + " ; " + a.getPosition().getY() + ")");
                    v.addObserver(this);
                    vuesAventuriers.add(v);
                    
                    i++;
                }
                vueParametres.hide();
                for(VueAventurier v : vuesAventuriers){
                    v.afficher();
                }
                afficherAventurier();
                break;
        }
        if(m.getType() != MessageType.VALIDER_SELECTION){
          lastMessage = m;  
        }
    }
    
    public static void main(String [] args) {
        Controleur c = new Controleur();
        c.start();
        System.out.println("test");
   }
}	