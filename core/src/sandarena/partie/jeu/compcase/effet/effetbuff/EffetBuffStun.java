package sandarena.partie.jeu.compcase.effet.effetbuff;

import java.util.ArrayList;

/**
 * Created by Guillaume on 17/06/2015.
 */
public class EffetBuffStun extends sandarena.partie.jeu.compcase.effet.EffetBuf {

    public EffetBuffStun(String nom,int duree, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
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
