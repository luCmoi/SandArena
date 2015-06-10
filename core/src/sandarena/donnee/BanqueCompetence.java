package sandarena.donnee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.joueur.competence.passive.CompetenceDeclencheurEffet;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.EffetDeclencheur;


/**
 * Banque de donnée qui stock les compétences
 *
 * @author Guillaume
 */
public class BanqueCompetence extends Banque {
    public static final int FIN = 9999;
    public static ArrayList<Entree> banque = new ArrayList<Entree>();

    public static ArrayList<Entree> getBanque() {
        return banque;
    }

    /**
     * Initialise toutes les compétences du jeu
     */
    public static void init() {
        banque.add(new EntreeCompetence("Bourre-pif",
                "Coup de poing puissant au visage, de préférence dans le nez.",
                "Image/Competence/Bourrepif.png",
                new CompetenceAttaque(Affinite.FORCE, 0, 0, 1, 1, 1, Caract.FORCE, 1.2),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
        banque.add(new EntreeCompetence("Berzerk",
                "Plus le personnage est blessé plus sa folie est grande.",
                "Image/Competence/Berzerk.png",
                new CompetenceDeclencheurEffet(Affinite.FORCE, EffetDeclencheur.DEGATRECU, EffetDeclencheur.SOI, CompetenceDeclencheurEffet.FIN, EffetDeclencheur.BUFF, EffetBuf.VALATTAQUE, 1.10),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
    }

    /**
     * Retourne une compétence aléatoire de l'affinité passée en argument
     *
     * @param affinite
     * @return
     */
    public static EntreeCompetence getCompetence(int affinite, Competence... dejaAcquise) {
        Collections.shuffle(banque);
        for (Entree e : banque) {
            if (((EntreeCompetence) e).affinite.contains(affinite)) {
                return (EntreeCompetence) e;
            }
        }
        return null;
    }

    /**
     * Une Entree de Competence
     */
    public static class EntreeCompetence extends Entree {

        public Competence competence;
        private ArrayList<Integer> affinite;

        public EntreeCompetence(String nom, String description, String chemin, Competence competence, ArrayList<Integer> affinite) {
            super(nom, description, chemin);
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
