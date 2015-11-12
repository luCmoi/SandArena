package sandarena.interfaceutil.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.competence.BanqueCompetence;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowComp;
import sandarena.infowindow.windows.InfoWindowCompIG;
import sandarena.match.partie.jeu.compcase.CompetenceIG;

/**
 * Created by lucmo on 12/11/2015.
 */
public class EmplacementCompetence extends Actor {
    protected Emplacement container;
    protected BanqueCompetence.EntreeCompetence comp;
    protected InfoWindowComp info;
    protected CompetenceIG compIG;
    protected InfoWindowCompIG infoIG;


    public EmplacementCompetence(Emplacement container, int place) {
        this.container = container;
        this.setBounds(container.getHeight() + (container.getHeight() / 2) * (place % 2), ((container.getHeight() / 2) * (1 - (place / 2))), container.getHeight() / 2, container.getHeight() / 2);
    }

    public void addListenerBasique() {
        this.addListener(new EmplacementCompetenceListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (comp != null) {
            batch.draw(comp.image, getX(), getY(), getWidth(), getHeight());
            ((EmplacementCompetenceListener) (getListeners().get(0))).update();
        } else if (compIG != null) {
            batch.draw(compIG.info.image, getX(), getY(), getWidth(), getHeight());
            ((EmplacementCompetenceListener) (getListeners().get(0))).update();
        }
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public BanqueCompetence.EntreeCompetence getComp() {
        return comp;
    }

    public CompetenceIG getCompIG() {
        return compIG;
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
            container.getScreen().getSurcouche().addActor(info);
        } else if (compIG != null) {
            this.infoIG = new InfoWindowCompIG(compIG);
            container.getScreen().getSurcouche().addActor(infoIG);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        } else if (infoIG != null) {
            this.infoIG.dispose();
            infoIG = null;
        }
    }

    public void clique() {

    }

    public void setCompIG(CompetenceIG compIG) {
        this.compIG = compIG;
    }

    public void dispose() {
        container = null;
        comp = null;
        compIG = null;
        if (info != null) {
            info.dispose();
            info = null;
        }
        if (infoIG != null) {
            infoIG.dispose();
            infoIG = null;
        }
        clear();
        remove();
    }
}
