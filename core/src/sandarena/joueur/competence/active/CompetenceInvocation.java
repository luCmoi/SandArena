/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sandarena.joueur.competence.active;

import sandarena.joueur.competence.CompetenceActive;

/**
 *
 * @author Guillaume
 */
public class CompetenceInvocation  extends CompetenceActive{

    public CompetenceInvocation(int type, int recharge, int utilisation,int porte) {
        super(type, recharge, utilisation,porte);
    }
    
}
