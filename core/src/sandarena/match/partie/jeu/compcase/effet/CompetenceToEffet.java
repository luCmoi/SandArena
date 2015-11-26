package sandarena.match.partie.jeu.compcase.effet;

import java.util.ArrayList;

import sandarena.donnee.donneestatic.Caract;
import sandarena.joueur.blessure.Blessure;
import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.joueur.competence.active.CompetenceBuffActif;
import sandarena.joueur.competence.active.CompetenceDispel;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.joueur.competence.passive.CompetenceDeclencheurEffet;
import sandarena.match.partie.jeu.compcase.PersonnageIG;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffStun;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffType;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffVal;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValCaract;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValVie;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValVitesse;

/**
 * Created by Guillaume on 16/06/2015.
 */
//TODO gestion des conditions
public class CompetenceToEffet {
    //todo a complete fur et a mesure
//Static type buff
    private static final int TYPEATTAQUE = 0;
    private static final int TYPEDEFENSE = 1;
    private static final int DOT = 4;
    private static final int VALVITESSE = 5;
    private static final int VALATTAQUE = 6;
    private static final int DEGAT = 7;
    private static final int STUN = 8;
    private static final int VALDEFENSE = 9;
    private static final int VALCARACT = 10;
    //static type declencheur
    private static final int DEGATRECU = 0;
    //static cible declencheur
    public static final int SOI = 0;


