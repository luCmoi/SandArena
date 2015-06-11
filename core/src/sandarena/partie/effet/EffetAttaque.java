package sandarena.partie.effet;

import sandarena.donnee.Caract;
import sandarena.donnee.IntegerNew;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.partie.Case;

/**
 * @author Guillaume
 */
public class EffetAttaque {
    public IntegerNew type;
    public double mul = 1;

    public EffetAttaque(int type) {
        this.type = new IntegerNew(type);
    }

    public EffetAttaque(CompetenceAttaque comp) {
        this.type = new IntegerNew(comp.getCaract());
        this.mul = comp.getMultiAttaque();
    }

    public void lance(Case attaquant, Case defenseur) {
        if (defenseur.getPresence() != null) {
            IntegerNew att = new IntegerNew(0);
            IntegerNew def = new IntegerNew(0);
            switch (type.anInt) {
                case (Caract.FORCE):
                    att.anInt = attaquant.getPresence().getDonnee().commun.force;
                    def.anInt = defenseur.getPresence().getDonnee().commun.force;
                    break;
                case (Caract.AGILITE):
                    att.anInt = attaquant.getPresence().getDonnee().commun.agilite;
                    def.anInt = defenseur.getPresence().getDonnee().commun.agilite;
                    break;
                case (Caract.MAGIE):
                    att.anInt = attaquant.getPresence().getDonnee().commun.magie;
                    def.anInt = defenseur.getPresence().getDonnee().commun.magie;
                    break;
            }
            att.aDouble = att.anInt;
            def.aDouble = def.anInt;
            attaquant.getPresence().modifAttaque(att, type);
            defenseur.getPresence().modifDefense(def, type);
            int degat = degat(att.anInt, def.anInt);
            for (EffetDeclencheur effet : defenseur.getPresence().getDeclencheurs()) {
                effet.check(type.anInt, defenseur.getPresence(), attaquant.getPresence(), degat);
            }
            defenseur.getPresence().inflige(degat);
        }
    }

    public int degat(int caractA, int caractD) {
        return caractA + (caractD-caractA);
    }
}
