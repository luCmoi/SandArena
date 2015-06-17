package sandarena.partie.effet;

import sandarena.partie.compcase.PersonnageIG;

//todo conditions
public class EffetBuf extends Effet{
    private PersonnageIG container;
    private EffetBuf chaine;

    public EffetBuf(EffetBuf chaine){
        this.setChaine(chaine);
    }

    public PersonnageIG getContainer() {
        return container;
    }

    public void setContainer(PersonnageIG container) {
        this.container = container;
        if(chaine != null){
            container.addBuf(chaine);
        }
    }

    public EffetBuf getChaine() {
        return chaine;
    }

    public void setChaine(EffetBuf chaine) {
        this.chaine = chaine;
    }

}
