package ile_interdite;

import Generateurs.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Modele.*;
import Vues.*;
import Utils.*;
import Aventuriers.*;
import Cartes.*;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class Controleur implements Observer{
    private VueParametres vueParametres; //paramètres de début de partie (nb de joueurs, noms etc)
	
    private Grille grille;  //plateau de jeu
    private ArrayList<Aventurier> aventuriers; //liste des aventuriers
    private VueAventuriers vueAventuriers; //pour afficher les aventuriers
    private VueSelection selection;  //fenêtre de sélection de la tuile
    
    private int indexAventurierCourant; 
    private int action; //nombre d'actions effectuées par le joueur courant
    private int tour; //nombre de tours de jeu
    private boolean jokerIngenieur; //égal à true si l'ingénieur a déjà asseché une case pour cette action
    
    private LinkedList<TypeTuile> pileInnondation; //pile des cartes a piocher
    //LinkedList : comme les ArrayList mais la structure interne est différente, elle permet de supprimer des éléments plus facilement mais il faut un itérateur pour la parcourir 
    private ListIterator iteratorPile; //iterateur sur la carte a piocher dans la pile, toutes les cartes avant cet iterateur seront considérés comme la défausse
    //Iterateur : équivalent des index mais pour les LinkedList
    
    
    public Controleur(){
        aventuriers = new ArrayList<>();
        pileInnondation = new LinkedList<>(Arrays.asList(TypeTuile.values())); //On initialise la pile de cartes avec toutes les cartes possibles
        iteratorPile = pileInnondation.listIterator(); //ont met l'iterateur au début de la pile (pas de défausse)
        
        vueParametres = new VueParametres();
        vueParametres.addObserver(this);
        
        //Le generateur construit la grille
        Generateur g = new GrillePredefinie();
        
        grille = new Grille(g);
        
        tour = 0;
        indexAventurierCourant = 0;
        action = 0;
        jokerIngenieur = false;
    }
    public void reinitialiserPileInnondation(){
        ArrayList<TypeTuile> sub = new ArrayList<>(pileInnondation.subList(0, iteratorPile.nextIndex() - 1)); //on récupère une sous-liste de 0 à l'iterateur (la défausse)
        Collections.shuffle(sub); //on la mélange
        iteratorPile = pileInnondation.listIterator(); //on remet l'iterateur au début (on remet les cartes sur la pile)
    }
    public void tirerInnondation(){
        Tuile t = grille.getTuileByType((TypeTuile)iteratorPile.next()); //on tire une carte, la place dans la défausse (méthode next()) et on récupère la tuile correspondante
        if(t.getEtat() == Etat.SECHE){ //si elle est sèche, on l'innonde
            t.setEtat(Etat.INNONDEE);
        }else{                         //sinon c'est qu'elle est déjà innondee (elle ne peut pas être coulée parce qu'on enlève les cartes des tuiles coulées)
            t.setEtat(Etat.COULEE);  //on la coule
            iteratorPile.remove();   //on retire la carte qui vient d'être tirée de la pile
        }
    }
    public void start(){
        vueParametres.afficher(); //ouvre la fenêtre des paramètres (inscription des joueurs)
    }
    
    //renvoie l'aventurier courant
    public Aventurier getAventurierCourant(){
        return aventuriers.get(indexAventurierCourant);
    }
    
    //incrémentation du nombre d'actions de l'aventurier courant et le passage au joueur suivant si le joueur a effectué ses trois actions
    public void actionSuivante(){ 
        action++;
        if(action >= 3){
            aventurierSuivant();
        }
    }
    //passe a l'aventurier suivant et au tour suivant si tous les aventuriers ont joués
    public void aventurierSuivant(){
        action = 0;
        setBoutonsActives(true);
        jokerIngenieur = false;
        indexAventurierCourant++;
        if(indexAventurierCourant >= aventuriers.size()){
            tourSuivant();
        }
        selectAventurier();

    }
    
    //désactive les aventuriers dont ce n'est pas le tour, et active l'aventurier courant (dans la vue)
    public void selectAventurier(){ 
        vueAventuriers.setActive(indexAventurierCourant);
    }

    //incrémente le nombre de tours et reviens au début de la liste des aventuriers
    public void tourSuivant(){
        tour++;
        indexAventurierCourant = 0;
    }
    
    //ouvre une fenêtre de séléction de tuile
    public void afficherSelection(ArrayList<Tuile> listeTuiles, MessageType returnMessage){
        ArrayList<TypeTuile> typeTuiles = new ArrayList<>();
        ArrayList<String> coordsTuiles = new ArrayList<>();
        for(Tuile t : listeTuiles){
            typeTuiles.add(t.getType());
            coordsTuiles.add(" (" + t.getX() + " ; " + t.getY() + ")");
        }
        selection = new VueSelection(typeTuiles, coordsTuiles, returnMessage);
        selection.addObserver(this);
        selection.afficher();
    }
    //déplace l'aventurier courant sur la tuile de type t
    public void deplacer(TypeTuile t){
        getAventurierCourant().setPosition(grille.getTuileByType(t));
        vueAventuriers.setPosition(indexAventurierCourant, getAventurierCourant().getPosition().getNom() + " (" + getAventurierCourant().getPosition().getX() + " ; " + getAventurierCourant().getPosition().getY() + ")");
    }
    //assèche la tuile de type t
    public void assecher(TypeTuile t){
        grille.getTuileByType(t).setEtat(Etat.SECHE);
    }
    //active ou désactive tous les boutons de la vue aventuriers
    public void setBoutonsActives(boolean a){
        vueAventuriers.setBoutonsActives(a);
    }
    //active ou desactive certains boutons de la vue aventuriers
    public void setBoutonsActivesIngenieur(boolean a){
        vueAventuriers.setBoutonsActivesIngenieur(a);
    }
    
    //gère la réception des messages des vues
    @Override
    public void update(Observable o, Object arg){
        Message m = (Message)arg;
        
        ArrayList<Tuile> listeTuiles; //tableau temporaire pour stocker les tuiles accessibles 
        ArrayList<TypeTuile> typeTuiles; //tableau temporaire pour stocker les types des tuiles accéssibles (TypeTuile est un enum avec toutes les différentes tuiles)
        ArrayList<String> coordsTuiles; //on passeras les coordonées de la tuile sous forme de string a la vue Sélection
        
        boolean b = (getAventurierCourant() instanceof Ingenieur); // b = true si l'aventurier courant est un ingénieur    
        if(!b){
            jokerIngenieur = false;
        }
                
        switch(m.getType()){
            case DEPLACER:  //clic sur le bouton déplacer
                listeTuiles = getAventurierCourant().getTuilesAccessiblesDeplacement(grille);
                afficherSelection(listeTuiles, MessageType.CHOISIR_DEPLACEMENT);
                setBoutonsActives(false);
                
                break;
            case ASSECHER:  //clic sur le bouton assecher
                listeTuiles = getAventurierCourant().getTuilesAccessiblesAssechement(grille);
                afficherSelection(listeTuiles, MessageType.CHOISIR_ASSECHEMENT);
                setBoutonsActives(false);
                
                break;
            case SPECIAL:   //clic sur le bouton spécial, pas implémenté pour l'instant
                
                
                break;
            case PASSER:    //clic sur le bouton passer
                aventurierSuivant();
                break;
               
            case CHOISIR_DEPLACEMENT:
                MessageTuile mtd = (MessageTuile)arg; //interprète le message reçu comme un message contenant une tuile 
                if(jokerIngenieur){ //si l'ingénieur avait asséché une tuile, on compte cette action
                    actionSuivante();
                }
                deplacer(mtd.getTuile());
                actionSuivante();
                jokerIngenieur = false;
                setBoutonsActives(true);
                selection.hide(); //on cache la fenêtre de séléction
                break;
   
            case CHOISIR_ASSECHEMENT:
                MessageTuile mta = (MessageTuile)arg; //interprète le message reçu comme un message contenant une tuile 
                assecher(mta.getTuile());
                if(!b || jokerIngenieur){ //si l'aventurier n'est pas un ingénieur ou si l'ingénieur a déjà asséché une tuile, on compte une action. le premier assèchement de l'ingénieur ne seras donc pas compté comme une action
                    actionSuivante();
                }
                jokerIngenieur = (b && !jokerIngenieur); 

                if(jokerIngenieur && action == 2){ //désactive le bouton déplacer si l'ingénieur en est a sa dernière action et a déjà asséché une tuile
                    setBoutonsActives(false);
                }
                setBoutonsActives(true);
                selection.hide(); //on cache la fenêtre de séléction
                break;            
                
            case ANNULER_SELECTION: //clic sur le bouton annuler (ou fermeture) de la fenêtre de séléction
                selection.hide();
                setBoutonsActives(true);
                
                break;
                
            case QUITTER: 
                vueParametres.hide();
                break;
                
            case VALIDER_PARAMETRES: //réception des noms des joueurs
                MessageNoms mn = (MessageNoms)arg; //interprète le message reçu comme un message contenant une liste de noms 
                
                ArrayList<String> noms = mn.getNoms();
                ArrayList<String> roles = new ArrayList<>();
                ArrayList<Color> couleurs = new ArrayList<>();
                
                
                ArrayList<Integer> indexes = new ArrayList<>();
                //on remplis un tableau avec les nombres de 0 à 5
                for(int i = 0; i < 6; i++){
                    indexes.add(i);
                }
                //on mélange le tableau
                Collections.shuffle(indexes);
                int i = 0;
                System.out.println(mn.getNoms().size());
                //a chaque joueur seras attribué un role en fonction de la valeur du tableau a sa position
                for(String nom : noms){
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
                    a.setPosition(grille.getTuileByType(a.getTuileDepart())); //on positionne les aventuriers sur leur tuile de départ
                    roles.add(a.getNomRole());
                    couleurs.add(a.getPion().getColor());
                   
                    i++;
                }
                vueAventuriers = new VueAventuriers(noms, roles, couleurs);
                vueAventuriers.addObserver(this);
                vueParametres.hide();
                vueAventuriers.afficher();
                
                //on met a jour la position des aventuriers dans la vue aventuriers
                for(int j = 0; j < aventuriers.size(); j++){
                    Aventurier a = aventuriers.get(j);
                    vueAventuriers.setPosition(j, a.getPosition().getNom() + " (" + a.getPosition().getX() + " ; " + a.getPosition().getY() + ")");
                }
                selectAventurier();
                break;
        }
    }
    
    
    //fonction main
    public static void main(String [] args) {
        Controleur c = new Controleur();
        c.start();
   }
}	
