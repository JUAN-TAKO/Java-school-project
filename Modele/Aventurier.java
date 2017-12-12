package Modele;

public abstract class Aventurier{
	private Tuile position;
	private String nom;
	private String nomRole;
	
	public ResultatAction seDeplacer(Grille g){
		return ResultatAction.NON_IMPLEMENTE;
	}
	
	public ResultatAction assecher(Grille g){
		return ResultatAction.NON_IMPLEMENTE;
	}
	
	public ResultatAction actionSpeciale(Grille g){
		return ResultatAction.NON_IMPLEMENTE;
	}
	
	public String getNom(){
		return nom;
	}
	public String getNomRole(){
		return nomRole;
	}
}