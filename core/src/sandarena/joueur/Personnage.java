package sandarena.joueur;

import sandarena.donnee.BanqueCompetence;
import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.donnee.BanquePersonnage;
import sandarena.donnee.BanquePersonnage.DonneePersonnage;

/**
 * Represente les donnee fixes d'un personnage
 *
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
        for (int i = 0; i < 4; i++) {
            int tmp = (int)(Math.random()*commun.affinite.length);
            this.competences[i] = BanqueCompetence.getCompetence(commun.affinite[tmp], competences);
            this.competences[i].incremente();
        }
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
        //todo return nom;
    }

    public EntreeCompetence[] getCompetences() {
        return competences;
    }
}
