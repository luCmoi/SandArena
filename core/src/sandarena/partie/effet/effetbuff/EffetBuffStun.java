package sandarena.partie.effet.effetbuff;

import java.util.ArrayList;

import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.EffetBufGroup;

/**
 * Created by Guillaume on 17/06/2015.
 */
public class EffetBuffStun extends EffetBuf{

    public EffetBuffStun(String nom,int duree, EffetBufGroup group) {
        super(nom, group);
        this.setDuree(duree);
    }


    @Override
    public boolean isBenefique() {
        return false;
    }

    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        retour.add("Assomm�");
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