    public static ArrayList<String> toStrings(Competence comp) {
        ArrayList<String> retour = new ArrayList<String>();
        if (comp instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) comp;
            retour.addAll(switchTypeBuff(tmp, true, -1));
        } else if (comp instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) comp;
            retour.addAll(switchTypeBuff(tmp.getSucc(), true, -1));
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
                retour.add("Quand le personnage re�oit des d�gats");
                break;
        }
        retour.addAll(toStrings(effet));
        if (succ != null) {
            retour.addAll(switchTypeBuff(succ, true, -1));
        }
        if (cible == SOI) {
            retour.add("Cible : soi");
        } else {
            retour.add("Cible : d�clencheur");
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

    private static ArrayList<String> switchTypeBuff(CompetenceBuff comp, boolean afficheCond, int cond) {
        ArrayList<String> retour = new ArrayList<String>();
        switch (comp.getTypeBuff()) {
            case (TYPEATTAQUE):
                retour.add("Change le type d'attaque en " + switchtype(comp.getVal()));
                break;
            case (TYPEDEFENSE):
                retour.add("Change le type de defense en " + switchtype(comp.getVal()));
                break;
            case (VALATTAQUE):
                retour.add((comp.getVal() < 0 ? "Diminue" : "Augmente") + " l'attaque de " + comp.getVal());
                break;
            case (VALVITESSE):
                retour.add((comp.getVal() < 0 ? "Diminue" : "Augmente") + " la vitesse de " + comp.getVal());
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
            case (VALCARACT):
                retour.add("Augmente de " + comp.getVal());
        }
        if (comp.getSucc() != null) {
            if (comp.getTypeBuff() != DEGAT) {
                retour.addAll(switchTypeBuff(comp.getSucc(), false, comp.getCondtype()));
            } else {
                retour.addAll(switchTypeBuff(comp.getSucc(), true, comp.getCondtype()));
            }
        }
        if (cond == -1 || cond != comp.getCondtype()) {
            if (comp.getCondtype() != -1) {
                retour.add("Sur la caract�ristique " + switchtype(comp.getCondtype()));
            }
        }
        if (afficheCond) {
            if (comp.getCondduree() != -1) {
                retour.add("Dure " + comp.getCondduree() + " tours");
            }
        }
        return retour;
    }

    public static EffetBuf toEffet(sandarena.match.partie.jeu.compcase.CompetenceIG competenceIG) {
        String nom = competenceIG.info.nom;
        if (competenceIG.info.competence instanceof CompetenceBuff) {
            CompetenceBuff tmp = (CompetenceBuff) competenceIG.info.competence;
            return creerEffet(nom, tmp, new EffetBufGroup(nom));
        } else if (competenceIG.info.competence instanceof CompetenceBuffActif) {
            CompetenceBuffActif tmp = (CompetenceBuffActif) competenceIG.info.competence;
            return creerEffet(nom, tmp.getSucc(), new EffetBufGroup(nom));
        } else if (competenceIG.info.competence instanceof CompetenceAttaque) {
            CompetenceAttaque tmp = (CompetenceAttaque) competenceIG.info.competence;
            if (tmp.getSucc() != null) {
                return creerEffet(nom, tmp.getSucc(), new EffetBufGroup(nom));
            }
        } else if (competenceIG.info.competence instanceof CompetenceDispel) {
            CompetenceDispel tmp = (CompetenceDispel) competenceIG.info.competence;
            if (tmp.getSucc() != null) {
                return creerEffet(nom, tmp.getSucc(), new EffetBufGroup(nom));
            }
        }
        return null;
    }

    private static EffetBuf creerEffet(String nom, CompetenceBuff comp, EffetBufGroup group) {
        EffetBuf retour = null;
        switch (comp.getTypeBuff()) {
            case (TYPEATTAQUE):
                retour = new sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbufftype.EffetBuffTypeAttaque(nom, comp.getVal(), group);
                break;
            case (TYPEDEFENSE):
                retour = new sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbufftype.EffetBuffTypeDefense(nom, comp.getVal(), group);
                break;
            case (VALATTAQUE):
                retour = new sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValAttaque(nom, comp.getVal(), group);
                break;
            case (VALVITESSE):
                retour = new EffetBuffValVitesse(nom, comp.getVal(), group);
                break;
            case (DEGAT):
                retour = new sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffDommage(nom, comp.getVal(), group);
                break;
            case (DOT):
                retour = new sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffDot(nom, comp.getVal(), group);
                break;
            case (STUN):
                retour = new EffetBuffStun(nom, comp.getVal(), group);
                break;
            case (VALDEFENSE):
                retour = new sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValDefense(nom, comp.getVal(), group);
                break;
            case (VALCARACT):
                retour = new sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValCaract(nom, comp.getVal(), group);
                break;
        }
        group.addBuff(retour);
        if (comp.getSucc() != null) {
            creerEffet(nom, comp.getSucc(), group);
        }
        if (comp.getCondtype() != -1) {
            if (!group.setTypeCond(comp.getCondtype())) {
                if (retour instanceof EffetBuffType) {
                    ((EffetBuffType) retour).setTypeCond(comp.getCondtype());
                } else if (retour instanceof sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffVal) {
                    ((sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffVal) retour).setTypeCond(comp.getCondtype());
                }
            }
        } else if (comp.getCondduree() != -1) {
            group.setDuree(comp.getCondduree());
        }
        return retour;
    }

    public static EffetDeclencheur toDeclencheur(sandarena.match.partie.jeu.compcase.CompetenceIG comp) {
        CompetenceDeclencheurEffet tmp2 = (CompetenceDeclencheurEffet) comp.info.competence;
        Effet tmp = null;
        if (tmp2.getEffet() != null) {
            if (tmp2.getEffet() instanceof CompetenceBuff) {
                tmp = creerEffet(comp.info.nom, (CompetenceBuff) tmp2.getEffet(), new EffetBufGroup(comp.info.nom));
            } else {
                tmp = new EffetAttaque(((CompetenceAttaque) tmp2.getEffet()), null);
            }
        }
        switch (tmp2.getTypedeclencheur()) {
            case (DEGATRECU):
                return new sandarena.match.partie.jeu.compcase.effet.effetdeclencheur.EffetDeclencheurDegatRecu(tmp2.getCible(), tmp);
        }
        return null;
    }

    public static int[] toStringsCase(Competence comp, sandarena.match.partie.jeu.compcase.PersonnageIG presence, sandarena.match.partie.jeu.compcase.PersonnageIG lanceur, int[] tabModif) {
        if (comp instanceof CompetenceAttaque) {
            tabModif[0] = tabModif[0] - new EffetAttaque(((CompetenceAttaque) comp), null).lance(lanceur.getContainer(), presence.getContainer(), false);
        } else if (comp instanceof CompetenceBuff) {
            CompetenceBuff compTmp = ((CompetenceBuff) comp);
            switch (compTmp.getTypeBuff()) {
                case (TYPEATTAQUE):
                    int modifTypeA = compTmp.getVal();
                    int modifTAtt = 0;
                    switch (modifTypeA) {
                        case (Caract.FORCE):
                            modifTAtt = presence.getForceAttaque();
                            break;
                        case (Caract.AGILITE):
                            modifTAtt = presence.getAgiliteAttaque();
                            break;
                        case (Caract.MAGIE):
                            modifTAtt = presence.getMagieAttaque();
                            break;
                    }
                    if (compTmp.getCondtype() != -1) {
                        switch (compTmp.getCondtype()) {
                            case (Caract.FORCE):
                                tabModif[2] = tabModif[2] + modifTAtt;
                                break;
                            case (Caract.AGILITE):
                                tabModif[4] = tabModif[4] + modifTAtt;
                                break;
                            case (Caract.MAGIE):
                                tabModif[6] = tabModif[6] + modifTAtt;
                                break;
                        }
                    }
                    break;
                case (TYPEDEFENSE):
                    int modifTypeD = compTmp.getVal();
                    int modifTDef = 0;
                    switch (modifTypeD) {
                        case (Caract.FORCE):
                            modifTDef = presence.getForceDefense();
                            break;
                        case (Caract.AGILITE):
                            modifTDef = presence.getAgiliteDefense();
                            break;
                        case (Caract.MAGIE):
                            modifTDef = presence.getMagieDefense();
                            break;
                    }
                    if (compTmp.getCondtype() != -1) {
                        switch (compTmp.getCondtype()) {
                            case (Caract.FORCE):
                                tabModif[3] = tabModif[3] + modifTDef;
                                break;
                            case (Caract.AGILITE):
                                tabModif[5] = tabModif[5] + modifTDef;
                                break;
                            case (Caract.MAGIE):
                                tabModif[7] = tabModif[7] + modifTDef;
                                break;
                        }
                    }
                    break;
                case (VALATTAQUE):
                    int modifAtt = compTmp.getVal();
                    if (compTmp.getCondtype() != -1) {
                        switch (compTmp.getCondtype()) {
                            case (Caract.FORCE):
                                tabModif[2] = tabModif[2] + modifAtt;
                                break;
                            case (Caract.AGILITE):
                                tabModif[4] = tabModif[4] + modifAtt;
                                break;
                            case (Caract.MAGIE):
                                tabModif[6] = tabModif[6] + modifAtt;
                                break;
                        }
                    } else {
                        tabModif[2] = tabModif[2] + modifAtt;
                        tabModif[4] = tabModif[4] + modifAtt;
                        tabModif[6] = tabModif[6] + modifAtt;
                    }
                    break;
                case (VALVITESSE):
                    tabModif[1] = tabModif[1] + compTmp.getVal();
                    break;
                case (DEGAT):
                    tabModif[0] = tabModif[0] - ((CompetenceBuff) comp).getVal();
                    break;
                case (DOT):
                    break;
                case (STUN):
                    break;
                case (VALDEFENSE):
                    int modifDef = compTmp.getVal();
                    if (compTmp.getCondtype() != -1) {
                        switch (compTmp.getCondtype()) {
                            case (Caract.FORCE):
                                tabModif[3] = tabModif[3] + modifDef;
                                break;
                            case (Caract.AGILITE):
                                tabModif[5] = tabModif[5] + modifDef;
                                break;
                            case (Caract.MAGIE):
                                tabModif[7] = tabModif[7] + modifDef;
                                break;
                        }
                    } else {
                        tabModif[3] = tabModif[3] + modifDef;
                        tabModif[5] = tabModif[5] + modifDef;
                        tabModif[7] = tabModif[7] + modifDef;
                    }
                    break;
                case (VALCARACT):
                    int modifCaract = compTmp.getVal();
                    if (compTmp.getCondtype() != -1) {
                        switch (compTmp.getCondtype()) {
                            case (Caract.FORCE):
                                tabModif[2] = tabModif[2] + modifCaract;
                                tabModif[3] = tabModif[3] + modifCaract;
                                break;
                            case (Caract.AGILITE):
                                tabModif[4] = tabModif[4] + modifCaract;
                                tabModif[5] = tabModif[5] + modifCaract;
                                break;
                            case (Caract.MAGIE):
                                tabModif[6] = tabModif[6] + modifCaract;
                                tabModif[7] = tabModif[7] + modifCaract;
                                break;
                        }
                    } else {
                        tabModif[2] = tabModif[2] + modifCaract;
                        tabModif[4] = tabModif[4] + modifCaract;
                        tabModif[6] = tabModif[6] + modifCaract;
                        tabModif[3] = tabModif[3] + modifCaract;
                        tabModif[5] = tabModif[5] + modifCaract;
                        tabModif[7] = tabModif[7] + modifCaract;
                    }
                    break;
            }
        }
        if (comp.getSucc() != null) {
            toStringsCase(comp.getSucc(), presence, lanceur, tabModif);
        }
        return tabModif;
    }

    public static int[] toStringsCase(PersonnageIG presence, int[] tabModif) {
        return new int[0];
    }

    public static EffetBuf blessureToEffet(PersonnageIG personnageIG, Blessure bless, EffetBufGroup effetBufGroup) {
        EffetBuf tmp = null;
        if (bless.donnee.vie != 0) {
             tmp = new EffetBuffValVie(bless.donnee.nom, bless.donnee.vie, effetBufGroup);
            effetBufGroup.addBuff(tmp);
        }
        if (bless.donnee.vitesse != 0) {
             tmp = new EffetBuffValVitesse(bless.donnee.nom, bless.donnee.vitesse, effetBufGroup);
            effetBufGroup.addBuff(tmp);
        }
        if (bless.donnee.force != 0) {
             tmp = new EffetBuffValCaract(bless.donnee.nom, bless.donnee.force, effetBufGroup);
            ((EffetBuffVal)tmp).setTypeCond(Caract.FORCE);
            effetBufGroup.addBuff(tmp);
        }
        if (bless.donnee.agilite != 0) {
             tmp = new EffetBuffValCaract(bless.donnee.nom, bless.donnee.agilite, effetBufGroup);
            ((EffetBuffVal)tmp).setTypeCond(Caract.AGILITE);
            effetBufGroup.addBuff(tmp);
        }
        if (bless.donnee.magie != 0) {
             tmp = new EffetBuffValCaract(bless.donnee.nom, bless.donnee.magie, effetBufGroup);
            ((EffetBuffVal)tmp).setTypeCond(Caract.MAGIE);
            effetBufGroup.addBuff(tmp);
        }
        return tmp;
    }
}
