package sandarena.match.partie.jeu.compcase.effet.effetbuff;

import java.util.ArrayList;

import sandarena.match.partie.jeu.compcase.effet.EffetBuf;
import sandarena.match.partie.jeu.compcase.effet.EffetBufGroup;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDot extends EffetBuf {
    private int degat;

    public EffetBuffDot(String nom,int degat, EffetBufGroup group) {
        super(nom, group);
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
