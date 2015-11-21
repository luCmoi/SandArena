package sandarena.interfaceutil.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.infowindow.windows.InfoWindowBlessure;
import sandarena.joueur.Personnage;
import sandarena.joueur.blessure.Blessure;

/**
 * Created by lucmo on 20/11/2015.
 */
public class EmplacementBlessure extends Actor {
    protected EmplacementPerso container;
    protected int place;
    private Blessure blessure;
    private InfoWindowBlessure info;

    public EmplacementBlessure(EmplacementPerso emplacementPerso, int i) {
        this.container = emplacementPerso;
        this.place = i;
        setBounds(i * (container.getWidth() / 4), 0, container.getWidth() / 4, container.getWidth() / 4);
        this.addListener(new EmplacementBlessureListener(this));
        setTouchable(Touchable.disabled);
    }

    public void setPerso(Personnage perso) {
        if (perso != null) {
            if (perso.getBlessures()[place] != null) {
                this.blessure = perso.getBlessures()[place];
                setTouchable(Touchable.enabled);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (blessure != null) {
            batch.draw(blessure.donnee.image, getX(), getY(), getWidth(), getHeight());
        }
    }

    public void dispose() {
        ((EmplacementBlessureListener)getListeners().get(0)).dispose();
        blessure = null;
        container = null;
        clear();
        remove();
    }

    public void clique() {

    }

    public void pression() {
        if (blessure != null) {
            this.info = new InfoWindowBlessure(blessure);
            container.getContainer().getScreen().getSurcouche().addActor(info);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }
}
