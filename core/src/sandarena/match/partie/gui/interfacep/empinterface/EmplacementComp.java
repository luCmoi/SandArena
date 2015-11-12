package sandarena.match.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.googleservice.ConnexionMatch;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowCompIG;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.joueur.competence.CompetencePassive;
import sandarena.match.partie.jeu.compcase.CompetenceIG;
import sandarena.match.partie.gui.interfacep.StageInterface;

/**
 */
public class EmplacementComp extends EmplacementInterface {
    private CompetenceIG competenceIG;


    public EmplacementComp(int place, StageInterface container) {
        super(place, container);
        this.setBounds((container.tailleCoteHeight / 2 * (2 + (getPlace() / 2) + (3 * (getPlace() / 4)))), ((container.tailleCoteHeight / 2) * (getPlace() % 2)), container.tailleCoteHeight / 2, container.tailleCoteHeight / 2);
        actif = place < 4;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        if (getCompetenceIG() != null) {
            batch.draw(getCompetenceIG().info.image, getX(), getY(), getWidth(), getHeight());
            if (container.getPartie().getCompetenceActive()== this.getCompetenceIG()){
                batch.draw(Utili.accessible,getX(),getY(),getWidth(),getHeight());
            }
            if (getCompetenceIG().getRecharge() != 0) {
                batch.draw(Utili.recharge, getX(), getY(),
                        getWidth() / ((CompetenceActive) getCompetenceIG().info.competence).getRechargement() * getCompetenceIG().getRecharge(),
                        getHeight());
            }
            if (getCompetenceIG().info.competence instanceof CompetencePassive || getCompetenceIG().getUtilisationRestante() == 0) {
                batch.draw(Utili.passive, getX(), getY(), getWidth(), getHeight());
            }
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
        if (!container.getPartie().isSpawn() && actif && !container.getPartie().isBloquand()) {
            if (getCompetenceIG() != null) {
                if (getCompetenceIG().isActive()) {
                    if (container.getPartie().getCompetenceActive() != null && container.getPartie().getCompetenceActive().equals(this.getCompetenceIG())) {
                        ConnexionMatch.partieEnvoiCompetence(container.getPartie().getJoueurActif().getPersonnages().indexOf(container.getPartie().getPersonnageActif()), -1);
                        container.getPartie().setCompetenceActive(null);
                    } else if (getCompetenceIG() != null) {
                        container.getPartie().setCompetenceActive(getCompetenceIG());
                        ConnexionMatch.partieEnvoiCompetence(container.getPartie().getJoueurActif().getPersonnages().indexOf(container.getPartie().getPersonnageActif()), competenceIG.getPlace());
                    }
                }
            }
        }
    }

    @Override
    public void pression() {
        if (this.getCompetenceIG() != null) {
            this.info = new InfoWindowCompIG(this.competenceIG);
            container.getPartie().getContainer().getSurcouche().addActor(info);
        }
    }

    public CompetenceIG getCompetenceIG() {
        return competenceIG;
    }
}
