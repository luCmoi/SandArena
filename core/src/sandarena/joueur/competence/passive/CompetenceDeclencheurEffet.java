

package sandarena.joueur.competence.passive;

import java.util.ArrayList;

import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.jeu.compcase.effet.CompetenceToEffet;

public class CompetenceDeclencheurEffet extends CompetencePassive {
    private byte typedeclencheur;
    private byte cible;
    private Competence effet;

    public CompetenceDeclencheurEffet( byte typedeclencheur, byte cible, Competence effet) {
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
