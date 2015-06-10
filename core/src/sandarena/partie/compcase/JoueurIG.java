package sandarena.partie.compcase;

import java.util.ArrayList;

import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;

/**
 * Le profil du joueur en partie
 */
public class JoueurIG {

    private Joueur joueur;
    private ArrayList<PersonnageIG> personnages = new ArrayList();

    public JoueurIG(Joueur joueur) {
        this.joueur = joueur;
        for (Personnage personage : joueur.getPersonnages()) {
            personnages.add(new PersonnageIG(personage, this));
        }

    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<PersonnageIG> getPersonnages() {
        return personnages;
    }
}
