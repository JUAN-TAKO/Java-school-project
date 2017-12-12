package ile_interdite;

import Generateurs.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Modele.*;
import Vues.*;
import Messages.*;

public class Controleur implements Observer{
	
	private VueAventurier vueAventurier;
	private VueParametres vueParametres;
	
	private Grille grille;
	private ArrayList<Aventurier> aventuriers;
	private int indexAventurierCourant;
	private int tour;
	
	public Controleur(){
		vueAventurier = new VueAventurier();
		vueParametres = new VueParametres();
		
		Generateur g = new GrillePredefinie();
		grille = new Grille(g);
		tour = 0;
	}
	
	public void init(){
		
	}
	
	public void start(){
		init();
	}
	
	public void afficherAventurier(){
		Aventurier av = aventuriers.get(indexAventurierCourant);
		vueAventurier.afficher(av.getNom(), av.getNomRole());
	}
	
	public void aventurierSuivant(){
		indexAventurierCourant++;
		if(indexAventurierCourant >= aventuriers.size()){
			tourSuivant();
		}
		afficherAventurier();
		
	}
	
	public void tourSuivant(){
		tour++;
		indexAventurierCourant = 0;
	}
	
        
	@Override
        public void update(Observable o, Object arg){
		Message m = (Message)arg;
		MessageCoords mc = (MessageCoords)arg;
		switch(m.getAction()){
			case DEPLACER:
				
				break;
			case ASSECHER:
				
				break;
			case SPECIAL:
				
				break;
			case PASSER:
				
				break;
			
		}
	}
}