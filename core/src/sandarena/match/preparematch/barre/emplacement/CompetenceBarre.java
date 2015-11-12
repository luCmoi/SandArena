package sandarena.match.preparematch.barre.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.competence.BanqueCompetence;
import sandarena.interfaceutil.emplacement.EmplacementCompetence;

/**
 * Created by Guillaume on 13/08/2015.
 */
class CompetenceBarre extends EmplacementCompetence {

    public CompetenceBarre(EmplacementBarre container, int place, BanqueCompetence.EntreeCompetence comp) {
        super(container, place);
        this.comp = comp;
        this.addListenerBasique();
        this.setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.isTouchable()) {
            super.draw(batch, parentAlpha);
        }
    }

    public void pression() {
        super.pression();
        if (comp != null) {
            ((EmplacementBarre)container).fenetre();
        }
    }
}
