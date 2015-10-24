package sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbufftype;

import java.util.ArrayList;

import sandarena.match.partie.jeu.compcase.effet.CompetenceToEffet;
import sandarena.match.partie.jeu.compcase.effet.EffetBufGroup;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffType;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffTypeDefense extends EffetBuffType {
    public EffetBuffTypeDefense(String nom,int type, EffetBufGroup group) {
        super(nom,type, group);
    }
    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        if (typeCond != -1){
            retour.add("Se d�fend des attaques re�ues de type "+ CompetenceToEffet.switchtype(typeCond));
        }else {
            retour.add("Se d�fend de toutes les attaques re�ues");
        }
        retour.add("Par le type "+ CompetenceToEffet.switchtype(type));
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
