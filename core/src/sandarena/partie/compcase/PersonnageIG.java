package sandarena.partie.compcase;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.Collections;

import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.donnee.Caract;
import sandarena.joueur.Personnage;
import sandarena.partie.Case;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.EffetDeclencheur;
import sandarena.partie.effet.effetbuff.EffetBuffDommage;
import sandarena.partie.effet.effetbuff.EffetBuffDot;
import sandarena.partie.effet.effetbuff.EffetBuffStun;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeAttaque;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValAttaque;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValVitesse;
import sandarena.partie.effet.effetdeclencheur.EffetDeclencheurDegatRecu;

/**
 * Une instance de Personnage en partie
 *
 * @author Guillaume
 */
public class PersonnageIG {
    private final CompetenceIG[] competence = new CompetenceIG[4];
    private Personnage donnee;
    private JoueurIG possesseur;
    private int vieActuelle;
    private int vitesseRestante;
    private Case container;
    private boolean aAgi;
    //Buff
    private ArrayList<EffetBuffValDefense> changeDef = new ArrayList<EffetBuffValDefense>();
    private ArrayList<EffetBuffValAttaque> changeAtt = new ArrayList<EffetBuffValAttaque>();
    private ArrayList<EffetBuffTypeAttaque> changeTypeAtt = new ArrayList<EffetBuffTypeAttaque>();
    private ArrayList<EffetBuffTypeDefense> changeTypeDef = new ArrayList<EffetBuffTypeDefense>();
    private ArrayList<EffetBuffValVitesse> changeVitesse = new ArrayList<EffetBuffValVitesse>();
    private ArrayList<EffetBuffValVitesse> changeVie = new ArrayList<EffetBuffValVitesse>();
    private ArrayList<EffetBuffDot> dot = new ArrayList<EffetBuffDot>();
    private ArrayList<EffetBuffStun> stun = new ArrayList<EffetBuffStun>();
    //Dispel
    private ArrayList<EffetBuf> buffBenefique = new ArrayList<EffetBuf>();
    private ArrayList<EffetBuf> buffMauvais = new ArrayList<EffetBuf>();
    private ArrayList<EffetBuf> buffPassif = new ArrayList<EffetBuf>();
    //Declencheur
    private ArrayList<EffetDeclencheurDegatRecu> recoiDegat = new ArrayList<EffetDeclencheurDegatRecu>();
    //caract en jeu
    private int forceAttaque;
    private int forceDefense;
    private int agiliteAttaque;
    private int agiliteDefense;
    private int magieAttaque;
    private int magieDefense;

    public PersonnageIG(Personnage donnee, JoueurIG possesseur) {
        this.donnee = donnee;
        this.possesseur = possesseur;
        this.vieActuelle = donnee.commun.vie;
        this.vitesseRestante = donnee.commun.vitesse;
        int i = 0;
        for (EntreeCompetence c : donnee.getCompetences()) {
            if (c != null) {
                competence[i] = new CompetenceIG(this, c);
                i++;
            }
        }
        forceAttaque = donnee.commun.force;
        forceDefense = donnee.commun.force;
        agiliteAttaque = donnee.commun.agilite;
        agiliteDefense = donnee.commun.agilite;
        magieAttaque = donnee.commun.magie;
        magieDefense = donnee.commun.magie;
    }

    public void dispose() {
        int i = 0;
        for (CompetenceIG c : getCompetence()) {
            if (c != null) {
                getCompetence()[i].dispose();
                i++;
            }
        }
        this.donnee = null;
        this.setContainer(null);
        //todo check dispose
    }

    public void render(Batch batch) {
        batch.draw(getDonnee().commun.image, getContainer().getX(), getContainer().getY(), getContainer().getWidth(), getContainer().getHeight());
    }

    public Personnage getDonnee() {
        return donnee;
    }

    public int getVieActuelle() {
        return vieActuelle;
    }

    public void setVieActuelle(int vieActuelle) {
        this.vieActuelle = vieActuelle;
        if (this.vieActuelle <= 0) {
        }
    }

    public int getVitesseRestante() {
        return vitesseRestante;
    }

    public void setVitesseRestante(int vitesseRestante) {
        this.vitesseRestante = vitesseRestante;
    }

