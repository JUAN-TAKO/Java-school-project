/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

/**
 *
 * @author damien
 */
public enum nombreJoueur {
    DEUX("2"),
    TROIS("3"),
    QUATRE("4");
    
    private final String name;
    
    nombreJoueur(String name){
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}