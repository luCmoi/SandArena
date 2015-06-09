package sandarena.partie.compcase;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.joueur.Personnage;
import sandarena.partie.Case;
import sandarena.partie.effet.Effet;
import sandarena.partie.effet.EffetBuf;

/**
 * Une instance de Personnage en partie
 *
 * @author Guillaume
 */
public class PersonnageIG {

    private Personnage donnee;
    private JoueurIG possesseur;
    private int vieActuelle;
    private int vitesseRestante;
    private final CompetenceIG[] competence = new CompetenceIG[4];
    private Case container;
    private boolean aAgi;
    //Buff
    private ArrayList<EffetBuf> changeDef = new ArrayList<EffetBuf>();
    private ArrayList<EffetBuf> changeAtt = new ArrayList<EffetBuf>();

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

    public void setContainer(Case container) {
        this.container = container;
    }

    public Personnage getDonnee() {
        return donnee;
    }

    public int getVieActuelle() {
        return vieActuelle;
    }

    public void setVieActuelle(int vieActuelle) {
        this.vieActuelle = vieActuelle;
        if (this.vieActuelle <= 0){
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

    public void setPossesseur(JoueurIG possesseur) {
        this.possesseur = possesseur;
    }

    public int modifAttaque(int val, int type){
        for (EffetBuf effet : changeAtt){
            //todo appel buf
        }
        return val;
    }
    public int modifDefense(int val, int type){
        for (EffetBuf effet : changeDef){
            //todo appel buf
        }
        return val;
    }

    public void inflige(int val){
        this.setVieActuelle(getVieActuelle()-val);
        //todo declencheurs degat recu test
        if (vieActuelle<=0){
            meurt();
        }
    }

    public void meurt(){
        //todo mort
    }
}
