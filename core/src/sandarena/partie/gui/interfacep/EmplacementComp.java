package sandarena.partie.gui.interfacep;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.Utili;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.compcase.PersonnageIG;

/**
 * Created by Guillaume on 08/06/2015.
 */
public class EmplacementComp extends EmplacementInterface {
    private CompetenceIG competenceIG;
    private boolean actif;
    sandarena.partie.gui.infowindow.InfoWindow info;


    public EmplacementComp(int place, StageInterface container) {
        super(place, container);
        this.setBounds((container.tailleCoteHeight/2 * (2 + (int)(getPlace() / 2) + (2 * (int)(getPlace() / 4)))), 0 + ((container.tailleCoteHeight/2) * (getPlace() % 2)), container.tailleCoteHeight/2, container.tailleCoteHeight / 2);
        this.addListener(new EmplacementCompListener(this));
        if (place < 4) {
            actif = true;
        } else {
            actif = false;
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ((EmplacementCompListener)getListeners().get(0)).update();
        batch.draw(Utili.contour,getX(), getY(), getWidth(), getHeight());
        if (getCompetenceIG() != null) {
            batch.draw(getCompetenceIG().info.image, getX(), getY(), getWidth(), getHeight());
        } else {
        }
    }

    public void dispose() {
        setCompetenceIG(null);
    }


    public void setCompetenceIG(CompetenceIG competenceIG) {
        this.competenceIG = competenceIG;
    }

    public void clique() {
        if (actif) {
            if (container.getPartie().getCompetenceActive() != null && container.getPartie().getCompetenceActive().equals(this.getCompetenceIG())) {
                container.getPartie().setCompetenceActive(null);
            } else if (getCompetenceIG() != null) {
                if (CompetenceActive.class.isAssignableFrom(getCompetenceIG().info.competence.getClass())) {
                    container.getPartie().setCompetenceActive(getCompetenceIG());
                }
            }
        }
    }


    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }

    public void pression() {
        this.info = new sandarena.partie.gui.infowindow.InfoWindow(this);
        container.getPartie().getContainer().getSurcouche().addActor(info);
    }

    public CompetenceIG getCompetenceIG() {
        return competenceIG;
    }
}
