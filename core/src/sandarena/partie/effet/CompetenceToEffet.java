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
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValVitesse;

/**
 * Created by Guillaume on 16/06/2015.
 */
//TODO gestion des conditions
public class CompetenceToEffet {
    public static final int TYPEATTAQUE = 0;
    public static final int TYPEDEFENSE = 1;
    //todo a complete fur et a mesure
    public static final int DOT = 4;
    public static final int VALVITESSE = 5;
    public static final int VALATTAQUE = 6;
    public static final int DEGAT = 7;

    //
    public static final int CONDITIONBUFF=3;
    public static final int CONDITIONDUREE = 0;

    public static ArrayList<String> toStrings(Competence comp) {
        ArrayList<String> retour = new ArrayList<String>();
        if (comp instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) comp;
            retour.add(switchTypeBuff(tmp.getTypeBuff(), tmp.getVal()));
        } else if (comp instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) comp;
            retour.add(switchTypeBuff(tmp.getTypeBuff(), tmp.getVal()));
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

    public static String switchTypeBuff(int type, int val) {
        switch (type) {
            case (TYPEATTAQUE):
                return "Change le type d'attaque en " + switchtype(val);
            case (TYPEDEFENSE):
                return "Change le type de defense en " + switchtype(val);
            case (VALATTAQUE):
                return "Augmente l'attaque de " + val;
            case (VALVITESSE):
                return "Augmente la vitesse de " + val;
            case (DEGAT):
                return "Inflige " + val + " degats";
            case (DOT):
                return "Inflige " + val + " degats par tour";
        }
        return null;
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

    private static EffetBuf creerEffet(int type, int val, double[] donnee) {
        switch (type) {
            case (TYPEATTAQUE):
                return new EffetBuffTypeAttaque(val, null);
            case (TYPEDEFENSE):
                return new EffetBuffTypeDefense(val, null);
            case (VALATTAQUE):
                return new EffetBuffValAttaque(val, null);
            case (VALVITESSE):
                return new EffetBuffValVitesse(val, null);
            case (DEGAT):
                return new EffetBuffDommage(val, null);
            case (DOT):
                return new EffetBuffDot(val, null);
        }
        return null;
    }
}
