package sandarena.match.partie.surcouche.emplacementfinpartie;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Utili;
import sandarena.match.partie.jeu.compcase.PersonnageIG;
import sandarena.match.partie.surcouche.Fin;

/**
 * Created by lucmo on 06/11/2015.
 */
public class EmplacementFinPartie extends Group{
    private final Relance relance;
    private final Tirage tirage;
    private Fin container;
    private final int place;
    private PersonnageIG perso;

    public EmplacementFinPartie(Fin fin, int i, PersonnageIG personnageIG) {
        this.container = fin;
        this.place = i;
        this.perso = personnageIG;
        setBounds((container.getHeight() / 3 * i) + (((container.getWidth() - container.getHeight() / 3 * 4) / (4 + 1)) * (i + 1)), container.getHeight() / 4, container.getHeight() / 3, container.getHeight() / 3+ (container.getHeight()/9*2));
        this.relance = new Relance(this);
        this.tirage = new Tirage(this);
        this.addActor(relance);
        this.addActor(tirage);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(perso.getDonnee().commun.image,getX(),getY()+getWidth()/3*2,getWidth(),getWidth());
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
    }

    public void dispose() {
        container = null;
        perso = null;
        clear();
        remove();
    }

    public PersonnageIG getPerso() {
        return perso;
    }

    public void relance() {
        tirage.retire();
    }
}
