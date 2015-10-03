package sandarena.partie.jeu.compcase.effet.effetbuff;

import sandarena.partie.jeu.compcase.PersonnageIG;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDommage extends sandarena.partie.jeu.compcase.effet.EffetBuf {
    int val;

    public EffetBuffDommage(String nom,int val, sandarena.partie.jeu.compcase.effet.EffetBufGroup group) {
        super(nom, group);
        this.val = val;
    }

    public void setContainer(PersonnageIG container, boolean dispelable){
        super.setContainer(container, dispelable);
        container.inflige(val);
    }

    @Override
    public boolean isBenefique() {
        return true;
    }


}
