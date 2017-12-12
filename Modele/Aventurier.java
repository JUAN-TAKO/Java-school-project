package Modele;

public abstract class Aventurier{
	private Tuile position;
	private String nom;
	private String nomRole;
        
        public Aventurier(String nom, String nomRole){
            this.nom = nom;
            this.nomRole = nomRole;
        }
        
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

    /**
     * @return the position
     */
    public Tuile getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Tuile position) {
        this.position = position;
    }
}