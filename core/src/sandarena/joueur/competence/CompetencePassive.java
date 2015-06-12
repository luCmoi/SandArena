/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sandarena.joueur.competence;

import java.util.ArrayList;

/**
 * @author Guillaume
 */
public abstract class CompetencePassive extends Competence {

    public CompetencePassive(int type) {
        super(type);
    }

    @Override
    public ArrayList<String> toStrings() {
        return null;
    }
}
