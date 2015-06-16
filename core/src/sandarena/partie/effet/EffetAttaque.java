package sandarena.partie.effet;

import sandarena.donnee.Caract;
import sandarena.donnee.IntegerNew;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.partie.Case;

/**
 * @author Guillaume
 */
public class EffetAttaque {
    public int type;
    public int mul = 0;
    private EffetBuf suite;

    public EffetAttaque(int type) {
        this.type = type;
    }

    public EffetAttaque(CompetenceAttaque comp, EffetBuf suite) {
        this.type = comp.getCaract();
        this.mul = comp.getMultiAttaque();
        this.suite = suite;
    }

    public void lance(Case attaquant, Case defenseur) {
        if (defenseur.getPresence() != null) {
            int att = 0;
            int def = 0;
            switch (type) {
                case (Caract.FORCE):
                    att = attaquant.getPresence().getDonnee().commun.force;
                    def= defenseur.getPresence().getDonnee().commun.force;
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
            att = att + mul;
            def = defenseur.getPresence().modifDefense(def, type);
            int degat = degat(att, def);
            for (EffetDeclencheur effet : defenseur.getPresence().getDeclencheurs()) {
                //effet.check(type.anInt, defenseur.getPresence(), attaquant.getPresence(), degat);
            }
            defenseur.getPresence().inflige(degat);
            if (suite != null){
                defenseur.getPresence().addBuf(suite);
            }
        }
    }

    public int degat(int caractA, int caractD) {
        return caractA + (caractA-caractD);
    }
}
