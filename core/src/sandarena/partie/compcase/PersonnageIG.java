package sandarena.partie.compcase;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.donnee.IntegerNew;
import sandarena.joueur.Personnage;
import sandarena.partie.Case;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.EffetDeclencheur;

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
    private ArrayList<EffetBuf> changeDef = new ArrayList<EffetBuf>();
    private ArrayList<EffetBuf> changeAtt = new ArrayList<EffetBuf>();
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
            this.setVitesseRestante(this.donnee.commun.vitesse);
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
        for (EffetBuf effet : changeAtt) {
            effet.modif(val, type);
        }
        val.anInt = (int) val.aDouble;
    }

    public void modifDefense(IntegerNew val, IntegerNew type) {
        for (EffetBuf effet : changeDef) {
            effet.modif(val, type);
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
        //todo a completer au fur et a mesure check type buff ajoute
        switch (effet.getChangement()) {
            case (EffetBuf.MULATTAQUE):
            case (EffetBuf.TYPEATTAQUE):
                changeAtt.add(effet);
                break;
            case (EffetBuf.MULDEFENSE):
            case (EffetBuf.TYPEDEFENSE):
                changeDef.add(effet);
                break;

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
