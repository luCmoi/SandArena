package sandarena.partie.effet.effetbuff;

import java.util.ArrayList;

import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 17/06/2015.
 */
public class EffetBuffStun extends EffetBuf{

    public EffetBuffStun(String nom,int duree, EffetBuf chaine) {
        super(nom, chaine);
        this.setDuree(duree);
    }


    @Override
    public boolean isBenefique() {
        return false;
    }

    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        retour.add("Assommé");
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
