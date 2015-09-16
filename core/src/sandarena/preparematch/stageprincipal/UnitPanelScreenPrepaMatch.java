package sandarena.preparematch.stageprincipal;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.joueur.Personnage;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class UnitPanelScreenPrepaMatch extends Actor {
    private PanelScreenPrepaMatch container;
    private boolean actif;
    private byte place;
    private Personnage perso;

    public UnitPanelScreenPrepaMatch(PanelScreenPrepaMatch container, boolean b, int i) {
        this.container = container;
        this.actif = b;
        this.place = (byte)i;
        if (actif) {
            this.setBounds(container.coteMoyen * (place % 2), Resolution.differenceBas + container.coteMoyen * (1 - (place / 2)), container.coteMoyen, container.coteMoyen);
        } else {
            this.setBounds(container.getWidth() - (container.coteMoyen * (2 - place % 2)), Resolution.differenceBas + container.coteMoyen * (1 - (place / 2)), container.coteMoyen, container.coteMoyen);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (perso != null) {
            batch.draw(perso.commun.image, getX(), getY(), getWidth(), getHeight());
        }
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public Personnage getPerso() {
        return perso;
    }

    public PanelScreenPrepaMatch getContainer() {
        return container;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }
}
