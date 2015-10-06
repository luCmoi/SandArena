package sandarena.preparematch.stageprincipal.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Personnage;
import sandarena.preparematch.stageprincipal.PanelScreenPrepaMatch;

/**
 * Created by lucmo on 23/09/2015.
 */
public class EmplacementPanelScreenPrepaMatch extends Group{
    private PanelScreenPrepaMatch container;
    private boolean actif;
    private byte place;
    UnitPanelScreenPrepaMatch unit;
    CompPanelScreenPrepaMatch[] comp = new CompPanelScreenPrepaMatch[4];

    public EmplacementPanelScreenPrepaMatch(PanelScreenPrepaMatch container, boolean b, int place) {
        this.container = container;
        this.actif = b;
        this.place = (byte)place;
        if (actif) {
            this.setBounds((container.getWidth()/4)* ((place % 2)*2), Resolution.differenceBas + (container.getHeight()/2) * (1 - (place / 2)),container.getWidth()/2,container.getWidth()/4);
        } else {
            this.setBounds(container.getWidth() - ((container.getWidth()/4)* ((1+(place % 2))*2)), container.getHeight() - ((container.getHeight()/2) * (1 - (place / 2))) ,container.getWidth()/2,container.getWidth()/4);
        }
        this.unit = new UnitPanelScreenPrepaMatch(this);
        this.addActor(unit);
        for (int i = 0; i < 4; i++) {
            comp[i] = new CompPanelScreenPrepaMatch(this, i);
            this.addActor(comp[i]);
        }
    }

    public Personnage getPerso() {
        return unit.getPerso();
    }

    public void setPerso(Personnage perso) {
        unit.setPerso(perso);
        for (int i = 0; i < 4; i++) {
            comp[i].setComp(perso.getCompetences()[i]);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public PanelScreenPrepaMatch getContainer() {
        return container;
    }
}