package sandarena.partie.effet.effetbuff.effetbufftype;

import java.util.ArrayList;

import sandarena.partie.effet.CompetenceToEffet;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.effetbuff.EffetBuffType;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffTypeDefense extends EffetBuffType {
    public EffetBuffTypeDefense(String nom,int type, EffetBuf chaine) {
        super(nom,type, chaine);
    }
    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        if (typeCond != -1){
            retour.add("Se d�fend des attaques re�ues de type "+ CompetenceToEffet.switchtype(typeCond));
        }else {
            retour.add("Se d�fend de toutes les attaques re�ues");
        }
        retour.add("Par le type "+CompetenceToEffet.switchtype(type));
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
