package sandarena.partie.jeu.compcase.effet.effetbuff;

import java.util.ArrayList;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDot extends sandarena.partie.jeu.compcase.effet.EffetBuf {
    private int degat;

    public EffetBuffDot(String nom,int degat, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
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
