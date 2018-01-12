/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generateurs;

/**
 *
 * @author claryd
 */
import java.util.ArrayList;
import Utils.*;
import Modele.*;
import java.util.Collections;

public class GrilleAleatoire extends Generateur{
    
    private ArrayList<TypeTuile> tuilesMelangees;
    private TypeTuile[] tuilesValeurs;
    private int[] tuilesInterdites = {0,1,4,5,6,11,24,29,30}; //valeur de tuiles nulles
    
    public void generate(Grille g){
        
        g.setSize(6);
//        tuilesInterdites = new int[9];
        tuilesValeurs = new TypeTuile[24]; 
        tuilesValeurs = TypeTuile.values();    //on recupere les differents types de tuile dans un tableau (obligatoirement car la methode .values() renvoie un tableau
        recupTuiles(); //on met ces types dans une arraylist
        Collections.shuffle(tuilesMelangees);  //on melange les types
        
        for(int i = 0 ; i < 36 ; i++){        //parcourt la grille pour la remplir
          for(int j = 0 ; j < 9 ; j++){       //parcourt la liste de tuiles interdites
              if(i == tuilesInterdites[j]){   //si la tuile est interdite on l'instancie en tant que tel avec la methode addEmpty
                  g.addEmpty();        
              }
              else{                           //sinon on ajoute une tuile d'un certain type à la grille, on retire cette tuile de la liste puis on re mélange la liste  
                  
                g.add(tuilesMelangees.get(0), Etat.SECHE);   
                tuilesMelangees.remove(0);
                Collections.shuffle(tuilesMelangees);
                 
              }
          }
        }   
                
           
        
        
    }
    
    public void recupTuiles(){
       
            for(int i = 0 ; i < 24 ; i++){
                 for(TypeTuile t : tuilesValeurs){
                 
                     tuilesMelangees.add(t);
                 
                 }    
            }
           
        
    }
    
    public void attribuerTuiles(){
        
    }
    
    
}
