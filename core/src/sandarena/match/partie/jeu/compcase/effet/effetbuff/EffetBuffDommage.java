package sandarena.match.partie.jeu.compcase.effet.effetbuff;

import sandarena.match.partie.jeu.compcase.effet.EffetBuf;
import sandarena.match.partie.jeu.compcase.effet.EffetBufGroup;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDommage extends EffetBuf {
    int val;

    public EffetBuffDommage(String nom,int val, EffetBufGroup group) {
        super(nom, group);
        this.val = val;
    }

    public void setContainer(sandarena.match.partie.jeu.compcase.PersonnageIG container, boolean dispelable){
        super.setContainer(container, dispelable);
        container.inflige(val);
    }

    @Override
    public boolean isBenefique() {
        return true;
    }


}
