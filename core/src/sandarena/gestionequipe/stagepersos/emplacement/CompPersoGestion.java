package sandarena.gestionequipe.stagepersos.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.competence.BanqueCompetence;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowComp;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class CompPersoGestion extends Actor {
    private EmplacementPersoGestion container;
    private BanqueCompetence.EntreeCompetence comp;
    private int place;
    private InfoWindowComp info;


    public CompPersoGestion(EmplacementPersoGestion container, int place) {
        this.container = container;
        this.place = place;
        this.setBounds(container.getHeight() + (container.getHeight() / 2) * (place % 2), ((container.getHeight() / 2) * (1 - (place / 2))), container.getHeight() / 2, container.getHeight() / 2);
        this.addListener(new CompPersoGestionListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (comp != null) {
            batch.draw(comp.image, getX(), getY(), getWidth(), getHeight());
            ((CompPersoGestionListener) (getListeners().get(0))).update();
        }
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public BanqueCompetence.EntreeCompetence getComp() {
        return comp;
    }

    public EmplacementPersoGestion getContainer() {
        return container;
    }

    public void setComp(BanqueCompetence.EntreeCompetence comp) {
        this.comp = comp;
    }

    public void pression() {
        if (comp != null) {
            this.info = new InfoWindowComp(comp);
            container.getContainer().getContainer().getSurcouche().addActor(info);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }
}
