package sandarena.partie.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.compcase.CompetenceIG;

/**
 * Created by Guillaume on 08/06/2015.
 */
public class EmplacementComp extends Actor {
    private StageInterface container;
    private CompetenceIG competenceIG;
    private int place;

    public EmplacementComp(int place, StageInterface container) {
        this.place = place;
        this.container = container;
        this.setTouchable(Touchable.enabled);
        this.setBounds(0 + (Resolution.widthCase * getPlace()), 0, Resolution.widthCase, Resolution.heightCase);
        this.container = container;
        this.addListener(new EmplacementCompListener(this));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (competenceIG != null) {
            batch.draw(competenceIG.info.image, 0 + (Resolution.widthCase * getPlace()), 0, Resolution.widthCase, Resolution.heightCase);
        } else {
        }
    }

    public void dispose() {
        setCompetenceIG(null);
    }

    public int getPlace() {
        return place;
    }

    public void setCompetenceIG(CompetenceIG competenceIG) {
        this.competenceIG = competenceIG;
    }

    public void clique() {
        if (container.getPartie().getCompetenceActive() != null && container.getPartie().getCompetenceActive().equals(this.competenceIG)) {
            container.getPartie().setCompetenceActive(null);
        } else if (competenceIG != null) {
            if (CompetenceActive.class.isAssignableFrom(competenceIG.info.competence.getClass())) {
                container.getPartie().setCompetenceActive(competenceIG);
            }
        }
    }
}
