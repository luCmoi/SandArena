/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sandarena.partie.effet;

import sandarena.partie.Case;
import sandarena.partie.compcase.PersonnageIG;

/**
 *
 * @author Guillaume
 */
public class EffetAttaque {
    public int type;
    
    public EffetAttaque(int type){
        this.type = type;
    }
    
    public void lance(Case attaquant, Case defenseur){
       /*Rajouter les check de tous les effets sur la case receuveuse et celle qui envoi
        ainsi que les check de buff de personnage qui pourront modifier leffet ou autre
        */ 
    }
}
