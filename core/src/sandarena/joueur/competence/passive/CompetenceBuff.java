

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;
import sandarena.match.partie.jeu.compcase.effet.CompetenceToEffet;

public class CompetenceBuff extends CompetencePassive {

    private byte typeBuff;
    private byte val;
    private byte condtype;
    private byte condduree;

    public CompetenceBuff(byte typeBuff, byte val, byte condtype, byte condduree) {
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
