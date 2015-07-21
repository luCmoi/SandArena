

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.effet.CompetenceToEffet;

public class CompetenceBuff extends CompetencePassive {

    private int typeBuff;
    private int val;
    private int condtype;
    private int condduree;

    public CompetenceBuff(int typeBuff, int val, int condtype, int condduree) {
        this.typeBuff = typeBuff;
        this.val = val;
        this.condtype = condtype;
        this.condduree = condduree;
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

    public int getCondtype() {
        return condtype;
    }

    public int getCondduree() {
        return condduree;
    }


}
