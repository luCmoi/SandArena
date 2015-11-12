package sandarena.match.preparematch.stageprincipal.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.interfaceutil.emplacement.Emplacement;
import sandarena.match.preparematch.stageprincipal.PanelScreenPrepaMatch;

/**
 * Created by lucmo on 23/09/2015.
 */
public class EmplacementPanelScreenPrepaMatch extends Emplacement {
    private PanelScreenPrepaMatch container;
    private boolean actif;

    public EmplacementPanelScreenPrepaMatch(PanelScreenPrepaMatch container, boolean b, int place) {
        super(container.getContainer().getContainer(), place);
        this.container = container;
        this.actif = b;
        if (actif) {
            this.setBounds((container.getWidth() / 4) * ((place % 2) * 2), Resolution.differenceBas + (container.getHeight() / 2) * (1 - (place / 2)), container.getWidth() / 2, container.getWidth() / 4);
        } else {
            this.setBounds(container.getWidth() - ((container.getWidth() / 4) * ((1 + (place % 2)) * 2)), container.getHeight() - ((container.getHeight() / 2) * (1 - (place / 2))), container.getWidth() / 2, container.getWidth() / 4);
        }
        this.linkEmplacementPerso();
        this.linkEmplacementCompetence();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (container.getContainer().getContainer().getCheck() == null) {
            super.draw(batch, parentAlpha);
        }
    }

    public PanelScreenPrepaMatch getContainer() {
        return container;
    }

    public void dispose() {
        super.dispose();
        container = null;
    }
}
