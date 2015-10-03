package sandarena.partie.jeu.compcase.effet.effetbuff;

import java.util.ArrayList;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffVal extends sandarena.partie.jeu.compcase.effet.EffetBuf {
    protected int val;
    protected int typeCond = -1;

    public EffetBuffVal(String nom,int val, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
        super(nom,group);
        this.val = val;
    }

    public int modif(int val,int type){
        if (this.getTypeCond() == -1) {
            return val + this.val;
        }else if (this.getTypeCond() == type){
            return val + this.val;
        }else{
            return val;
        }
    }
    public int modif(int val){
        return val + this.val;
    }

    public int getTypeCond() {
        return typeCond;
    }

    public void setTypeCond(int type) {
        this.typeCond = type;
    }

    @Override
    public boolean isBenefique() {
        return (val>=0);
    }

    public ArrayList<String> toStrings(){
        return super.toStrings();
    }

}
