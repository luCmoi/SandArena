package sandarena.partie.effet;

import java.util.ArrayList;

import sandarena.partie.compcase.PersonnageIG;

//todo conditions
public abstract class EffetBuf extends Effet {
    private PersonnageIG container;
    private EffetBuf chaine;
    private int duree = -1;
    private String nom;

    public EffetBuf(String nom, EffetBuf chaine) {
        this.nom= nom;
        this.setChaine(chaine);
    }

    public PersonnageIG getContainer() {
        return container;
    }

    public void setContainer(PersonnageIG container, boolean dispelable) {
        this.container = container;
        if (chaine != null) {
            container.addBuf(chaine, dispelable);
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

    public int getDuree(){
        return duree;
    }

    public ArrayList<String> toStrings(){
        ArrayList<String> retour = new ArrayList<String>();
        retour.add(nom);
        return retour;
    }
}
