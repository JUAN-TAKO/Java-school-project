package Utils;

import Modele.*;
import java.util.ArrayList;

public class MessageTuiles extends Message{
	ArrayList<Tuile> tuiles;
	public MessageTuiles(MessageType t, ArrayList<Tuile> tuiles){
		super(t);
		this.tuiles = tuiles;
	}
	public ArrayList<Tuile> getTuiles(){
		return tuiles;
	}
}