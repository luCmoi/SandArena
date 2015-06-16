package sandarena.partie.effet;

import java.util.ArrayList;

import sandarena.donnee.Caract;
import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.active.CompetenceBuffActif;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.effet.effetbuff.EffetBuffDommage;
import sandarena.partie.effet.effetbuff.EffetBuffDot;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeAttaque;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValAttaque;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValVitesse;

/**
 * Created by Guillaume on 16/06/2015.
 */
//TODO gestion des conditions
public class CompetenceToEffet {
    //todo a complete fur et a mesure
//Static type buff
    public static final int TYPEATTAQUE = 0;
    public static final int TYPEDEFENSE = 1;
    public static final int DOT = 4;
    public static final int VALVITESSE = 5;
    public static final int VALATTAQUE = 6;
    public static final int DEGAT = 7;
    //static type declencheur
    public static final int DEGATRECU = 0;
    //static cible declencheur
    public static final int SOI = 0;
    //static suite
    public static final int CONDITIONBUFF = 0;
    public static final int CONDITIONDUREE = 1;

    public static ArrayList<String> toStrings(Competence comp) {
        ArrayList<String> retour = new ArrayList<String>();
        if (comp instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) comp;
            retour.addAll(switchTypeBuff(tmp.getTypeBuff(), tmp.getVal(), tmp.getDonnee()));
        } else if (comp instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) comp;
            retour.addAll(switchTypeBuff(tmp.getTypeBuff(), tmp.getVal(), tmp.getDonnee()));
        }
        return retour;
    }

    public static String switchtype(int type) {
        switch (type) {
            case (Caract.FORCE):
                return "force";
            case (Caract.AGILITE):
                return "agilite";
            case (Caract.MAGIE):
                return "magie";
        }
        return null;
    }

    public static ArrayList<String> switchTypeBuff(int type, int val, int[] donnee) {
        ArrayList<String> retour = new ArrayList<String>();
        switch (type) {
            case (TYPEATTAQUE):
                retour.add("Change le type d'attaque en " + switchtype(val));
                break;
            case (TYPEDEFENSE):
                retour.add("Change le type de defense en " + switchtype(val));
                break;
            case (VALATTAQUE):
                retour.add("Augmente l'attaque de " + val);
                break;
            case (VALVITESSE):
                retour.add("Augmente la vitesse de " + val);
                break;
            case (DEGAT):
                retour.add("Inflige " + val + " degats");
                break;
            case (DOT):
                retour.add("Inflige " + val + " degats par tour");
                break;
        }
        if (donnee != null && donnee[0] == CONDITIONBUFF) {
            int[] donneetmp = null;
            if (donnee.length != 3) {
                donneetmp = new int[donnee.length - 3];
                for (int i = 0; i < donnee.length - 3; i++) {
                    donneetmp[i] = donnee[i + 3];
                }
            }
            retour.addAll(switchTypeBuff(donnee[1], donnee[2], donneetmp));
        }
        return retour;
    }

    public static EffetBuf toEffet(CompetenceIG competenceIG) {
        if (competenceIG.info.competence instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) competenceIG.info.competence;
            return creerEffet(tmp.getTypeBuff(), tmp.getVal(), tmp.getDonnee());
        } else if (competenceIG.info.competence instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) competenceIG.info.competence;
            return creerEffet(tmp.getTypeBuff(), tmp.getVal(), tmp.getDonnee());
        }
        return null;
    }

    private static EffetBuf creerEffet(int type, int val, int[] donnee) {
        EffetBuf tmp = null;
        if (donnee!= null) {
            if (donnee[0] == CONDITIONBUFF) {
                int[] donneetmp = null;
                if (donnee.length != 3) {
                    donneetmp = new int[donnee.length - 3];
                    for (int i = 0; i < donnee.length - 3; i++) {
                        donneetmp[i] = donnee[i + 3];
                    }
                }
                tmp = creerEffet(donnee[1], donnee[2], donneetmp);
            }
        }
            switch (type) {
                case (TYPEATTAQUE):
                    return new EffetBuffTypeAttaque(val, tmp);
                case (TYPEDEFENSE):
                    return new EffetBuffTypeDefense(val, tmp);
                case (VALATTAQUE):
                    return new EffetBuffValAttaque(val, tmp);
                case (VALVITESSE):
                    return new EffetBuffValVitesse(val, tmp);
                case (DEGAT):
                    return new EffetBuffDommage(val, tmp);
                case (DOT):
                    return new EffetBuffDot(val, tmp);
            }
            return null;
        }

    public static EffetDeclencheur toDeclencheur(CompetenceIG competenceIG) {
        return null;
        //todo comme pour les buff pleins de classes
    }
}
