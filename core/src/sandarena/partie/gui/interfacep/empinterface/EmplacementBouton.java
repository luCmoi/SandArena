package sandarena.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.ConnexionMatch;
import sandarena.donnee.Utili;
import sandarena.partie.gui.interfacep.StageInterface;

/**
 * Created by Guillaume on 16/07/2015.
 */
public class EmplacementBouton extends EmplacementInterface {

    public EmplacementBouton(int place, StageInterface container) {
        super(place, container);
        this.setBounds(container.getWidth() - ((container.tailleCoteHeight / 2) * (1 + (getPlace() / 2))), 0 + ((container.tailleCoteHeight / 2) * (getPlace() % 2)), container.tailleCoteHeight / 2, container.tailleCoteHeight / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        switch (place) {
            case 0:
                batch.draw(Utili.sablier, getX(), getY(), getWidth(), getHeight());
                break;
            case 1:
                batch.draw(Utili.rouage, getX(), getY(), getWidth(), getHeight());
                break;
            case 2:
                batch.draw(Utili.drapeau, getX(), getY(), getWidth(), getHeight());
                break;
        }
    }

    @Override
    public void clique() {
        switch (place) {
            case 0:
                if (!container.getPartie().isBloquand()) {
                    ConnexionMatch.partieEnvoiFinPhase();
                    container.getPartie().finPhase();
                    container.recharge();
                }
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    @Override
    public void pression() {

    }
}
