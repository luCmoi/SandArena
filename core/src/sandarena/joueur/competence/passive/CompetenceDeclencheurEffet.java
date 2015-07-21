

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

    public CompetenceDeclencheurEffet( int typedeclencheur, int cible, Competence effet) {
        this.typedeclencheur = typedeclencheur;
        this.cible = cible;
        this.effet = effet;
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

    public Competence getEffet() {
        return effet;
    }

}
