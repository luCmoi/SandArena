package sandarena.partie.effet.effetbuff;

import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffType extends EffetBuf {
    protected int type;
    private int typeCond = -1;


    public EffetBuffType(int type, EffetBuf chaine) {
        super(chaine);
        this.type = type;
    }

    public int modif(int type){
        if (this.getTypeCond() == -1) {
            return this.type;
        }else if (this.getTypeCond() == type){
            return this.type;
        }else{
            return type;
        }
    }

    public int getTypeCond() {
        return typeCond;
    }

    public void setTypeCond(int type) {
        this.typeCond = type;
    }
}
