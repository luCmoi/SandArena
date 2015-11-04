package sandarena.selectionequipe.Surcouche.nouvelleequipe.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.competence.BanqueCompetence;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowComp;

/**
 * Created by lucmo on 04/11/2015.
 */
public class EmplacementComp extends Actor {
    private Emplacement container;
    private BanqueCompetence.EntreeCompetence comp;
    private int place;
    private InfoWindowComp info;


    public EmplacementComp(Emplacement container, int place, BanqueCompetence.EntreeCompetence comp) {
        this.container = container;
        this.place = place;
        this.comp = comp;
        this.setBounds(container.getHeight() + (container.getHeight() / 2) * (place % 2), ((container.getHeight() / 2) * (1 - (place / 2))), container.getHeight() / 2, container.getHeight() / 2);
        this.addListener(new EmplacementCompListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(comp.image, getX(), getY(), getWidth(), getHeight());
        ((EmplacementCompListener) (getListeners().get(0))).update();
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public BanqueCompetence.EntreeCompetence getComp() {
        return comp;
    }

    public Emplacement getContainer() {
        return container;
    }

    public void setComp(BanqueCompetence.EntreeCompetence comp) {
        this.comp = comp;
    }

    public void pression() {
        if (comp != null) {
            this.info = new InfoWindowComp(comp);
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
