

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.effet.CompetenceToEffet;

public class CompetenceBuff extends CompetencePassive {

    private int typeBuff;
    private int val;
    private int[] donnee = null;
    public CompetenceBuff(int type,int typeBuff, int val, int... donnee) {
        super(type);
        this.typeBuff = typeBuff;
        this.val = val;
        if (donnee.length > 0) {
            this.donnee = donnee;
        }
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        retour.addAll(CompetenceToEffet.toStrings(this));
        return retour;
    }

    public int getTypeBuff() {
        return typeBuff;
    }

    public int getVal() {
        return val;
    }

    public int[] getDonnee() {
        return donnee;
    }
}
