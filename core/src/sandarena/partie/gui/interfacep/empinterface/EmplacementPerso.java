package sandarena.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowPersoIG;
import sandarena.partie.jeu.compcase.PersonnageIG;
import sandarena.partie.gui.interfacep.StageInterface;

/**
 * Created by Guillaume on 10/06/2015.
 */
public class EmplacementPerso extends EmplacementInterface {
    private PersonnageIG perso;


    public EmplacementPerso(int place, StageInterface container) {
        super(place, container);
        this.setBounds((container.tailleCoteHeight / 2) * getPlace() * 5, 0, container.tailleCoteHeight, container.tailleCoteHeight);
        actif = place < 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        if (getPerso() != null) {
            batch.draw(getPerso().getDonnee().commun.image, getX(), getY(), getWidth(), getHeight());
            batch.draw(Utili.competenceable,getX(),getY(),getWidth()-((getWidth()/getPerso().getDonnee().commun.vie) *  (getPerso().getDonnee().commun.vie- getPerso().getVieActuelle())),getHeight()/8);
        }
    }

    public void dispose() {
        super.dispose();
        this.perso = null;
    }

    public void clique() {

    }

    @Override
    public void pression() {
        if (this.getPerso() != null) {
            this.info = new InfoWindowPersoIG(this);
            container.getPartie().getContainer().getSurcouche().addActor(info);
        }
    }

    public PersonnageIG getPerso() {
        return perso;
    }

    public void setPerso(PersonnageIG perso) {
        this.perso = perso;
    }
}
