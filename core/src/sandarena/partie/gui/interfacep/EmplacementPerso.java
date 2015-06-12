package sandarena.partie.gui.interfacep;

import com.badlogic.gdx.graphics.g2d.Batch;


import sandarena.donnee.Utili;
import sandarena.partie.compcase.PersonnageIG;

/**
 * Created by Guillaume on 10/06/2015.
 */
public class EmplacementPerso extends EmplacementInterface {
    public PersonnageIG perso;
    private boolean actif;
    sandarena.partie.gui.infowindow.InfoWindow info;


    public EmplacementPerso(int place, StageInterface container) {
        super(place, container);
        this.setBounds((container.tailleCoteHeight/2) * getPlace() *4, 0, container.tailleCoteHeight, container.tailleCoteHeight);
        this.addListener(new EmplacementPersoListener(this));
        if (place < 4) {
            actif = true;
        } else {
            actif = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        if (getPerso() != null) {
            batch.draw(getPerso().getDonnee().commun.image, getX(), getY(), getWidth(), getHeight());
        } else {
        }
    }

    public void clique() {

    }

    public void finPression() {
        this.info.dispose();
        info = null;
    }

    public void pression() {
        this.info = new sandarena.partie.gui.infowindow.InfoWindow(this);
        container.getPartie().getContainer().getSurcouche().addActor(info);
    }

    public PersonnageIG getPerso() {
        return perso;
    }
}
