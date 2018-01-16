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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class GrilleAleatoire extends Generateur{
    
    private ArrayList<TypeTuile> tuilesMelangees;
    private HashSet<Integer> tuilesInterdites; //indexes des tuiles nulles
    
    //génére un plateau de jeu de manière aléatoire
    public void generate(Grille g){
        
        g.setSize(6);
        tuilesInterdites = new HashSet(Arrays.asList(0,1,4,5,6,11,24,29,30,31,34,35));
        tuilesMelangees = new ArrayList<>(Arrays.asList(TypeTuile.values())); //on recupere les differents types de tuile
        Collections.shuffle(tuilesMelangees);  //on melange les tuiles
        int indexMelangees = 0;


        for(int i = 0 ; i < g.length() ; i++){ //parcourt la grille pour la remplir

          if(tuilesInterdites.contains(i)){   //si la tuile est interdite on l'instancie en tant que tel avec la methode addEmpty
            g.addEmpty();        
          }
          else{     //sinon on ajoute une tuile d'un certain type à la grille
            g.add(tuilesMelangees.get(indexMelangees), Etat.SECHE);   
            indexMelangees++;
          }
        }   
                
           
        
        
    }
    
    public void attribuerTuiles(){
        
    }
    
    
}
