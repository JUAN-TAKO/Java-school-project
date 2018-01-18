/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Observable;

/**
 *
 * @author juan
 */
public class CompositionObservable extends Observable{
    public CompositionObservable(){
        super();
    }
    public void setChanged(){
        super.setChanged();
    }
    public void notifyObservers(Object arg){
        super.notifyObservers(arg);
    }
    public void clearChanged(){
        super.clearChanged();
    }
}
