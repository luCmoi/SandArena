/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.effet.EffetDeclencheur;

/**
 * @author Guillaume
 */
public class CompetenceDeclencheurEffet extends CompetencePassive {
    public static final int FIN = 9999;
    private int typedeclencheur;
    private int cible;
    private ArrayList<Double> infodeclencheur = new ArrayList<Double>();
    private int typeEffet;
    private ArrayList<Double> infoEffet = new ArrayList<Double>();

    public CompetenceDeclencheurEffet(int type, int typedeclencheur, int cible, double... donnees) {
        super(type);
        this.typedeclencheur = typedeclencheur;
        this.cible = cible;
        int i = 0;
        while (donnees[i] != FIN) {
            infodeclencheur.add(donnees[i]);
            i++;
        }
        i++;
        typeEffet = (int) donnees[i];
        i++;
        for (; i < donnees.length; i++) {
            infoEffet.add(donnees[i]);
        }
    }

    public EffetDeclencheur enJeu() {
        Double[] infos = infodeclencheur.toArray(new Double[infodeclencheur.size()]);
        Double[] infos2 = infoEffet.toArray(new Double[infoEffet.size()]);
        EffetDeclencheur tmp = new EffetDeclencheur(typedeclencheur, cible, infos);
        tmp.addEffet(typeEffet, infos2);
        return tmp;
    }


}
