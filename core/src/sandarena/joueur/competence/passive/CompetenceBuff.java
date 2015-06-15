

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;

public class CompetenceBuff extends CompetencePassive {
    private int typeBuff;
    private int val;
    private double[] donnee;
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

    public int getTypeBuff() {
        return typeBuff;
    }

    public int getVal() {
        return val;
    }

    public double[] getDonnee() {
        return donnee;
    }
}
