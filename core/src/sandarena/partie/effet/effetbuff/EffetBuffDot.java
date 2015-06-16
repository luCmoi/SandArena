package sandarena.partie.effet.effetbuff;

import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDot extends EffetBuf {
    private int degat;

    public EffetBuffDot(int degat, EffetBuf chaine) {
        super(chaine);
        this.degat = degat;
    }

    public void inflige(){
        getContainer().inflige(degat);
    }
}
