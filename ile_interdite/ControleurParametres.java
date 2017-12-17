/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

import Vues.Commandes;
import Vues.VueParametres;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author damien
 */
public class ControleurParametres implements Observer{
    private VueParametres view = null ;
    
    public void addView(VueParametres aView){
        this.view = aView;
        aView.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Commandes) {
            if ((Commandes) arg == Commandes.ANNULER) {
                System.out.println("prout");
                view.hide();
            } else {
                view.hide();
            }
        }
    }
    
}
