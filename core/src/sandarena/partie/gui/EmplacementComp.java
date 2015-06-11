package sandarena.partie.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.compcase.CompetenceIG;

/**
 * Created by Guillaume on 08/06/2015.
 */
public class EmplacementComp extends EmplacementInterface {
    private CompetenceIG competenceIG;
    private boolean actif;

    public EmplacementComp(int place, StageInterface container) {
        super(place, container);
        this.setBounds((container.tailleCoteHeight/2 * (2 + (int)(getPlace() / 2) + (2 * (int)(getPlace() / 4)))), 0 + ((container.tailleCoteHeight/2) * (getPlace() % 2)), container.tailleCoteHeight/2, container.tailleCoteHeight/2);
        this.addListener(new EmplacementCompListener(this));
        if (place < 4) {
            actif = true;
        } else {
            actif = false;
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour,getX(), getY(), getWidth(), getHeight());
        if (competenceIG != null) {
            batch.draw(competenceIG.info.image, getX(), getY(), getWidth(), getHeight());
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
            if (container.getPartie().getCompetenceActive() != null && container.getPartie().getCompetenceActive().equals(this.competenceIG)) {
                container.getPartie().setCompetenceActive(null);
            } else if (competenceIG != null) {
                if (CompetenceActive.class.isAssignableFrom(competenceIG.info.competence.getClass())) {
                    container.getPartie().setCompetenceActive(competenceIG);
                }
            }
        }
    }
}
