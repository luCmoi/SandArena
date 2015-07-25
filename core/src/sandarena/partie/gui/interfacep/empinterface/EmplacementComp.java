package sandarena.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.Utili;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.joueur.competence.CompetencePassive;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.gui.interfacep.StageInterface;
import sandarena.partie.gui.interfacep.infowindow.InfoWindow;

/**
 */
public class EmplacementComp extends EmplacementInterface {
    private CompetenceIG competenceIG;


    public EmplacementComp(int place, StageInterface container) {
        super(place, container);
        this.setBounds((container.tailleCoteHeight / 2 * (2 + (getPlace() / 2) + (3 * (getPlace() / 4)))), 0 + ((container.tailleCoteHeight / 2) * (getPlace() % 2)), container.tailleCoteHeight / 2, container.tailleCoteHeight / 2);
        actif = place < 4;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        if (getCompetenceIG() != null) {
            batch.draw(getCompetenceIG().info.image, getX(), getY(), getWidth(), getHeight());
            if (getCompetenceIG().getRecharge() != 0) {
                batch.draw(Utili.recharge, getX(), getY(),
                        getWidth() / ((CompetenceActive) getCompetenceIG().info.competence).getRechargement() * getCompetenceIG().getRecharge(),
                        getHeight());
            }
            if (getCompetenceIG().info.competence instanceof CompetencePassive || getCompetenceIG().getUtilisationRestante() == 0) {
                batch.draw(Utili.passive, getX(), getY(), getWidth(), getHeight());
            }
        } else {
        }
    }

    public void dispose() {
        super.dispose();
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
                if (getCompetenceIG().isActive()) {
                    if (CompetenceActive.class.isAssignableFrom(getCompetenceIG().info.competence.getClass())) {
                        container.getPartie().setCompetenceActive(getCompetenceIG());
                    }
                }
            }
        }
    }

    @Override
    public void pression() {
        if (this.getCompetenceIG() != null) {
            this.info = new InfoWindow(this);
            container.getPartie().getContainer().getSurcouche().addActor(info);
        }
    }

    public CompetenceIG getCompetenceIG() {
        return competenceIG;
    }
}
