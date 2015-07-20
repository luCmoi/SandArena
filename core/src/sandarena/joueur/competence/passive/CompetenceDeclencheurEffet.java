

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.effet.CompetenceToEffet;
import sandarena.partie.effet.EffetDeclencheur;

public class CompetenceDeclencheurEffet extends CompetencePassive {
    private int typedeclencheur;
    private int cible;
    private Competence effet;
    private int[] donnee = null;

    public CompetenceDeclencheurEffet( int typedeclencheur, int cible, int... donnee) {
        this.typedeclencheur = typedeclencheur;
        this.cible = cible;
        if (donnee.length > 0){
            this.donnee = donnee;
        }
    }

    public CompetenceDeclencheurEffet( int typedeclencheur, int cible, Competence effet, int... donnee) {
        this.typedeclencheur = typedeclencheur;
        this.cible = cible;
        this.effet = effet;
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

    public int getCible() {
        return cible;
    }

    public int getTypedeclencheur() {
        return typedeclencheur;
    }

    public int[] getDonnee() {
        return donnee;
    }
}
