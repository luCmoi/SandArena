package sandarena.joueur;

import java.util.ArrayList;

public class Joueur {
    private ArrayList<Personnage> personnages = new ArrayList();

    public Joueur() {

    }

    public void dispose() {
        for (Personnage perso : personnages) {
            perso.dispose();
        }
        personnages.clear();
        personnages = null;
    }

    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public void setPersonnages(ArrayList<Personnage> personnages) {
        this.personnages = personnages;
    }
}
