/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;

/**
 *
 * @author Guillaume
 */
public class CompetenceDeclencheurEffet extends CompetencePassive{
    public static final int FIN = 9999;
    public static final int BUFF = 0;
    private int typedeclencheur;
    private ArrayList<Double> infodeclencheur;
    private int typeEffet;
    private ArrayList<Double> infoEffet;

    public CompetenceDeclencheurEffet(int type, int typedeclencheur, double ... donnees) {
        super(type);
        this.typedeclencheur=typedeclencheur;
        int i = 0;
        while(donnees[i]!= FIN){
            infodeclencheur.add(donnees[i]);
            i++;
        }
        i++;
        int typeAction = (int)donnees[i];
        for(;i<donnees.length;i++){
            infoEffet.add(donnees[i]);
        }
    }
    
}
