package sandarena.partie.effet.effetbuff;

import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDommage extends EffetBuf{
    int val;

    public EffetBuffDommage(String nom,int val, EffetBuf chaine) {
        super(nom, chaine);
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
