package sandarena.partie.effet.effetbuff;

import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffVal extends EffetBuf {
    protected int val;

    public EffetBuffVal(int val, EffetBuf chaine) {
        super(chaine);
        this.val = val;
    }

    public int modif(int val){
        return val + this.val;
    }
}
