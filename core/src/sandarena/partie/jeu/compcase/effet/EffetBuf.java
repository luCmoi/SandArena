package sandarena.partie.jeu.compcase.effet;

import java.util.ArrayList;

import sandarena.partie.jeu.compcase.PersonnageIG;

public abstract class EffetBuf extends Effet {
    private EffetBufGroup group;
    private PersonnageIG container;
    protected int duree = -1;
    protected String nom;

    public EffetBuf(String nom, EffetBufGroup group) {
        this.nom= nom;
        this.setGroup(group);
    }

    public PersonnageIG getContainer() {
        return container;
    }

    public void setContainer(PersonnageIG container, boolean dispelable) {
        this.container = container;
    }

    public abstract boolean isBenefique();


    public void setDuree(int d) {
        this.duree = d;
    }

    public boolean tour() {
        this.duree--;
        return this.duree == 0;
    }

    public int getDuree(){
        return duree;
    }

    public ArrayList<String> toStrings(){
        ArrayList<String> retour = new ArrayList<String>();
        retour.add(nom);
        return retour;
    }

    public EffetBufGroup getGroup() {
        return group;
    }

    public void setGroup(EffetBufGroup group) {
        this.group = group;
    }
}
