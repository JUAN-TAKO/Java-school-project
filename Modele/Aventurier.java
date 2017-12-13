package Modele;

public abstract class Aventurier extends Observable{
	private Tuile position;
	private String nom;
	private String nomRole;
        
        public Aventurier(String nom, String nomRole){
            this.nom = nom;
            this.nomRole = nomRole;
        }
        
	public void seDeplacer(Grille g){
		ArrayList<Tuile> tuilesAccessibles;
		tuilesAccessibles.add(g.at(getPosition().getX(), getPosition().getY()+1));
		tuilesAccessibles.add(g.at(getPosition().getX(), getPosition().getY()-1));
		tuilesAccessibles.add(g.at(getPosition().getX()+1, getPosition().getY()));
		tuilesAccessibles.add(g.at(getPosition().getX()-1, getPosition().getY()));

		setChanged();
		MessageTuiles m = new MessageTuiles(MessageType.TUILES_DEPLACEMENT, tuilesAccessibles);
		notifyObservers(m);
		clearChanged();
	}

	public void assecher(Grille g){
		ArrayList<Tuile> tuilesAccessibles;
		Tuile t;
		t = g.at(position.getX(), position.getY()+1);
		if(t.getEtat() == Etat.INNONDEE)
		tuilesAccessibles.add(t);	
		
		t = g.at(position.getX(), position.getY()-1);
		if(t.getEtat() == Etat.INNONDEE)
		tuilesAccessibles.add(t);
		
		t = g.at(position.getX()+1, position.getY());
		if(t.getEtat() == Etat.INNONDEE)
		tuilesAccessibles.add(t);
		
		t = g.at(position.getX()-1, position.getY());
		if(t.getEtat() == Etat.INNONDEE)
			tuilesAccessibles.add(t);
		
		setChanged();
		MessageTuiles m = new MessageTuiles(MessageType.TUILES_ASSECHER, tuilesAccessibles);
		notifyObservers(m);
		clearChanged();
	}
	
	public void actionSpeciale(Grille g){}
        
	
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