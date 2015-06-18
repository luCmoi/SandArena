package sandarena.partie.effet;

import java.util.ArrayList;

import sandarena.donnee.Caract;
import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.joueur.competence.active.CompetenceBuffActif;
import sandarena.joueur.competence.active.CompetenceDispel;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.joueur.competence.passive.CompetenceDeclencheurEffet;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.effet.effetbuff.EffetBuffDommage;
import sandarena.partie.effet.effetbuff.EffetBuffDot;
import sandarena.partie.effet.effetbuff.EffetBuffStun;
import sandarena.partie.effet.effetbuff.EffetBuffType;
import sandarena.partie.effet.effetbuff.EffetBuffVal;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeAttaque;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValAttaque;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValVitesse;
import sandarena.partie.effet.effetdeclencheur.EffetDeclencheurDegatRecu;

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
    public static final int STUN = 8;
    public static final int VALDEFENSE = 9;
    //static type declencheur
    public static final int DEGATRECU = 0;
    //static cible declencheur
    public static final int SOI = 0;
    //static suite
    public static final int CONDITIONBUFF = 0;
    public static final int CONDITIONDUREE = 1;
    public static final int CONDITIONTYPE = 2;

    public static ArrayList<String> toStrings(Competence comp) {
        ArrayList<String> retour = new ArrayList<String>();
        if (comp instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) comp;
            retour.addAll(switchTypeBuff(tmp.getTypeBuff(), tmp.getVal(), tmp.getDonnee()));
        } else if (comp instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) comp;
            retour.addAll(switchTypeBuff(tmp.getTypeBuff(), tmp.getVal(), tmp.getDonnee()));
        } else if (comp instanceof CompetenceDeclencheurEffet) {
            CompetenceDeclencheurEffet tmp = (CompetenceDeclencheurEffet) comp;
            retour.addAll(switchTypeDeclencheur(tmp.getTypedeclencheur(), tmp.getCible(), tmp.getDonnee()));
        }
        return retour;
    }

    private static ArrayList<String> switchTypeDeclencheur(int type, int cible, int[] donnee) {
        ArrayList<String> retour = new ArrayList<String>();
        switch (type) {
            case (DEGATRECU):
                retour.add("Quand le personnage reçoit des dégats");
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
        if (cible == SOI) {
            retour.add("Cible : soi");
        } else {
            retour.add("Cible : déclencheur");
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
            case (STUN):
                retour.add("Assomme pendant " + val + " tours");
                break;
            case (VALDEFENSE):
                retour.add("Augmente la defense de "+val);
                break;
        }
        if (donnee != null) {
            for (int y = 0; y < donnee.length; y++) {
                if (donnee[y] == CONDITIONBUFF) {
                    int[] donneetmp = null;
                    if (donnee.length != 3 + y) {
                        donneetmp = new int[donnee.length - 3 - y];
                        for (int i = y; i < donnee.length - 3 - y; i++) {
                            donneetmp[i] = donnee[i + 3];
                        }
                    }
                    retour.addAll(switchTypeBuff(donnee[1], donnee[2], donneetmp));
                    y = donnee.length;
                } else if (donnee[y] == CONDITIONTYPE) {
                    retour.add("Sur la caractéristique " + switchtype(donnee[y + 1]));
                    y++;
                }else if (donnee[y] == CONDITIONDUREE){
                    retour.add("Dure "+donnee[y+1]+" tours");
                }
            }
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
        } else if (competenceIG.info.competence instanceof CompetenceAttaque) {
            CompetenceAttaque tmp = (CompetenceAttaque) competenceIG.info.competence;
            if (tmp.getDonnee() != null) {
                int[] donneetmp = null;
                if (tmp.getDonnee().length > 3) {
                    donneetmp = new int[tmp.getDonnee().length - 3];
                    for (int i = 0; i < tmp.getDonnee().length - 3; i++) {
                        donneetmp[i] = tmp.getDonnee()[i + 3];
                    }
                }
                return CompetenceToEffet.creerEffet(tmp.getDonnee()[1], tmp.getDonnee()[2], donneetmp);
            }
        }else if (competenceIG.info.competence instanceof CompetenceDispel){
            CompetenceDispel tmp = (CompetenceDispel) competenceIG.info.competence;
            if (tmp.getDonnee() != null) {
                int[] donneetmp = null;
                if (tmp.getDonnee().length > 3) {
                    donneetmp = new int[tmp.getDonnee().length - 3];
                    for (int i = 0; i < tmp.getDonnee().length - 3; i++) {
                        donneetmp[i] = tmp.getDonnee()[i + 3];
                    }
                }
                return CompetenceToEffet.creerEffet(tmp.getDonnee()[1], tmp.getDonnee()[2], donneetmp);
            }
        }
        return null;
    }

    private static EffetBuf creerEffet(int type, int val, int[] donnee) {
        EffetBuf retour = null;
        switch (type) {
            case (TYPEATTAQUE):
                retour = new EffetBuffTypeAttaque(val, null);
                break;
            case (TYPEDEFENSE):
                retour = new EffetBuffTypeDefense(val, null);
                break;
            case (VALATTAQUE):
                retour = new EffetBuffValAttaque(val, null);
                break;
            case (VALVITESSE):
                retour = new EffetBuffValVitesse(val, null);
                break;
            case (DEGAT):
                retour = new EffetBuffDommage(val, null);
                break;
            case (DOT):
                retour = new EffetBuffDot(val, null);
                break;
            case (STUN):
                retour = new EffetBuffStun(val, null);
                break;
            case (VALDEFENSE):
                retour = new EffetBuffValDefense(val, null);
        }
        if (donnee != null) {
            for (int y = 0; y < donnee.length; y++) {
                if (donnee[y] == CONDITIONBUFF) {
                    int[] donneetmp = null;
                    if (donnee.length != 3 + y) {
                        donneetmp = new int[donnee.length - 3 - y];
                        for (int i = y; i < donnee.length - 3 - y; i++) {
                            donneetmp[i] = donnee[i + 3];
                        }
                    }
                    retour.setChaine(creerEffet(donnee[1], donnee[2], donneetmp));
                    y = donnee.length;
                } else if (donnee[y] == CONDITIONTYPE) {
                    if (retour instanceof EffetBuffVal) {
                        ((EffetBuffVal) retour).setTypeCond(donnee[y + 1]);
                        y++;
                    }else if (retour instanceof EffetBuffType){
                        ((EffetBuffType) retour).setTypeCond(donnee[y+1]);
                        y++;
                    }
                }else if (donnee[y]== CONDITIONDUREE){
                    retour.setDuree(donnee[y+1]);
                    y++;
                }
            }
        }
        return retour;
    }

    public static EffetDeclencheur toDeclencheur(CompetenceIG comp) {
        CompetenceDeclencheurEffet tmp2 = (CompetenceDeclencheurEffet) comp.info.competence;
        EffetBuf tmp = null;
        if (tmp2.getDonnee() != null) {
            if (tmp2.getDonnee()[0] == CONDITIONBUFF) {
                int[] donneetmp = null;
                if (tmp2.getDonnee().length != 3) {
                    donneetmp = new int[tmp2.getDonnee().length - 3];
                    for (int i = 0; i < tmp2.getDonnee().length - 3; i++) {
                        donneetmp[i] = tmp2.getDonnee()[i + 3];
                    }
                }
                tmp = creerEffet(tmp2.getDonnee()[1], tmp2.getDonnee()[2], donneetmp);
            }
        }
        switch (tmp2.getTypedeclencheur()) {
            case (DEGATRECU):
                return new EffetDeclencheurDegatRecu(tmp2.getCible(), tmp);
        }
        return null;
    }
}
