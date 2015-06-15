package sandarena.partie.gui.interfacep;

import com.badlogic.gdx.graphics.g2d.Batch;


import sandarena.donnee.Utili;
import sandarena.partie.compcase.PersonnageIG;

/**
 * Created by Guillaume on 10/06/2015.
 */
public class EmplacementPerso extends EmplacementInterface {
    private PersonnageIG perso;



    public EmplacementPerso(int place, StageInterface container) {
        super(place, container);
        this.setBounds((container.tailleCoteHeight / 2) * getPlace() * 4, 0, container.tailleCoteHeight, container.tailleCoteHeight);
        if (place < 1) {
            actif = true;
        } else {
            actif = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        if (getPerso() != null) {
            batch.draw(getPerso().getDonnee().commun.image, getX(), getY(), getWidth(), getHeight());
        } else {
        }
    }

    public void dispose(){
        super.dispose();
        this.perso = null;
    }
    public void clique() {

    }

    public PersonnageIG getPerso() {
        return perso;
    }

    public void setPerso(PersonnageIG perso) {
        this.perso = perso;
    }
}
