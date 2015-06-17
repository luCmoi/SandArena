package sandarena.partie.effet.effetbuff;

import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffVal extends EffetBuf {
    protected int val;
    private int type = -1;

    public EffetBuffVal(int val, EffetBuf chaine) {
        super(chaine);
        this.val = val;
    }

    public int modif(int val,int type){
        if (this.getType() == -1) {
            return val + this.val;
        }else if (this.getType() == type){
            return val + this.val;
        }else{
            return val;
        }
    }
    public int modif(int val){
        return val + this.val;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
