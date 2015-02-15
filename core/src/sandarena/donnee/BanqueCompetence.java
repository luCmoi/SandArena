package sandarena.donnee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.active.CompetenceAttaque;

/**
 * Banque de donnée qui stock les compétences
 * @author Guillaume
 */
public class BanqueCompetence extends Banque {

    public static ArrayList<Entree> banque = new ArrayList<Entree>();
    public static ArrayList<Entree> getBanque(){
        return banque;
    }
    /**
     * Initialise toutes les compétences du jeu
     */
    public static void init() {
        banque.add(new EntreeCompetence("Bourre-pif",
                "Coup de poing puissant au visage, de préférence dans le nez.",
                "Image/Competence/Bourre-pif.png",
                new CompetenceAttaque(Affinite.FORCE, 0, 0, 1, Caract.FORCE, 1.2, 1),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
    }

    /**
     * Retourne une compétence aléatoire de l'affinité passée en argument
     * @param affinite
     * @return 
     */
    public static EntreeCompetence getCompetence(int affinite) {
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
