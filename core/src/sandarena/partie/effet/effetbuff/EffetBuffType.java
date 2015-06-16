package sandarena.partie.effet.effetbuff;

import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffType extends EffetBuf {
    protected int type;

    public EffetBuffType(int type, EffetBuf chaine) {
        super(chaine);
        this.type = type;
    }
    public int modif(int type){
        return this.type;
    }
}
