import java.utils.ArrayList;
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
		Aventurier av = aventuriers.at(indexAventurierCourant);
		vueAventurier.afficher(av.getNom(), av.getRole());
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
	
	public void update(Observable o, Object arg){
		Message m = (Message)arg;
		MessageCoords mc = (MessageCoords)arg;
		switch(m.getAction()){
			case Action.DEPLACER:
				
				break;
			case Action.ASSECHER:
				
				break;
			case Action.SPECIAL:
				
				break;
			case Action.PASSER:
				
				break;
			
		}
	}
	
}