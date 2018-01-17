package ile_interdite;

import Utils.CarteTirage;
import Generateurs.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Modele.*;
import Vues.*;
import Utils.*;
import Aventuriers.*;
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
    
    private int[] cartesPourNiveau = {2,2,3,3,3,4,4,5,5,0}; //nombre de cartes a piocher en fonction du niveau de l'eau
    private int niveauEau;
    
    private LinkedList<TypeTuile> pileInondation; //pile des cartes a piocher
    //LinkedList : comme les ArrayList mais la structure interne est différente, elle permet de supprimer des éléments plus facilement mais il faut un itérateur pour la parcourir 
    private ListIterator iteratorInondation; //iterateur sur la carte a piocher dans la pile, toutes les cartes avant cet iterateur seront considérés comme la défausse
    //Iterateur : équivalent des index mais pour les LinkedList
    
    private LinkedList<CarteTirage> pileTirage; //pile des cartes tirage à piocher
    private ListIterator iteratorTirage;
    
    
    public Controleur(){
        aventuriers = new ArrayList<>();
        pileInondation = new LinkedList<>(Arrays.asList(TypeTuile.values())); //On initialise la pile de cartes avec toutes les cartes possibles
        iteratorInondation = pileInondation.listIterator(); //on met l'iterateur au début de la pile (pas de défausse)
       
        //de même pour la pile de cartes Tirage
        pileTirage = new LinkedList<>();
        iteratorTirage = pileTirage.listIterator();
        
        vueParametres = new VueParametres();
        vueParametres.addObserver(this);
        
        //Le generateur construit la grille
        Generateur g = new GrilleAleatoire();
        
        grille = new Grille(g);
        
        tour = 0;
        indexAventurierCourant = 0;
        action = 0;
        jokerIngenieur = false;
    }
    
    //reformer la pile de cartes inondation à partir de la défausse
    public void reinitialiserPileInondation(){
        ArrayList<TypeTuile> defausse = new ArrayList<>(pileInondation.subList(0, iteratorInondation.nextIndex() - 1)); //on récupère une sous-liste de 0 à l'iterateur (la défausse)
        Collections.shuffle(defausse); //on la mélange
        iteratorInondation = pileInondation.listIterator(); //on remet l'iterateur au début (on remet les cartes sur la pile)
    }
    //tirage automatique d'autant de cartes inondation que nécessaire (par rapport au niveau d'eau)
    public void piocherInondation(int n){
        
        for(int i = 0; i < n; i++){
            Tuile t = grille.getTuileByType((TypeTuile)iteratorInondation.next()); //on tire une carte, la place dans la défausse (méthode next()) et on récupère la tuile correspondante

            if(t.getEtat() == Etat.SECHE){ //si elle est sèche, on l'inonde
                t.setEtat(Etat.INONDEE);
            }
            else{                         //sinon c'est qu'elle est déjà inondee (elle ne peut pas être coulée parce qu'on enlève les cartes des tuiles coulées)
                t.setEtat(Etat.COULEE);  //on la coule
                iteratorInondation.remove();   //on retire la carte qui vient d'être tirée de la pile
            }
            if(!iteratorInondation.hasNext()){ //on reinitialise la pile si on arrive a la fin
                reinitialiserPileInondation();
            }
        }
    }
    
    public void reinitialiserPileTirage(){
        
        ArrayList<CarteTirage> defausse = new ArrayList<>(pileTirage.subList(0, iteratorTirage.nextIndex()-1));
        Collections.shuffle(defausse);
        iteratorTirage = pileTirage.listIterator();
    }
    
    //pioche d'une carte tirage de la meme maniere
    public void piocherTirage(){
        
        CarteTirage t = (CarteTirage)iteratorTirage.next();
        
        if(t == CarteTirage.MONTEE_DES_EAUX){ //si la carte piochée est une carte montee des eaux 
            niveauEau++; //alors on incremente d'un le niveau d'eau
        }
        else{ 
            
            aventuriers.get(tour).addCarte(t); //sinon on l'ajoute à la collection de cartes de l'aventurier courant
        }
        
        if(!iteratorTirage.hasNext()){ //si on arrive à la fin de la pile alors on mélange la défausse et on la remet dans la pile
            reinitialiserPileTirage();
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
        ArrayList<Boolean> boolTresors = new ArrayList<>();
        boolean b;
        VueFinale vueFinale;
                
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
                b = (getAventurierCourant() instanceof Ingenieur); // b = true si l'aventurier courant est un ingénieur    
                if(!b){
                    jokerIngenieur = false;
                }
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
                
      
            case GAGNE_PERDU:
                
                boolTresors = vueJeu.getBoolTresors();  //recuperation de l'état des trésors (à créer)
                vueFinale = new VueFinale(boolTresors);
                break;
                
            case RETOUR_MENU:
                vueFinale.hide();
                vueParametres.afficher();
                break;
                

                
           
                
                
            case VALIDER_PARAMETRES: //réception des noms des joueurs
                System.out.println("test");
                MessageParametre mp = (MessageParametre)arg; //interprète le message reçu comme un message contenant une liste de noms 
                
                int difficulte = mp.getIndex();
                ArrayList<String> noms = mp.getNoms();
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
                System.out.println(mp.getNoms().size());
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
                    System.out.println(a);
                    System.out.println(vueAventuriers);
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
