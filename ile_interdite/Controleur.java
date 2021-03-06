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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

public class Controleur implements Observer{
    private VueDebut vueDebut;
    private VueParametres vueParametres; //paramètres de début de partie (nb de joueurs, noms etc)    
    private VueConfirm vueConfirm;
    private VueFinale vueFinale;
    private VueJeu vueJeu;
    private VueRegles vueRegle;
    private VuePion vuePion;
    
    boolean parametre, finale, jeu, regle = false;
    boolean debut = true;
    
    
    private Grille grille;  //plateau de jeu
    private ArrayList<Aventurier> aventuriers; //liste des aventuriers
    private HashMap<Pion, Aventurier> aventuriersByPion;
    

    private TypeTuile tuileContexte;
    
    private CarteTirage carteContexte;
    private Aventurier aventurierCarteContexte;
    
    private int indexAventurierCourant; 
    private Integer action; //nombre d'actions effectuées par le joueur courant
    private int tour; //nombre de tours de jeu
    private boolean jokerIngenieur; //égal à true si l'ingénieur a déjà asseché une case pour cette action
    
    private int[] cartesPourNiveau = {2,2,3,3,3,4,4,5,5,0,0}; //nombre de cartes a piocher en fonction du niveau de l'eau
    private int niveauEau;
    private int niveauInitial;
    
    private LinkedList<TypeTuile> pileInondation; //pile des cartes a piocher
    //LinkedList : comme les ArrayList mais la structure interne est différente, elle permet de supprimer des éléments plus facilement mais il faut un itérateur pour la parcourir 
    private ListIterator iteratorInondation; //iterateur sur la carte a piocher dans la pile, toutes les cartes avant cet iterateur seront considérés comme la défausse
    //Iterateur : équivalent des index mais pour les LinkedList
    
    //private LinkedList<CarteTirage> pileTirage; //pile des cartes tirage à piocher
    //private ListIterator iteratorTirage;
    
    private int[] defausseTirage = new int[7];
    private int[] piocheTirage = new int[7];
    
    private ArrayList<Boolean> tresorsRecoltes; 
    
    private ArrayList<Tuile> tuilesAssechables;
    private ArrayList<Tuile> tuilesAccessibles;
    private ArrayList<Tuile> tuilesSpeciales;
    
    public Controleur(){
        initialiserJeu();
    }
    public void initialiserJeu(){
        aventuriers = new ArrayList<>();
        aventuriersByPion = new HashMap<>();
        pileInondation = new LinkedList<>(Arrays.asList(TypeTuile.values())); //On initialise la pile de cartes avec toutes les cartes possibles
        iteratorInondation = pileInondation.listIterator(); //on met l'iterateur au début de la pile (pas de défausse)
        tresorsRecoltes = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            tresorsRecoltes.add(false);
        }
        piocheTirage[0] = 5;
        piocheTirage[1] = 5;
        piocheTirage[2] = 5;
        piocheTirage[3] = 5;
        piocheTirage[4] = 3;
        piocheTirage[5] = 2;
        piocheTirage[6] = 3;
        //de même pour la pile de cartes Tirage
        
        vueDebut = new VueDebut();
        vueDebut.addObserver(this);
        start();
        
        //Le generateur construit la grille
        Generateur g = new GrilleAleatoire();
        
        grille = new Grille(g);
        
