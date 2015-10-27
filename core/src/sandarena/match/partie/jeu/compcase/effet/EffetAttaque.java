package sandarena.match.partie.jeu.compcase.effet;

import sandarena.donnee.donneestatic.Caract;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.match.partie.jeu.Case;

/**
 * @author Guillaume
 */
public class EffetAttaque extends Effet {
    private int type;
    private int mul = 0;
    private EffetBuf suite;


    public EffetAttaque(CompetenceAttaque comp, EffetBuf suite) {
        this.type = comp.getCaract();
        this.mul = comp.getMultiAttaque();
        this.suite = suite;
    }

    public int lance(Case attaquant, Case defenseur, boolean agit) {
        if (defenseur.getPresence() != null) {
            int att = 0;
            int def = 0;
            switch (type) {
                case (Caract.FORCE):
                    att = attaquant.getPresence().getDonnee().commun.force;
                    def = defenseur.getPresence().getDonnee().commun.force;
                    break;
                case (Caract.AGILITE):
                    att = attaquant.getPresence().getDonnee().commun.agilite;
                    def = defenseur.getPresence().getDonnee().commun.agilite;
                    break;
                case (Caract.MAGIE):
                    att = attaquant.getPresence().getDonnee().commun.magie;
                    def = defenseur.getPresence().getDonnee().commun.magie;
                    break;
            }
            att = attaquant.getPresence().modifAttaque(att, type);
            def = defenseur.getPresence().modifDefense(def, type);
            int degat = degat(att, def);
            if (agit) {
                defenseur.getPresence().inflige(degat + mul);
                if (suite != null) {
                    if (defenseur.getPresence() != null) {
                        defenseur.getPresence().addBuf(suite, true);
                    }
                }
            } else return degat + mul;
        }
        return 0;
    }

    private int degat(int caractA, int caractD) {
        return Math.min(1,(caractA - caractD));
    }
}
