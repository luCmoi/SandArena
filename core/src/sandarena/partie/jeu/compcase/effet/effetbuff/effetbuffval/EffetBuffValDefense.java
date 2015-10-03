package sandarena.partie.jeu.compcase.effet.effetbuff.effetbuffval;

import java.util.ArrayList;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffValDefense extends sandarena.partie.jeu.compcase.effet.effetbuff.EffetBuffVal {
    public EffetBuffValDefense(String nom, int val, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
        super(nom, val, group);
    }
    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        retour.add("Modifie la defense de  "+val);
        if (typeCond != -1){
            retour.add("Sur les attaques de type "+ sandarena.partie.jeu.compcase.effet.CompetenceToEffet.switchtype(typeCond));
        }
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
