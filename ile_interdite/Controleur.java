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
	private int action;
	private int tour;
	
	public Controleur(){
		vueAventurier = new VueAventurier();
		vueParametres = new VueParametres();
		
		Generateur g = new GrillePredefinie();
		grille = new Grille(g);
		tour = 0;
		indexAventurierCourant = 0;
		action = 0;
	}
	
	public void init(){
		
	}
	
	public void start(){
		init();
	}
	public Aventurier getAventurierCourant(){
		return aventuriers.get(indexAventurierCourant);
	}

	public void afficherAventurier(){
		Aventurier av = getAventurierCourant();
		vueAventurier.afficher(av.getNom(), av.getNomRole());
	}
	public void actionSuivante(){
		action++;
		if(action > 3){
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
	
	public void tourSuivant(){
		tour++;
		indexAventurierCourant = 0;
	}
	
	public void deplacer(int idTuile){
		getAventurierCourant.setPosition(grille.getTuileById(idTuile));
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
				
			
				getAventurierCourant().setPosition(g.getTuileById(mi.getId()));
				break;
			case SELECT_ASSECHER:      //fenêtre de selection de la tuile pour l'assèchement

				

				g.getTuileById(mi.getId()).setEtat(Etat.SECHE);
				break;
			case SELECT_SPECIAL:       //fenêtre de selection de la tuile pour l'action spéciale

				break;
			case ACTION:
				actionSuivante();
				break;
		}
	}
}	