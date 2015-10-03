package sandarena.donnee.competence;

import java.util.ArrayList;
import java.util.Collections;

import sandarena.donnee.banque.Banque;
import sandarena.donnee.banque.Entree;
import sandarena.joueur.competence.Competence;


/**
 * Banque de donnée qui stock les compétences
 *
 * @author Guillaume
 */
public class BanqueCompetence extends Banque {
    private static final Integer ATTAQUEBASE = -1;
    public static ArrayList<Entree> banque = new ArrayList<Entree>();

    public static ArrayList<Entree> getBanque() {
        return banque;
    }


    /**
     * Retourne une compétence aléatoire de l'affinité passée en argument
     *
     * @param affinite
     * @return
     */
    public static EntreeCompetence getCompetence(int affinite, EntreeCompetence... dejaAcquise) {
        ArrayList<Entree> tmpBanque = (ArrayList<Entree>)banque.clone();
        Collections.shuffle(tmpBanque);
        boolean vide =true;
        for (EntreeCompetence c : dejaAcquise) {
            if (c != null) {
                vide = false;
                tmpBanque.remove(c);
            }
        }
        for (Entree e : tmpBanque) {
            if (!vide) {
                if (((EntreeCompetence) e).affinite.contains(affinite)) {
                    tmpBanque.clear();
                    return (EntreeCompetence) e;
                }
            } else {
                if (((EntreeCompetence) e).affinite.contains(affinite) && ((EntreeCompetence)e).affinite.contains(ATTAQUEBASE)) {
                    tmpBanque.clear();
                    return (EntreeCompetence) e;
                }
            }
        }
        tmpBanque.clear();
        return null;
    }

    /**
     * Une Entree de Competence
     */
    public static class EntreeCompetence extends Entree{

        public Competence competence;
        private ArrayList<Integer> affinite;

        public EntreeCompetence(int id, String nom, String description, String chemin, Competence competence, ArrayList<Integer> affinite) {
            super(id, nom, description, chemin);
            this.competence = competence;
            this.affinite = affinite;
        }

        @Override
        public void dispose() {
            super.dispose();
            competence.dispose();
            competence = null;
            affinite.clear();
            affinite = null;
        }
    }
}
