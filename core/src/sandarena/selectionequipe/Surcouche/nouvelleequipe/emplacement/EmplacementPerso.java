package sandarena.selectionequipe.Surcouche.nouvelleequipe.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowPerso;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 04/11/2015.
 */
public class EmplacementPerso extends Actor {
    private Emplacement container;
    private Personnage perso;
    private InfoWindowPerso info;

    public EmplacementPerso(Emplacement container, Personnage perso) {
        this.container = container;
        this.perso = perso;
        this.setBounds(0, 0, container.getHeight(), container.getHeight());
        this.addListener(new EmplacementPersoListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(perso.commun.image, getX(), getY(), getWidth(), getHeight());
        ((EmplacementPersoListener) (getListeners().get(0))).update();
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public Personnage getPerso() {
        return perso;
    }

    public Emplacement getContainer() {
        return container;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }

    public void pression() {
        if (perso != null) {
            this.info = new InfoWindowPerso(perso);
            container.getContainer().getContainer().addActor(info);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }
}
