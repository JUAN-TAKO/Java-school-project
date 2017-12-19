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
public class MessageNoms extends Message{
    private ArrayList<String> noms;
    public MessageNoms(MessageType t, ArrayList<String> noms){
	super(t);
	this.noms = noms;
    }
    public ArrayList<String> getTuile(){
	return noms;
    }
}
