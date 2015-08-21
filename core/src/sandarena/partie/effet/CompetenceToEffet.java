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


    public static ArrayList<String> toStrings(Competence comp) {
        ArrayList<String> retour = new ArrayList<String>();
        if (comp instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) comp;
            retour.addAll(switchTypeBuff(tmp));
        } else if (comp instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) comp;
            retour.addAll(switchTypeBuff(tmp.getSucc()));
        } else if (comp instanceof CompetenceDeclencheurEffet) {
            CompetenceDeclencheurEffet tmp = (CompetenceDeclencheurEffet) comp;
            retour.addAll(switchTypeDeclencheur(tmp.getTypedeclencheur(), tmp.getCible(), tmp.getEffet(), tmp.getSucc()));
        }
        return retour;
    }

    private static ArrayList<String> switchTypeDeclencheur(int type, int cible, Competence effet, CompetenceBuff succ) {
        ArrayList<String> retour = new ArrayList<String>();
        switch (type) {
            case (DEGATRECU):
                retour.add("Quand le personnage reçoit des dégats");
                break;
        }
        retour.addAll(toStrings(effet));
        if (succ != null) {
            retour.addAll(switchTypeBuff(succ));
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

    public static ArrayList<String> switchTypeBuff(CompetenceBuff comp) {
        ArrayList<String> retour = new ArrayList<String>();
        switch (comp.getTypeBuff()) {
            case (TYPEATTAQUE):
                retour.add("Change le type d'attaque en " + switchtype(comp.getVal()));
                break;
            case (TYPEDEFENSE):
                retour.add("Change le type de defense en " + switchtype(comp.getVal()));
                break;
            case (VALATTAQUE):
                retour.add((comp.getVal()<0?"Diminue" : "Augmente")+" l'attaque de " + comp.getVal());
                break;
            case (VALVITESSE):
                retour.add((comp.getVal()<0?"Diminue" : "Augmente")+" la vitesse de " + comp.getVal());
                break;
            case (DEGAT):
                retour.add("Inflige " + comp.getVal() + " degats");
                break;
            case (DOT):
                retour.add("Inflige " + comp.getVal() + " degats par tour");
                break;
            case (STUN):
                retour.add("Assomme pendant " + comp.getVal() + " tours");
                break;
            case (VALDEFENSE):
                retour.add("Augmente la defense de " + comp.getVal());
                break;
        }
        if (comp.getSucc() != null) {
            retour.addAll(switchTypeBuff(comp.getSucc()));
        }
        if (comp.getCondtype() != -1) {
            retour.add("Sur la caractéristique " + switchtype(comp.getCondtype()));
        }
        if (comp.getCondduree() != -1) {
            retour.add("Dure " + comp.getCondduree() + " tours");
        }
        return retour;
    }

    public static EffetBuf toEffet(CompetenceIG competenceIG) {
        String nom = competenceIG.info.nom;
        if (competenceIG.info.competence instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) competenceIG.info.competence;
            return creerEffet(nom, tmp);
        } else if (competenceIG.info.competence instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) competenceIG.info.competence;
            return creerEffet(nom, tmp.getSucc());
        } else if (competenceIG.info.competence instanceof CompetenceAttaque) {
            CompetenceAttaque tmp = (CompetenceAttaque) competenceIG.info.competence;
            if (tmp.getSucc() != null) {
                return creerEffet(nom, tmp.getSucc());
            }
        } else if (competenceIG.info.competence instanceof CompetenceDispel) {
            CompetenceDispel tmp = (CompetenceDispel) competenceIG.info.competence;
            if (tmp.getSucc() != null) {
                return creerEffet(nom, tmp.getSucc());
            }
        }
        return null;
    }

    private static EffetBuf creerEffet(String nom, CompetenceBuff comp) {
        EffetBuf retour = null;
        switch (comp.getTypeBuff()) {
            case (TYPEATTAQUE):
                retour = new EffetBuffTypeAttaque(nom, comp.getVal(), null);
                break;
            case (TYPEDEFENSE):
                retour = new EffetBuffTypeDefense(nom, comp.getVal(), null);
                break;
            case (VALATTAQUE):
                retour = new EffetBuffValAttaque(nom, comp.getVal(), null);
                break;
            case (VALVITESSE):
                retour = new EffetBuffValVitesse(nom, comp.getVal(), null);
                break;
            case (DEGAT):
                retour = new EffetBuffDommage(nom, comp.getVal(), null);
                break;
            case (DOT):
                retour = new EffetBuffDot(nom, comp.getVal(), null);
                break;
            case (STUN):
                retour = new EffetBuffStun(nom, comp.getVal(), null);
                break;
            case (VALDEFENSE):
                retour = new EffetBuffValDefense(nom, comp.getVal(), null);
        }
        if (comp.getSucc() != null) {
            retour.setChaine(creerEffet(nom, comp.getSucc()));
        }
        if (comp.getCondtype() != -1) {
            if (retour instanceof EffetBuffVal) {
                ((EffetBuffVal) retour).setTypeCond(comp.getCondtype());
            } else if (retour instanceof EffetBuffType) {
                ((EffetBuffType) retour).setTypeCond(comp.getCondtype());
            }
        } else if (comp.getCondduree() != -1) {
            retour.setDuree(comp.getCondduree());
        }
        return retour;
    }

    public static EffetDeclencheur toDeclencheur(CompetenceIG comp) {
        CompetenceDeclencheurEffet tmp2 = (CompetenceDeclencheurEffet) comp.info.competence;
        Effet tmp = null;
        if (tmp2.getEffet() != null) {
            if (tmp2.getEffet() instanceof CompetenceBuff){
                System.out.println("On ajoute");
                tmp = creerEffet(comp.info.nom,(CompetenceBuff) tmp2.getEffet());
            }else {
                //todo apres un declencheur une attaque ne peut faire de buff
                tmp = new EffetAttaque(((CompetenceAttaque)tmp2.getEffet()), null);
            }
        }
        switch (tmp2.getTypedeclencheur()) {
            case (DEGATRECU):
                System.out.println(tmp.getClass());
                return new EffetDeclencheurDegatRecu(tmp2.getCible(), tmp);
        }
        return null;
    }
}
