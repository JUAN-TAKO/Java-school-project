package Messages;

import Modele.*;
import java.util.ArrayList;

public class MessageTuiles extends Message{
	TypeTuile tuile;
	public MessageTuiles(MessageType t, TypeTuile tuile){
		super(t);
		this.tuile = tuile;
	}
	public TypeTuile getTuile(){
		return tuile;
	}
}