    public CompetenceIG[] getCompetence() {
        return competence;
    }

    public Case getContainer() {
        return container;
    }

    public void setContainer(Case container) {
        this.container = container;
    }

    public boolean isAAgi() {
        return aAgi;
    }

    public void setAAgi(boolean b) {
        this.aAgi = b;
        if (b) {
            this.container.getContainer().setCompetenceActive(null);
            this.container.getContainer().finPerso();
        } else {
            for (CompetenceIG c : competence) {
                if (c != null) {
                    c.tour();
                }
            }
            if (!getStun().isEmpty()) {
                this.aAgi = true;
            }
        }
    }


    public void mouvement(Case aCase) {
        this.vitesseRestante--;
        this.container.sortiePresence();
        aCase.entrePresence(this);
    }

    public JoueurIG getPossesseur() {
        return possesseur;
    }

    public int modifAttaque(int val, int type) {
        for (EffetBuffTypeAttaque effet : getChangeTypeAtt()) {
            type = effet.modif(type);
            switch (type) {
                case (Caract.FORCE):
                    val = donnee.commun.force;
                    break;
                case (Caract.AGILITE):
                    val = donnee.commun.agilite;
                    break;
                case (Caract.MAGIE):
                    val = donnee.commun.magie;
                    break;
            }
        }
        for (EffetBuffValAttaque effet : getChangeAtt()) {
            val = effet.modif(val, type);
        }
        return val;
    }

    public int modifDefense(int val, int type) {
        for (EffetBuffTypeDefense effet : getChangeTypeDef()) {
            type = effet.modif(type);
            switch (type) {
                case (Caract.FORCE):
                    val = donnee.commun.force;
                    break;
                case (Caract.AGILITE):
                    val = donnee.commun.agilite;
                    break;
                case (Caract.MAGIE):
                    val = donnee.commun.magie;
                    break;
            }
        }
        for (EffetBuffValDefense effet : getChangeDef()) {
            val = effet.modif(val, type);
        }
        return val;
    }

    public void modifVitesse() {
        int tmp = this.donnee.commun.vitesse;
        for (EffetBuffValVitesse effet : getChangeVitesse()) {
            tmp = effet.modif(tmp);
        }
        vitesseRestante = tmp;
    }

    public void declencheRecoitDegat(int val) {
        for (EffetDeclencheurDegatRecu effet : recoiDegat) {
            effet.check(val);
        }
    }

    public void infligeDot() {
        for (EffetBuffDot effet : getDot()) {
            effet.inflige();
        }
    }

    public void inflige(int val) {
        declencheRecoitDegat(val);
        this.setVieActuelle(getVieActuelle() - val);
        if (vieActuelle <= 0) {
            meurt();
        }
    }

    public void meurt() {
        //todo mort
    }

    public void addBuf(EffetBuf effet, boolean dispelable) {
        effet.setContainer(this, dispelable);
        if (dispelable) {
            if (!(effet instanceof EffetBuffDommage) && effet.isBenefique()) {
                buffBenefique.add(effet);
            } else {
                buffMauvais.add(effet);
            }
        } else {
            buffPassif.add(effet);
        }
        if (effet instanceof EffetBuffValAttaque) {
            getChangeAtt().add((EffetBuffValAttaque) effet);
            modifCaract();
        } else if (effet instanceof EffetBuffValDefense) {
            getChangeDef().add((EffetBuffValDefense) effet);
            modifCaract();
        } else if (effet instanceof EffetBuffValVitesse) {
            getChangeVitesse().add((EffetBuffValVitesse) effet);
            modifCaract();
        } else if (effet instanceof EffetBuffTypeAttaque) {
            getChangeTypeAtt().add((EffetBuffTypeAttaque) effet);
            modifCaract();
        } else if (effet instanceof EffetBuffTypeDefense) {
            getChangeTypeDef().add((EffetBuffTypeDefense) effet);
            modifCaract();
        } else if (effet instanceof EffetBuffDot) {
            getDot().add((EffetBuffDot) effet);
        } else if (effet instanceof EffetBuffStun) {
            this.setAAgi(true);
            getStun().add((EffetBuffStun) effet);
        }
    }

