/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


public class MessagePion extends Message{
    private Pion pion;
    
    public MessagePion(MessageType m, Pion p){
	super(m);
        pion = p;
    }
    
    public Pion getPion(){
        return pion;
    }
}
