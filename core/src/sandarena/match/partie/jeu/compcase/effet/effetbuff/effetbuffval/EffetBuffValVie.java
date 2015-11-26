package sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval;

import java.util.ArrayList;

import sandarena.match.partie.jeu.compcase.effet.CompetenceToEffet;
import sandarena.match.partie.jeu.compcase.effet.EffetBufGroup;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffVal;

/**
 * Created by lucmo on 24/11/2015.
 */
public class EffetBuffValVie extends EffetBuffVal {

    public EffetBuffValVie(String nom, int val, EffetBufGroup group) {
        super(nom, val, group);
    }

    public ArrayList<String> toStrings() {
        ArrayList<String> retour = super.toStrings();
        retour.add("Modifie la vie de  " + val);
        if (typeCond != -1) {
            retour.add("Sur les attaques de type " + CompetenceToEffet.switchtype(typeCond));
        }
        if (getDuree() > 0) {
            retour.add("Dure " + getDuree() + " tours");
        }
        return retour;
    }
}
