

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.effet.CompetenceToEffet;
import sandarena.partie.effet.EffetDeclencheur;

public class CompetenceDeclencheurEffet extends CompetencePassive {
    private int typedeclencheur;
    private int cible;
    private int[] donnee = null;

    public CompetenceDeclencheurEffet(int type, int typedeclencheur, int cible, int... donnee) {
        super(type);
        this.typedeclencheur = typedeclencheur;
        this.cible = cible;
        if (donnee.length > 0){
            this.donnee = donnee;
        }
    }


    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        retour.addAll(CompetenceToEffet.toStrings(this));
        return retour;
    }
}