        tour = 0;
        indexAventurierCourant = 0;
        tuilesAssechables = new ArrayList<>();
        tuilesAccessibles = new ArrayList<>();
        tuilesSpeciales = new ArrayList<>();
        action = -1;
    }
    //reformer la pile de cartes inondation à partir de la défausse
    public void reinitialiserPileInondation(){
        if(iteratorInondation.nextIndex() != 0){
            ArrayList<TypeTuile> defausse = new ArrayList<>(pileInondation.subList(0, iteratorInondation.nextIndex() - 1)); //on récupère une sous-liste de 0 à l'iterateur (la défausse)
            Collections.shuffle(defausse); //on la mélange
            iteratorInondation = pileInondation.listIterator(); //on remet l'iterateur au début (on remet les cartes sur la pile)
        }
    }
    //tirage automatique d'autant de cartes inondation que nécessaire (par rapport au niveau d'eau)
    public void piocherInondation(int n){
        
        for(int i = 0; i < n; i++){
            Tuile t = grille.getTuileByType((TypeTuile)iteratorInondation.next()); //on tire une carte, la place dans la défausse (méthode next()) et on récupère la tuile correspondante

            if(t.getEtat() == Etat.SECHE){ //si elle est sèche, on l'inonde
                t.setEtat(Etat.INONDEE);
                vueJeu.setEtatTuile(grille.getIndexTuile(t), Etat.INONDEE);
            }
            else{                         //sinon c'est qu'elle est déjà inondee (elle ne peut pas être coulée parce qu'on enlève les cartes des tuiles coulées)
                removeTuile(t); //on la coule
                iteratorInondation.remove();   //on retire la carte qui vient d'être tirée de la pile
            }
            if(!iteratorInondation.hasNext()){ //on reinitialise la pile si on arrive a la fin
                reinitialiserPileInondation();
            }
        }
    }
    public boolean tresorRecuperable(){
        return
                (grille.getTuileByType(TypeTuile.CAVERNE_BRASIER).getEtat() == Etat.COULEE && grille.getTuileByType(TypeTuile.CAVERNE_OMBRES).getEtat() == Etat.COULEE) ||
                (grille.getTuileByType(TypeTuile.JARDIN_HURLEMENTS).getEtat() == Etat.COULEE && grille.getTuileByType(TypeTuile.JARDIN_MURMURES).getEtat() == Etat.COULEE) ||
                (grille.getTuileByType(TypeTuile.PALAIS_CORAIL).getEtat() == Etat.COULEE && grille.getTuileByType(TypeTuile.PALAIS_MAREES).getEtat() == Etat.COULEE) ||
                (grille.getTuileByType(TypeTuile.TEMPLE_LUNE).getEtat() == Etat.COULEE && grille.getTuileByType(TypeTuile.TEMPLE_SOLEIL).getEtat() == Etat.COULEE) ||
                (grille.getTuileByType(TypeTuile.HELIPORT).getEtat() == Etat.COULEE);
    }
    public ArrayList<Aventurier> getAventuriersSurTuile(Tuile t){
        HashMap<Integer, ArrayList<Aventurier>> positionsAv = new HashMap<>();

        for(int j = 0; j < aventuriers.size(); j++){
            Aventurier a = aventuriers.get(j);
            Integer index = grille.getIndexTuile(a.getPosition());
            ArrayList<Aventurier> aa = positionsAv.get(index);
            if(aa == null){
                aa = new ArrayList<>();
                positionsAv.put(index, aa);
            }
            aa.add(a);
        }
        return positionsAv.get(grille.getIndexTuile(t));
    }
    public boolean deplacementUrgence(Tuile t){
        ArrayList<Aventurier> avs = getAventuriersSurTuile(t);
        if(avs != null){
            for(Aventurier av : avs){
                ArrayList<Tuile> tad = av.getTuilesAccessiblesDeplacement(grille);
                if(tad.isEmpty()){
                    return true;
                }
                else{
                    Collections.shuffle(tad);
                    clearPionsVue();
                    av.setPosition(tad.get(0));
                    checkGagne();
                    updatePionsVue();
                }
            }
            recalculerDeplacement();
        }
        return false;
    }
    public void removeTuile(Tuile t){
        t.setEtat(Etat.COULEE);
        boolean perdu = false;
        vueJeu.setEtatTuile(grille.getIndexTuile(t), Etat.COULEE);
        perdu |= tresorRecuperable();
        perdu |= deplacementUrgence(t);
        if(perdu){
            fin();
        }
        
    }
    public void fin(){
        if(!finale){
            vueFinale = new VueFinale(tresorsRecoltes);
            vueFinale.addObserver(this);
            finale = true;
            vueFinale.afficher();
            vueJeu.hide();
        }
    }
    //pioche d'une carte tirage de la meme maniere
    public void piocherTirage(boolean init){
        
        Aventurier av = getAventurierCourant();
        int randomNum;
        int nb;
        boolean monte = false;
        CarteTirage[] pioche = new CarteTirage[2];
        for(int i = 0; i < 2; i++){
            do{
                randomNum = (int)(Math.random() * 7);
                nb = piocheTirage[randomNum];
                if(nb != 0){
                    piocheTirage[randomNum]--;
                    pioche[i] = CarteTirage.values()[randomNum];
                }
            }while(nb == 0 || (randomNum == 6 && init));
            
            if(pioche[i] == CarteTirage.MONTEE_DES_EAUX){
                niveauEau++;
                if(niveauEau > 8){
                    fin();
                    return;
                }
                monte = true;
                addCarte(pioche[i], defausseTirage);
            }
            else{
                av.addCarte(pioche[i]);
            }
            if(getNbCartes(piocheTirage) == 0){
                reinitialiserTirage();
            }
        }
        
        if(monte){
            vueJeu.setNiveau(niveauEau);
            reinitialiserPileInondation();
        }
        updateCartes(av);
    }
    public void reinitialiserTirage(){
        moveAll(defausseTirage, piocheTirage);
    }
    public void start(){
        vueDebut.afficher(); //ouvre la fenêtre des paramètres (inscription des joueurs)
    }
    
    //renvoie l'aventurier courant
    public Aventurier getAventurierCourant(){
        return aventuriers.get(indexAventurierCourant);
    }
    
    //incrémentation du nombre d'actions de l'aventurier courant et le passage au joueur suivant si le joueur a effectué ses trois actions
    public void actionSuivante(){ 
        
        action++;
        int nbActMax = getAventurierCourant().getNbActionMax();
        if(action >= nbActMax){
            aventurierSuivant();
        }
        nbActMax = getAventurierCourant().getNbActionMax();
        vueJeu.setNbAction(((Integer)(nbActMax - action)).toString());
    }
    //passe a l'aventurier suivant et au tour suivant si tous les aventuriers ont joués
    public void aventurierSuivant(){
        
        piocherTirage(false);
        action = 0;
        jokerIngenieur = false;
        indexAventurierCourant++;
        if(indexAventurierCourant >= aventuriers.size()){
            tourSuivant();
        }
        
        vueJeu.setJoueurActif(indexAventurierCourant);
        int nbActMax = getAventurierCourant().getNbActionMax();
        vueJeu.setNbAction(((Integer)(nbActMax - action)).toString());
        
        piocherInondation(cartesPourNiveau[niveauEau]);
        
        
        //selectAventurier();
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
//        selection = new VueSelection(typeTuiles, coordsTuiles, returnMessage);
//        selection.addObserver(this);
//        selection.afficher();
    }
    //déplace l'aventurier courant sur la tuile de type t
    public void deplacer(TypeTuile t){
        if(t != null){
            getAventurierCourant().setPosition(grille.getTuileByType(t));
            checkGagne();
            recalculerDeplacement();
        }
    }
    public void checkGagne(){
        ArrayList<Aventurier> aa = getAventuriersSurTuile(grille.getTuileByType(TypeTuile.HELIPORT));
        if(aa != null && aa.size() > aventuriers.size()){
            Boolean b = true;
            for(Boolean b2 : tresorsRecoltes){
                if(b2 == false)
                    b = false;
            }
            if(b){
                for(Aventurier a : aventuriers){
                    if(a.getCartes(CarteTirage.HELICOPTERE) > 0){
                        fin();
                    }
                }
            }
        }
    }
    //assèche la tuile de type t
    public void assecher(TypeTuile t){
        grille.getTuileByType(t).setEtat(Etat.SECHE);
        vueJeu.setEtatTuile(grille.getIndexTuile(grille.getTuileByType(tuileContexte)), Etat.SECHE);
        
        recalculerAssechement();
    }
    public void addCarte(CarteTirage c, int[] tab){
        tab[c.ordinal()]++;
    }
    public void removeCarte(CarteTirage c, int[] tab){
        removeCarte(c, tab, 1);
    }
    public void moveCarte(CarteTirage c, int[] tab1, int[] tab2){
        if(getCartes(c, tab1) > 0){
            removeCarte(c, tab1);
            addCarte(c, tab2);
        }
    }
    public void moveAll(int[] tab1, int[] tab2){
        for(int i = 0; i < tab1.length; i++){
            tab2[i] += tab1[i];
            tab1[i] = 0;
        }
    }
    public void removeCarte(CarteTirage c, int[] tab, int nbCartes){
        tab[c.ordinal()] -= nbCartes;
        if(tab[c.ordinal()] < 0){
            tab[c.ordinal()] = 0;
        }
    }
    public int getCartes(CarteTirage c, int[] tab){
        return tab[c.ordinal()];
    }
    public int getNbCartes(int[] tab){
        int nb = 0;
        for(int i = 0; i < tab.length; i++){
            nb += tab[i];
        }
        return nb;
    }
    //met a jour les cartes d'un aventurier dans la vue
    public void updateCartes(Aventurier av){
        
        ArrayList<CarteTirage> listeCartes = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < av.getCartes(CarteTirage.values()[i]); j++){
                listeCartes.add(CarteTirage.values()[i]);
            }
        }
        System.out.println(listeCartes.size());
        vueJeu.updateCartes(aventuriers.indexOf(av), listeCartes);
    }
    public Aventurier getAventurierDefausse(){
        for(Aventurier a : aventuriers){
            if(a.getNbCartes() > 5){
                return a;
            }
        }
        return null;
    }
    public void afficherActionsPossibles(Tuile t){
        
        ArrayList<Boolean> actionsPossibles = new ArrayList<>();
        Aventurier av = getAventurierDefausse();
        for(int i = 0; i < 7; i++){
            actionsPossibles.add(false);
        }
        if(t != null){
            if(av == null){
                Tresor tr = t.getTresor();
                actionsPossibles.set(0, tuilesAccessibles.contains(t) && tuileContexte != null); //se deplacer
                actionsPossibles.set(1, tuilesAssechables.contains(t) && tuileContexte != null); //assecher
                actionsPossibles.set(2, tr != null && getAventurierCourant().getCartes(CarteTirage.values()[tr.ordinal()]) >= 4); // recup tresor
            }
            actionsPossibles.set(3, carteContexte == CarteTirage.HELICOPTERE || (carteContexte == CarteTirage.SABLE && t.getEtat() == Etat.INONDEE)); // utiliser carte
        }
        ArrayList<Aventurier> avProches = getAventuriersSurTuile(getAventurierCourant().getPosition());
        actionsPossibles.set(4, getAventurierCourant() == aventurierCarteContexte && carteContexte != null && carteContexte.ordinal() < 5 && avProches != null && avProches.size() > 1); // donner carte
        actionsPossibles.set(5, carteContexte != null); // defausser
        vueJeu.choisirEtatsBoutons(actionsPossibles);
    }
    //gère la réception des messages des vues
    @Override
    public void update(Observable o, Object arg){
        Message m = (Message)arg;
        boolean b;
                
        switch(m.getType()){
            case CLIC_TUILE:
                MessageTuile mct = (MessageTuile)m;
                tuileContexte = mct.getTuile();
                recalculerTuiles();
                afficherActionsPossibles(grille.getTuileByType(tuileContexte));
                
                break;
                
            case DEFAUSSER:
                aventurierCarteContexte.removeCarte(carteContexte);
                addCarte(carteContexte, defausseTirage);
                carteContexte = null;
                afficherActionsPossibles(grille.getTuileByType(tuileContexte));
                updateCartes(aventurierCarteContexte);
                break;
            case DEPLACER:  //clic sur le bouton déplacer
//               
                if(jokerIngenieur){ //si l'ingénieur avait asséché une tuile, on compte cette action
                    actionSuivante();
                    break;
                }
                clearPionsVue();
                deplacer(tuileContexte);
                updatePionsVue();
                Tuile tmp = grille.getTuileByType(tuileContexte);
                tuileContexte = null;
                afficherActionsPossibles(tmp);
                actionSuivante();
                jokerIngenieur = false;
                
                break;
            case CLIC_CARTE:
                MessageCarte msc = (MessageCarte)m;
                carteContexte = msc.getCarte();
                aventurierCarteContexte = aventuriersByPion.get(msc.getPion());
                afficherActionsPossibles(grille.getTuileByType(tuileContexte));
                break;
            case ASSECHER:  //clic sur le bouton assecher
                b = (getAventurierCourant() instanceof Ingenieur); // b = true si l'aventurier courant est un ingénieur    
                if(!b){
                    jokerIngenieur = false;
                }
                if(tuileContexte != null){
                    assecher(tuileContexte);
                }
                //TypeTuile tmp = tuileContexte;
                tuileContexte = null;
                afficherActionsPossibles(grille.getTuileByType(tuileContexte));
                if(!b || jokerIngenieur){ //si l'aventurier n'est pas un ingénieur ou si l'ingénieur a déjà asséché une tuile, on compte une action. le premier assèchement de l'ingénieur ne seras donc pas compté comme une action
                    actionSuivante();
                }
                jokerIngenieur = (b && !jokerIngenieur); 
                
                break;
                
            case DONNER_CARTE:   //clic sur le bouton donner carte
                Aventurier av = getAventurierCourant();
                ArrayList<Aventurier> avProches = getAventuriersSurTuile(av.getPosition());
                avProches.remove(av);
                if(avProches.size() > 1){
                    ArrayList<Pion> pions = new ArrayList<>();
                    ArrayList<String> noms = new ArrayList<>();
                    for(Aventurier a : avProches){
                        pions.add(a.getPion());
                        noms.add(a.getNom());
                    }
                    vuePion = new VuePion(pions, noms);
                    vuePion.addObserver(this);
                    vuePion.afficher();
                }
                else{
                    av.removeCarte(carteContexte);
                    avProches.get(0).addCarte(carteContexte);
                    updateCartes(av);
                    updateCartes(avProches.get(0));
                    carteContexte = null;
                    afficherActionsPossibles(null);
                }              
                break;
            case SELECT_PION:
                MessagePion msp = (MessagePion)m;
                getAventurierCourant().removeCarte(carteContexte);
                Aventurier avs = aventuriersByPion.get(msp.getPion());
                avs.addCarte(carteContexte);
                updateCartes(getAventurierCourant());
                updateCartes(avs);
                carteContexte = null;
                afficherActionsPossibles(null);
                vuePion.hide();
                actionSuivante();
                
                break;
            case RECUP_TRESOR:
                Tresor tr = tuileContexte.getTresor();
                getAventurierCourant().removeCarte(CarteTirage.values()[tr.ordinal()], 4);
                tresorsRecoltes.set(tr.ordinal(), true);
                vueJeu.setTresor(tr.ordinal());
                updateCartes(getAventurierCourant());
                actionSuivante();
            
            case UTILISER_CARTE : //clic sur le bouton utiliser carte
                if(carteContexte == CarteTirage.HELICOPTERE){
                    ArrayList<Aventurier> ast = getAventuriersSurTuile(aventurierCarteContexte.getPosition());
                    clearPionsVue();
                    for(Aventurier a : ast){
                        a.setPosition(grille.getTuileByType(tuileContexte));
                        checkGagne();
                    }
                    updatePionsVue();
                    recalculerDeplacement();
                    aventurierCarteContexte.removeCarte(carteContexte);
                }else{
                    assecher(tuileContexte);
                    aventurierCarteContexte.removeCarte(carteContexte);
                }
                updateCartes(aventurierCarteContexte);
                carteContexte = null;
                aventurierCarteContexte = null;
                tuileContexte = null;
                afficherActionsPossibles(null);
                break;
                
            case PASSER:    //clic sur le bouton passer
                aventurierSuivant();
                break;
                   
            case VOIR_INONDATION:
                ArrayList<String> defausseInondationStr = new ArrayList<>();
                ListIterator it = pileInondation.listIterator();
                while(it.nextIndex() < iteratorInondation.nextIndex()){
                    defausseInondationStr.add(((TypeTuile)it.next()).getImagePath().replaceAll("tuiles", "cartes"));
                }
                VueDefausse vdi = new VueDefausse(defausseInondationStr);
                vdi.afficher();
                break;
                
            case VOIR_TIRAGE:
                ArrayList<String> defausseTirageStr = new ArrayList<>();
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j < defausseTirage[i]; j++){
                        defausseTirageStr.add(CarteTirage.values()[i].getImage());
                    }
                }
                VueDefausse vdt = new VueDefausse(defausseTirageStr);
                vdt.afficher();
                break;
            case JOUER :
                vueDebut.hide();
                debut = false;
                
                vueParametres = new VueParametres();
                vueParametres.addObserver(this);
                vueParametres.afficher();
                parametre = true;
                               
                break;
                
            case REGLES :
                vueRegle = new VueRegles();
                vueRegle.addObserver(this);
                vueRegle.afficher();             
                
                break;
                
            case QUITTER:
                vueConfirm = new VueConfirm();
                vueConfirm.addObserver(this);
                vueConfirm.afficher();
                
                if(debut){
                    vueDebut.desactive();
                }else if(parametre){
                    vueParametres.desactive();
                }else if(jeu){
                    //vueJeu.visible(jeu);
                    jeu = false;
                }
                
                break;      
                
                
                
            case OUI :
                if(vueConfirm != null)
                    vueConfirm.hide();
                
                if(debut){
                    vueDebut.hide();
                    debut = false;
                }else if(parametre){
                    vueParametres.hide();
                    parametre = false;
                }else if(!jeu){
                    vueJeu.hide();
                    if(finale){
                        vueFinale.hide();
                    }
                    jeu = false;
                }
                
                break;
                
            case NON :
                vueConfirm.hide();
                if(debut){
                    vueDebut.active();
                }else if(parametre){
                    vueParametres.active();
                }else if(!jeu){
                    vueJeu.visible(jeu);
                    jeu = true;
                }
                
                
                break;
                
      
            case GAGNE_PERDU:
                
                
                vueFinale = new VueFinale(tresorsRecoltes);
                vueFinale.addObserver(this);
                vueFinale.afficher();
                finale = true;
                break;
                
            case RETOUR_MENU:
                vueFinale.hide();
                finale = false;
                initialiserJeu();
                break;              
                
            case VALIDER_PARAMETRES: //réception des noms des joueurs
                MessageParametre mp = (MessageParametre)arg; //interprète le message reçu comme un message contenant une liste de noms 
                
                niveauInitial = mp.getNiveau();
                niveauEau = mp.getNiveau();
                ArrayList<String> noms = mp.getNoms();
                ArrayList<Pion> pions = new ArrayList<>();
                
                
                ArrayList<Integer> indexes = new ArrayList<>();
                //on remplis un tableau avec les nombres de 0 à 5
                for(int i = 0; i < 6; i++){
                    indexes.add(i);
                }
                //on mélange le tableau
                Collections.shuffle(indexes);
                int i = 0;
                //a chaque joueur seras attribué un role en fonction de la valeur du tableau a sa position
                for(String nom : noms){
                    switch(indexes.get(i)){
                        case 0:
                            Explorateur ex = new Explorateur(nom);
                            aventuriers.add(ex);
                            aventuriersByPion.put(ex.getPion(), ex);
                            pions.add(Pion.VERT);
                            break;
                        case 1:
                            
                            aventuriers.add(new Ingenieur(nom));
                            pions.add(Pion.ROUGE);
                            break;
                        case 2:
                            aventuriers.add(new Messager(nom));
                            pions.add(Pion.GRIS);
                            break;
                        case 3:
                            aventuriers.add(new Navigateur(nom));
                            pions.add(Pion.JAUNE);
                            break;    
                        case 4:
                            aventuriers.add(new Pilote(nom));
                            pions.add(Pion.BLEU);
                            break;
                        case 5:
                            aventuriers.add(new Plongeur(nom));
                            pions.add(Pion.NOIR);
                            break;
                    }
                    Aventurier a = aventuriers.get(aventuriers.size()-1);
                    a.setPosition(grille.getTuileByType(a.getTuileDepart())); //on positionne les aventuriers sur leur tuile de départ
                    
//                    roles.add(a.getNomRole());
//                    couleurs.add(a.getPion().getColor());
                   
                    i++;
                }
