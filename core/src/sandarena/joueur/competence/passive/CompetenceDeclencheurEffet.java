

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.effet.EffetDeclencheur;

public class CompetenceDeclencheurEffet extends CompetencePassive {
    public static final int FIN = 9999;
    private int typedeclencheur;
    private int cible;
    private ArrayList<Double> infodeclencheur = new ArrayList<Double>();
    private int typeEffet;
    private ArrayList<Double> infoEffet = new ArrayList<Double>();
    private String toStrings;

    public CompetenceDeclencheurEffet(String toStrings, int type, int typedeclencheur, int cible, double... donnees) {
        super(type);
        this.toStrings=toStrings;
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
        //EffetDeclencheur tmp = new EffetDeclencheur(typedeclencheur, cible, infos);
        //tmp.addEffet(typeEffet, infos2);
        //return tmp;
        return null;
    }


    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        retour.add(this.toStrings);
        return retour;
    }
}
