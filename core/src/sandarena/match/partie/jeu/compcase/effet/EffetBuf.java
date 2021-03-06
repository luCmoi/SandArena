package sandarena.match.partie.jeu.compcase.effet;

import java.util.ArrayList;

public abstract class EffetBuf extends Effet {
    private EffetBufGroup group;
    private sandarena.match.partie.jeu.compcase.PersonnageIG container;
    private int duree = -1;
    private String nom;

    protected EffetBuf(String nom, EffetBufGroup group) {
        this.nom= nom;
        this.setGroup(group);
    }

    protected sandarena.match.partie.jeu.compcase.PersonnageIG getContainer() {
        return container;
    }

    public void setContainer(sandarena.match.partie.jeu.compcase.PersonnageIG container, boolean dispelable) {
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

    protected int getDuree(){
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

    private void setGroup(EffetBufGroup group) {
        this.group = group;
    }
}
