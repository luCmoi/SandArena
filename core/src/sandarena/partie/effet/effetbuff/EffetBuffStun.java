package sandarena.partie.effet.effetbuff;

import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 17/06/2015.
 */
public class EffetBuffStun extends EffetBuf{
    private int duree;

    public EffetBuffStun(int duree, EffetBuf chaine) {
        super(chaine);
        this.duree = duree;
    }

    public int getDuree() {
        return duree;
    }

    @Override
    public boolean isBenefique() {
        return false;
    }
}
