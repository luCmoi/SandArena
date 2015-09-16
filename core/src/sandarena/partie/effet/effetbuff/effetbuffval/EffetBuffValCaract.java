package sandarena.partie.effet.effetbuff.effetbuffval;

import java.util.ArrayList;

import sandarena.partie.effet.CompetenceToEffet;
import sandarena.partie.effet.EffetBufGroup;
import sandarena.partie.effet.effetbuff.EffetBuffVal;

/**
 * Created by lucmo on 16/09/2015.
 */
public class EffetBuffValCaract extends EffetBuffVal{
    public EffetBuffValCaract(String nom, int val, EffetBufGroup group) {
        super(nom,val,group);
    }

    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        retour.add("Modifie de  "+val);
        if (typeCond != -1){
            retour.add("Sur la caracteristique "+ CompetenceToEffet.switchtype(typeCond));
        }
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
