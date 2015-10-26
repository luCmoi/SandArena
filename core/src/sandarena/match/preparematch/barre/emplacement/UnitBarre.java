package sandarena.match.preparematch.barre.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowPerso;
import sandarena.joueur.Personnage;

/**
 * Created by Guillaume on 13/08/2015.
 */
class UnitBarre extends Actor {
    private Personnage perso;
    private InfoWindowPerso info;
    private EmplacementBarre container;

    public UnitBarre(EmplacementBarre container, Personnage perso) {
        super();
        this.container = container;
        this.perso = perso;
        this.setBounds(0, 0, Resolution.differenceBas, Resolution.differenceBas);
        this.addListener(new sandarena.match.preparematch.barre.emplacement.UnitBarreListener(this));
        this.setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(perso.commun.image, getX(), getY(), getWidth(), getHeight());
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        ((sandarena.match.preparematch.barre.emplacement.UnitBarreListener) (getListeners().get(0))).update();
    }

    public void dispose() {
        ((sandarena.match.preparematch.barre.emplacement.UnitBarreListener) (getListeners().get(0))).dispose();
        getListeners().clear();
        perso = null;
        this.container = null;
        remove();
    }

    public void pression() {
        if (perso != null) {
            this.info = new InfoWindowPerso(perso);
            container.fenetre();
            container.getContainer().getPrincipal().getContainer().getSurcouche().addActor(info);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }
}