//                vueAventuriers = new VueAventuriers(noms, roles, couleurs);
//                vueAventuriers.addObserver(this);
                
                ArrayList<TypeTuile> types = new ArrayList<>();
                for(int j = 0; j < grille.length(); j++){
                    Tuile t = grille.get(j);
                    if(t == null){
                        types.add(null);
                    }else{
                        types.add(t.getType());
                    }
                }
                
                vueParametres.hide();
                vueJeu = new VueJeu(noms, pions, types);
                vueJeu.setNiveau(niveauEau);
                
                //vueJeu.addObserver(this);
                vueJeu.setObserver(this);                
                vueJeu.afficher();
                parametre = false;
                jeu = true;
                
                initialiserPartie();
                break;
        }
    }
    public void clearPionsVue(){
        HashSet<Integer> positionsAv = new HashSet<>();

        for(int j = 0; j < aventuriers.size(); j++){
            Aventurier a = aventuriers.get(j);
            Integer index = grille.getIndexTuile(a.getPosition());
            positionsAv.add(index);
        }        
        for(Integer index : positionsAv){
            vueJeu.setAventurier(index, null);
        }
    }
    public void updatePionsVue(){
        HashMap<Integer, ArrayList<Pion>> positionsAv = new HashMap<>();

        for(int j = 0; j < aventuriers.size(); j++){
            Aventurier a = aventuriers.get(j);
            Integer index = grille.getIndexTuile(a.getPosition());
            ArrayList<Pion> aa = positionsAv.get(index);
            if(aa == null){
                aa = new ArrayList<>();
                positionsAv.put(index, aa);
            }
            aa.add(a.getPion());
           
            //vueAventuriers.setPosition(j, a.getPosition().getNom() + " (" + a.getPosition().getX() + " ; " + a.getPosition().getY() + ")");
            
        }
        
        for(Integer index : positionsAv.keySet()){
            vueJeu.setAventurier(index, positionsAv.get(index));
        }
    }
    
    public void initialiserPartie(){
        
        for(Aventurier a : aventuriers){
            aventuriersByPion.put(a.getPion(), a);
        }
        //on met a jour la position des aventuriers dans la vue aventuriers
        updatePionsVue();
        //vueAventuriers.setPosition(j, a.getPosition().getNom() + " (" + a.getPosition().getX() + " ; " + a.getPosition().getY() + ")");
 
        Collections.shuffle(pileInondation);
        piocherInondation(6);
        
        //selectAventurier();
        //aventurierSuivant();
        actionSuivante();
        recalculerTuiles();
        
        ArrayList<Boolean> actionsPossibles = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            actionsPossibles.add(false);
        }
        for(int i = 0; i < aventuriers.size(); i++){
            indexAventurierCourant = i;
            piocherTirage(true);
        }
        indexAventurierCourant = 0;
        //niveauEau = niveauInitial;
        //reinitialiserTirage();
        
        vueJeu.setJoueurActif(0);
        vueJeu.choisirEtatsBoutons(actionsPossibles);
        
        
    }
 
    public void recalculerTuiles(){
        recalculerDeplacement();
        recalculerAssechement();
        recalculerSpecial();
    }
    public void recalculerDeplacement(){
        tuilesAccessibles = getAventurierCourant().getTuilesAccessiblesDeplacement(grille);
    }
    public void recalculerAssechement(){
        tuilesAssechables = getAventurierCourant().getTuilesAccessiblesAssechement(grille);
    }
    public void recalculerSpecial(){
        tuilesSpeciales = getAventurierCourant().getTuilesSpeciales(grille);
    }
    
    //fonction main
    public static void main(String [] args) {
        Controleur c = new Controleur();
        c.start();
   }
}	
