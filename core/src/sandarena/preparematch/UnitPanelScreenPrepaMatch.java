package sandarena.preparematch;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.Resolution;
import sandarena.donnee.Utili;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class UnitPanelScreenPrepaMatch extends Actor {
    private PanelScreenPrepaMatch container;
    private boolean actif;
    private int place;

    public UnitPanelScreenPrepaMatch(PanelScreenPrepaMatch container, boolean b, int i) {
        this.container = container;
        this.actif = b;
        this.place = i;
        if (actif) {
            this.setBounds(container.coteMoyen * (place / 2),Resolution.differenceBas+ container.coteMoyen * (place % 2),container.coteMoyen, container.coteMoyen);
        } else {
            this.setBounds(container.getWidth() - (container.coteMoyen * (1 + (place / 2))),Resolution.differenceBas+ container.coteMoyen * (place % 2),container.coteMoyen, container.coteMoyen);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }
}
