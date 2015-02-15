package sandarena.joueur;

import sandarena.donnee.BanqueCompetence;
import sandarena.donnee.BanqueCompetence.*;
import sandarena.donnee.BanquePersonnage;
import sandarena.donnee.BanquePersonnage.*;

/**
 * Represente les donnee fixes d'un personnage
 * @author Guillaume
 */
public class Personnage {

    public String nom;
    public EntreePersonnage info;
    public EntreeCompetence[] competences = new EntreeCompetence[4];

    /**
     * Création d'un nouveau personnage tirer avec un nom et une comptence
     * @param nom
     */
    public Personnage(String nom) {
        this.info = (EntreePersonnage) BanquePersonnage.getEntree(BanquePersonnage.banque, nom);
        this.nom = BanquePersonnage.getNom(nom);
        this.competences[0] = BanqueCompetence.getCompetence(info.affinite);
    }

    public void dispose() {
        info.decremente();
        this.info = null;
        for (EntreeCompetence c : competences) {
            if (c != null) {
                c.decremente();
                c = null;
            }
        }
        competences = null;
        nom = null;
    }
}
