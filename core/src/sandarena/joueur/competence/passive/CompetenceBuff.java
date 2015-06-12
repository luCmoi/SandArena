/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;

/**
 * @author Guillaume
 */
public class CompetenceBuff extends CompetencePassive {
    protected int typeBuff;
    protected int val;
    protected double[] donnee;
    protected  String toStrings;
    public CompetenceBuff(String toStrings, int type,int typeBuff, int val, double... donnee) {
        super(type);
        this.toStrings=toStrings;
        this.typeBuff = typeBuff;
        this.val = val;
        this.donnee = donnee;
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        retour.add(this.toStrings);
        return retour;
    }
}
