package sandarena.partie.effet;

import sandarena.partie.compcase.PersonnageIG;

//todo conditions
public abstract class EffetBuf extends Effet {
    private PersonnageIG container;
    private EffetBuf chaine;
    private int duree = -1;

    public EffetBuf(EffetBuf chaine) {
        this.setChaine(chaine);
    }

    public PersonnageIG getContainer() {
        return container;
    }

    public void setContainer(PersonnageIG container) {
        this.container = container;
        if (chaine != null) {
            container.addBuf(chaine);
        }
    }

    public abstract boolean isBenefique();

    public EffetBuf getChaine() {
        return chaine;
    }

    public void setChaine(EffetBuf chaine) {
        this.chaine = chaine;
    }

    public void setDuree(int d) {
        this.duree = d;
    }

    public boolean tour() {
        this.duree--;
        if (this.duree == 0) {
            return true;
        }
        return false;
    }
}
