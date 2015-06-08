/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandarena.joueur.competence.active;

import sandarena.joueur.competence.CompetenceActive;

/**
 * @author Guillaume
 */

public class CompetenceSoin extends CompetenceActive {

    public CompetenceSoin(int type, int recharge, int utilisation, int porte, int portemin, int zone) {
        super(type, recharge, utilisation, porte, portemin, zone);
    }

}
