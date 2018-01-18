/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;

/**
 *
 * @author damien
 */
public class MessageParametre extends Message{
    private ArrayList<String> noms;
    private int index;
            
    public MessageParametre(MessageType t, ArrayList<String> noms, int index){
	super(t);
	this.noms = noms;
        this.index = index;
    }
    
    public ArrayList<String> getNoms(){
	return noms;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
