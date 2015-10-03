package sandarena.partie.jeu.compcase.effet.effetbuff.effetbufftype;

import java.util.ArrayList;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffTypeDefense extends sandarena.partie.jeu.compcase.effet.effetbuff.EffetBuffType {
    public EffetBuffTypeDefense(String nom,int type, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
        super(nom,type, group);
    }
    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        if (typeCond != -1){
            retour.add("Se d�fend des attaques re�ues de type "+ sandarena.partie.jeu.compcase.effet.CompetenceToEffet.switchtype(typeCond));
        }else {
            retour.add("Se d�fend de toutes les attaques re�ues");
        }
        retour.add("Par le type "+ sandarena.partie.jeu.compcase.effet.CompetenceToEffet.switchtype(type));
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
