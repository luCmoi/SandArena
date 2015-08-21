package sandarena.partie.effet.effetbuff.effetbuffval;

import java.util.ArrayList;

import sandarena.partie.effet.CompetenceToEffet;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.effetbuff.EffetBuffVal;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffValVitesse extends EffetBuffVal {
    public EffetBuffValVitesse(String nom,int val, EffetBuf chaine) {
        super(nom,val, chaine);
    }
    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        retour.add("Modifie la vitesse de  "+val);
        if (typeCond != -1){
            retour.add("Sur les attaques de type "+ CompetenceToEffet.switchtype(typeCond));
        }
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
