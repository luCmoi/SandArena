package sandarena.match.partie.jeu.compcase.effet;

import java.util.ArrayList;

import sandarena.match.partie.jeu.compcase.PersonnageIG;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffDommage;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffDot;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffStun;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffType;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.EffetBuffVal;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbufftype.EffetBuffTypeAttaque;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValAttaque;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValCaract;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValDefense;
import sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbuffval.EffetBuffValVitesse;

/**
 * Created by lucmo on 07/09/2015.
 */
public class EffetBufGroup {
    private String nom;
    private ArrayList<EffetBuf> chaine = new ArrayList<EffetBuf>();
    private int typeCond = -1;
    private int duree;
    private boolean benefique;

    public EffetBufGroup(String nom) {
        this.nom = nom;
    }

    public void addBuff(EffetBuf add) {
        chaine.add(add);
        if (chaine.size() <= 2 && !(add instanceof EffetBuffDommage)) {
            benefique = add.isBenefique();
        }
    }

    public boolean isBenefique() {
        return benefique;
    }

    public boolean setTypeCond(int typeCond) {
        if (this.typeCond == typeCond || this.typeCond == -1) {
            this.typeCond = typeCond;
            for (EffetBuf effetBuf : chaine) {
                if (effetBuf instanceof EffetBuffType) {
                    ((EffetBuffType) effetBuf).setTypeCond(typeCond);
                } else if (effetBuf instanceof EffetBuffVal) {
                    ((EffetBuffVal) effetBuf).setTypeCond(typeCond);
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public void setDuree(int duree) {
        this.duree = duree;
        for (EffetBuf effetBuf : chaine) {
            if (!(effetBuf instanceof EffetBuffDommage)) {
                effetBuf.setDuree(duree);
            }
        }
    }

    public ArrayList<EffetBuf> getChaine() {
        return chaine;
    }

    public void remove(PersonnageIG personnageIG) {
        for (EffetBuf effetBuf : chaine) {
            if (effetBuf instanceof EffetBuffValCaract) {
                personnageIG.getChangeStat().remove(effetBuf);
            } else if (effetBuf instanceof EffetBuffValAttaque) {
                personnageIG.getChangeAtt().remove(effetBuf);
            } else if (effetBuf instanceof EffetBuffValDefense) {
                personnageIG.getChangeDef().remove(effetBuf);
            } else if (effetBuf instanceof EffetBuffValVitesse) {
                personnageIG.getChangeVitesse().remove(effetBuf);
            } else if (effetBuf instanceof EffetBuffTypeAttaque) {
                personnageIG.getChangeTypeAtt().remove(effetBuf);
            } else if (effetBuf instanceof sandarena.match.partie.jeu.compcase.effet.effetbuff.effetbufftype.EffetBuffTypeDefense) {
                personnageIG.getChangeTypeDef().remove(effetBuf);
            } else if (effetBuf instanceof EffetBuffDot) {
                personnageIG.getDot().remove(effetBuf);
            } else if (effetBuf instanceof EffetBuffStun) {
                personnageIG.getStun().remove(effetBuf);
            }
        }
        personnageIG.modifCaract();
    }
}
