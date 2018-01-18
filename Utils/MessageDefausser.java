/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author juan
 */
public class MessageDefausser extends Message{
    private CarteTirage carte;
    private Pion pion;
    public MessageDefausser(CarteTirage c, Pion p){
	super(MessageType.DEFAUSSER);
	carte = c;
        pion = p;
    }
    public CarteTirage getCarte(){
	return carte;
    }
    public Pion getPion(){
        return pion;
    }
}