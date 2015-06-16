package sandarena.partie.effet.effetbuff;

import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.effet.EffetBuf;

/**
 * Created by Guillaume on 16/06/2015.
 */
public class EffetBuffDommage extends EffetBuf{
    int val;

    public EffetBuffDommage(int val, EffetBuf chaine) {
        super(chaine);
        this.val = val;
    }

    public void setContainer(PersonnageIG container){
        super.setContainer(container);
        container.inflige(val);
    }



}
