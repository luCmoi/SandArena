package sandarena.partie.compcase;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.joueur.Personnage;
import sandarena.partie.Case;

/**
 * Une instance de Personnage en partie
 *
 * @author Guillaume
 */
public class PersonnageIG {

    private Personnage donnee;
    private int vieActuelle;
    private int vitesseRestante;
    private final CompetenceIG[] competence = new CompetenceIG[4];
    private Case container;
    private boolean aAgi;

    public PersonnageIG(Personnage donnee) {
        this.donnee = donnee;
        this.vieActuelle = donnee.commun.vie;
        this.vitesseRestante = donnee.commun.vitesse;
        int i = 0;
        for (EntreeCompetence c : donnee.getCompetences()) {
            if (c != null) {
                competence[i] = new CompetenceIG(c);
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
    }

    public void render(SpriteBatch batch) {
        batch.draw(getDonnee().commun.image, getContainer().getPosition().x, getContainer().getPosition().y, getContainer().getPosition().width, getContainer().getPosition().height);
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
    }
}
