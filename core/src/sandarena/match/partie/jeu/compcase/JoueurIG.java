package sandarena.match.partie.jeu.compcase;

import java.util.ArrayList;

import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;

/**
 * Le profil du joueur en partie
 */
public class JoueurIG {

    private Joueur joueur;
    private ArrayList<PersonnageIG> personnages = new ArrayList();

    public JoueurIG(Joueur joueur, ArrayList<Personnage> personnages) {
        this.joueur = joueur;
        for (Personnage personage : personnages) {
            this.personnages.add(new PersonnageIG(personage, this));
        }

    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<PersonnageIG> getPersonnages() {
        return personnages;
    }
}
