package sandarena.match.preparematch.stageprincipal.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowPerso;
import sandarena.joueur.Personnage;

/**
 * Created by Guillaume on 23/07/2015.
 */
class UnitPanelScreenPrepaMatch extends Actor {
    private EmplacementPanelScreenPrepaMatch container;
    private Personnage perso;
    private InfoWindowPerso info;

    public UnitPanelScreenPrepaMatch(EmplacementPanelScreenPrepaMatch container) {
        this.container = container;
        this.setBounds(0, 0, container.getHeight(), container.getHeight());
        this.addListener(new UnitPanelScreenPrepaListener(this));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (perso != null && container.getContainer().getContainer().getContainer().getPrincipal().getContainer().getCheck() == null) {
            batch.draw(perso.commun.image, getX(), getY(), getWidth(), getHeight());
            ((UnitPanelScreenPrepaListener) (getListeners().get(0))).update();
        }
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public Personnage getPerso() {
        return perso;
    }

    public EmplacementPanelScreenPrepaMatch getContainer() {
        return container;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }

    public void pression() {
        if (perso != null) {
            this.info = new InfoWindowPerso(perso);
            container.getContainer().getContainer().getContainer().getSurcouche().addActor(info);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }

    public void dispose() {
        ((UnitPanelScreenPrepaListener) (getListeners().get(0))).dispose();
        getListeners().clear();
        container = null;
        perso = null;
        if (info != null) {
            info.dispose();
            info = null;
        }
        remove();
    }
}
