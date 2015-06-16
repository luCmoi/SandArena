package sandarena.partie.compcase;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.donnee.IntegerNew;
import sandarena.joueur.Personnage;
import sandarena.partie.Case;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.EffetDeclencheur;
import sandarena.partie.effet.effetbuff.EffetBuffDot;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeAttaque;
import sandarena.partie.effet.effetbuff.effetbufftype.EffetBuffTypeDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValAttaque;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValDefense;
import sandarena.partie.effet.effetbuff.effetbuffval.EffetBuffValVitesse;

/**
 * Une instance de Personnage en partie
 *
 * @author Guillaume
 */
public class PersonnageIG {
//TODO imperatif rebosser les caract
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
    private ArrayList<EffetBuffDot> dot = new ArrayList<EffetBuffDot>();
    //Declencheur
    private ArrayList<EffetDeclencheur> declencheurs = new ArrayList<EffetDeclencheur>();

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
            for(CompetenceIG c : competence){
                c.tour();
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

    public void modifAttaque(IntegerNew val, IntegerNew type) {
        int tmp = val.anInt;
        for (EffetBuffValAttaque effet : changeAtt) {
            tmp = effet.modif(tmp);
        }
        val.anInt = tmp;
    }

    public void modifDefense(IntegerNew val, IntegerNew type) {
        int tmp = val.anInt;
        for (EffetBuffValDefense effet : changeDef) {
            tmp = effet.modif(tmp);
        }
        val.anInt = tmp;
    }

    public void modifVitesse() {
        int tmp = this.donnee.commun.vitesse;
        for (EffetBuffValVitesse effet : changeVitesse) {
            tmp = effet.modif(tmp);
        }
        vitesseRestante = tmp;
    }

    public void infligeDot() {
        for (EffetBuffDot effet : dot) {
            effet.inflige();
        }
    }

    public void inflige(int val) {
        this.setVieActuelle(getVieActuelle() - val);
        if (vieActuelle <= 0) {
            meurt();
        }
    }

    public void meurt() {
        //todo mort
    }

    public void addBuf(EffetBuf effet) {
        effet.setContainer(this);
        if (effet instanceof EffetBuffValAttaque){
            changeAtt.add((EffetBuffValAttaque)effet);
        }else if (effet instanceof EffetBuffValDefense){
            changeDef.add((EffetBuffValDefense)effet);
        }else if (effet instanceof EffetBuffValVitesse){
            changeVitesse.add((EffetBuffValVitesse)effet);
        }else if (effet instanceof EffetBuffTypeAttaque){
            changeTypeAtt.add((EffetBuffTypeAttaque)effet);
        } else if (effet instanceof EffetBuffTypeDefense){
            changeTypeDef.add((EffetBuffTypeDefense)effet);
        }  else if (effet instanceof EffetBuffDot){
            dot.add((EffetBuffDot)effet);
        }
    }

    public ArrayList<EffetDeclencheur> getDeclencheurs() {
        return declencheurs;
    }

    public void addDeclencheur(EffetDeclencheur effetDeclencheur) {
        declencheurs.add(effetDeclencheur);
    }

    public String[] toStrings() {
        String[] retour = new String[6];
        retour[0] = donnee.getNom();
        retour[1] = vieActuelle + "/" + donnee.commun.vie;
        retour[2] = vitesseRestante + "/" + donnee.commun.vitesse;
        retour[3] = Integer.toString(donnee.commun.force);
        retour[4] = Integer.toString(donnee.commun.agilite);
        retour[5] = Integer.toString(donnee.commun.magie);
        return retour;
    }

}
