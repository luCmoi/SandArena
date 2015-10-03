package sandarena.partie.jeu.compcase.effet.effetbuff;

import java.util.ArrayList;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffType extends sandarena.partie.jeu.compcase.effet.EffetBuf {
    protected int type;
    protected int typeCond = -1;


    public EffetBuffType(String nom,int type, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
        super(nom, group);
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

    @Override
    public boolean isBenefique() {
        return true;
    }

    public ArrayList<String> toStrings(){
        return super.toStrings();
    }
}
