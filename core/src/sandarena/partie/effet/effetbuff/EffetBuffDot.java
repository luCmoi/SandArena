package sandarena.partie.effet.effetbuff;

import java.util.ArrayList;

import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDot extends EffetBuf {
    private int degat;

    public EffetBuffDot(String nom,int degat, EffetBuf chaine) {
        super(nom, chaine);
        this.degat = degat;
    }

    public void inflige(){
        getContainer().inflige(degat);
    }

    @Override
    public boolean isBenefique() {
        return false;
    }

    public ArrayList<String> toStrings(){
        ArrayList<String> retour = super.toStrings();
        retour.add("Inflige "+degat+" dommages par tour");
        if (getDuree()>0){
            retour.add("Dure "+getDuree()+" tours");
        }
        return retour;
    }
}
