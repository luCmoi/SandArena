package sandarena.partie.jeu.compcase.effet.effetbuff.effetbuffval;

import java.util.ArrayList;

/**
 * Created by lucmo on 16/09/2015.
 */
public class EffetBuffValCaract extends sandarena.partie.jeu.compcase.effet.effetbuff.EffetBuffVal {
    public EffetBuffValCaract(String nom, int val, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
        super(nom,val,group);
    }

    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        retour.add("Modifie de  "+val);
        if (typeCond != -1){
            retour.add("Sur la caracteristique "+ sandarena.partie.jeu.compcase.effet.CompetenceToEffet.switchtype(typeCond));
        }
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