    public void addDeclencheur(EffetDeclencheur effetDeclencheur) {
        effetDeclencheur.setContainer(this);
        if (effetDeclencheur instanceof EffetDeclencheurDegatRecu) {
            recoiDegat.add((EffetDeclencheurDegatRecu) effetDeclencheur);
        }
    }

    public String[] toStrings() {
        String[] retour = new String[6];
        retour[0] = donnee.getNom();
        retour[1] = vieActuelle + "/" + donnee.commun.vie;
        retour[2] = vitesseRestante + "/" + donnee.commun.vitesse;
        retour[3] = forceAttaque + "/" + forceDefense;
        retour[4] = agiliteAttaque + "/" + agiliteDefense;
        retour[5] = magieAttaque + "/" + magieDefense;
        return retour;
    }

    public int getForceAttaque() {
        return forceAttaque;
    }

    public int getForceDefense() {
        return forceDefense;
    }

    public int getAgiliteAttaque() {
        return agiliteAttaque;
    }

    public int getAgiliteDefense() {
        return agiliteDefense;
    }

    public int getMagieAttaque() {
        return magieAttaque;
    }

    public int getMagieDefense() {
        return magieDefense;
    }

    public void modifCaract() {
        forceAttaque = modifAttaque(donnee.commun.force, Caract.FORCE);
        forceDefense = modifDefense(donnee.commun.force, Caract.FORCE);
        agiliteAttaque = modifAttaque(donnee.commun.agilite, Caract.AGILITE);
        agiliteDefense = modifDefense(donnee.commun.agilite, Caract.AGILITE);
        magieAttaque = modifAttaque(donnee.commun.magie, Caract.MAGIE);
        magieDefense = modifDefense(donnee.commun.magie, Caract.MAGIE);
        modifVitesse();

    }

    public void removeBuff(EffetBuf effet) {
        buffBenefique.remove(effet);
        buffMauvais.remove(effet);
        if (effet instanceof EffetBuffValAttaque) {
            getChangeAtt().remove(effet);
            modifCaract();
        } else if (effet instanceof EffetBuffValDefense) {
            getChangeDef().remove(effet);
            modifCaract();
        } else if (effet instanceof EffetBuffValVitesse) {
            getChangeVitesse().remove(effet);
            modifCaract();
        } else if (effet instanceof EffetBuffTypeAttaque) {
            getChangeTypeAtt().remove(effet);
            modifCaract();
        } else if (effet instanceof EffetBuffTypeDefense) {
            getChangeTypeDef().remove(effet);
            modifCaract();
        } else if (effet instanceof EffetBuffDot) {
            getDot().remove(effet);
        } else if (effet instanceof EffetBuffStun) {
            getStun().remove(effet);
        }
    }

    public void tourBuff() {
        ArrayList<EffetBuf> toRemove = new ArrayList<EffetBuf>();
        for (EffetBuf effet : buffBenefique) {
            if (effet.tour()) toRemove.add(effet);
        }
        for (EffetBuf effet : buffMauvais) {
            if (effet.tour()) toRemove.add(effet);
        }
        for (EffetBuf effet : toRemove) {
            removeBuff(effet);
        }
        toRemove.clear();
    }

    public void removeBuffBenefique() {
        Collections.shuffle(buffBenefique);
        buffBenefique.remove(0);
    }

    public void removeBuffMauvais() {
        Collections.shuffle(buffMauvais);
        if (buffMauvais.size() > 0) {
            buffMauvais.remove(0);
        }
    }

    public ArrayList<EffetBuffValDefense> getChangeDef() {
        return changeDef;
    }

    public ArrayList<EffetBuffValAttaque> getChangeAtt() {
        return changeAtt;
    }

    public ArrayList<EffetBuffTypeAttaque> getChangeTypeAtt() {
        return changeTypeAtt;
    }

    public ArrayList<EffetBuffTypeDefense> getChangeTypeDef() {
        return changeTypeDef;
    }

    public ArrayList<EffetBuffValVitesse> getChangeVitesse() {
        return changeVitesse;
    }

    public ArrayList<EffetBuffValVitesse> getChangeVie() {
        return changeVie;
    }

    public ArrayList<EffetBuffDot> getDot() {
        return dot;
    }

    public ArrayList<EffetBuffStun> getStun() {
        return stun;
    }
}
