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
    private int niveau;
            
    public MessageParametre(MessageType t, ArrayList<String> noms, int index){
	super(t);
	this.noms = noms;
        this.niveau = index;
    }
    public ArrayList<String> getNoms(){
	return noms;
    }

    /**
     * @return the index
     */
    public int getNiveau() {
        return niveau;
    }
}
