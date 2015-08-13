package sandarena.preparematch.barre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.donnee.BanqueCompetence;
import sandarena.donnee.Utili;
import sandarena.infowindow.windows.InfoWindowComp;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class CompetenceBarre extends Actor {
    private BanqueCompetence.EntreeCompetence comp;
    private InfoWindowComp info;
    private EmplacementBarre container;

    public CompetenceBarre(EmplacementBarre container, int place, BanqueCompetence.EntreeCompetence comp) {
        super();
        this.container = container;
        this.comp = comp;
        this.setBounds((Resolution.differenceBas / 2) * (2 + (place / 2)), 0 + ((Resolution.differenceBas / 2) * (place % 2)), Resolution.differenceBas / 2, Resolution.differenceBas / 2);
        this.addListener(new CompBarreListener(this));
        this.setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (this.isTouchable()) {
            batch.draw(comp.image, getX(), getY(), getWidth(), getHeight());
            batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
            ((CompBarreListener) (getListeners().get(0))).update();
        }
    }

    public void dispose() {
        ((CompBarreListener) (getListeners().get(0))).dispose();
        getListeners().clear();
        comp = null;
        dispose();
        remove();
    }

    public void pression() {
        if (comp != null) {
            this.info = new InfoWindowComp(comp);
            container.fenetre();
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }
}
