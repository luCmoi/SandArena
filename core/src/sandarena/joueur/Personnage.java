package sandarena.joueur;

import sandarena.donnee.BanqueCompetence;
import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.donnee.BanquePersonnage;
import sandarena.donnee.BanquePersonnage.DonneePersonnage;

/**
 * Represente les donnee fixes d'un personnage
 *
 * @author Guillaume
 */
public class Personnage {

    public DonneePersonnage commun;
    private String nom;
    private EntreeCompetence[] competences = new EntreeCompetence[4];

    /**
     * Création d'un nouveau personnage tirer avec un nom et une comptence
     *
     * @param nom
     */
    public Personnage(String nom) {
        this.commun = (DonneePersonnage) BanquePersonnage.getEntree(BanquePersonnage.banque, nom);
        this.nom = BanquePersonnage.getNom(nom);

        //Ligne qui changera les compétence n'étant pas init au pifometre
        this.competences[0] = BanqueCompetence.getCompetence(commun.affinite[0]);
        this.competences[0].incremente();
        this.competences[1] = BanqueCompetence.getCompetence(commun.affinite[0]);
        this.competences[1].incremente();
        this.competences[2] = BanqueCompetence.getCompetence(commun.affinite[0]);
        this.competences[2].incremente();
        this.competences[3] = BanqueCompetence.getCompetence(commun.affinite[1]);
        this.competences[3].incremente();
    }

    public void dispose() {
        commun.decremente();
        this.commun = null;
        for (EntreeCompetence c : getCompetences()) {
            if (c != null) {
                c.decremente();
                c = null;
            }
        }
        competences = null;
        nom = null;
    }

    public String getNom() {
        return "Fred";
        //return nom;
    }

    public EntreeCompetence[] getCompetences() {
        return competences;
    }
}
