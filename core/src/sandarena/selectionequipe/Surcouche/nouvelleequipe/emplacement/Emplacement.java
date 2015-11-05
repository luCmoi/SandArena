package sandarena.selectionequipe.Surcouche.nouvelleequipe.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Utili;
import sandarena.joueur.Personnage;
import sandarena.selectionequipe.Surcouche.nouvelleequipe.PanneauEquipe;

/**
 * Created by lucmo on 04/11/2015.
 */
public class Emplacement extends Group {
    private PanneauEquipe container;
    private byte place;
    private EmplacementPerso unit;
    private EmplacementComp[] comp = new EmplacementComp[4];

    public Emplacement(PanneauEquipe container, int place, Personnage personnage) {
        this.container = container;
        this.place = (byte) place;
        this.setBounds((container.getWidth() / 4) * (place % 3)+container.getWidth() / 8, container.getHeight() - (container.getWidth() / 8) * (2 + place / 3), container.getWidth() / 4, container.getWidth() / 8);
        this.unit = new EmplacementPerso(this, personnage);
        this.addActor(unit);
        for (int i = 0; i < 4; i++) {
            comp[i] = new EmplacementComp(this, i, personnage.getCompetences()[i]);
            this.addActor(comp[i]);
        }
    }

    public Personnage getPerso() {
        return unit.getPerso();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
    }

    public PanneauEquipe getContainer() {
        return container;
    }

    public byte getPlace() {
        return place;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        unit.setVisible(visible);
        for (EmplacementComp cmp : comp) {
            cmp.setVisible(visible);
        }
        if (visible) {
            unit.setTouchable(Touchable.enabled);
            for (EmplacementComp cmp : comp) {
                cmp.setTouchable(Touchable.enabled);
            }
        } else {
            unit.setTouchable(Touchable.disabled);
            for (EmplacementComp cmp : comp) {
                cmp.setTouchable(Touchable.disabled);
            }
        }
    }

    public void dispose() {

    }
}
