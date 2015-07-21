package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.donnee.CompXML;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.partie.effet.CompetenceToEffet;

public class CompetenceBuffActif extends CompetenceActive {

    public CompetenceBuffActif(CompXML.CompLance attaque ) {
        super(attaque);
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        retour.addAll(CompetenceToEffet.toStrings(this));
        retour.addAll(super.toStrings());
        return retour;
    }

}
