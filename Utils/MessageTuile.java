package Utils;

import Modele.*;
import java.util.ArrayList;

public class MessageTuile extends Message{
    private TypeTuile tuile;
    
    public MessageTuile(MessageType t, TypeTuile tuile){
	super(t);
	this.tuile = tuile;
    }
    
    public TypeTuile getTuile(){
	return tuile;
    }
}