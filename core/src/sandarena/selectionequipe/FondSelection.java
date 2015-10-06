package sandarena.selectionequipe;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 05/10/2015.
 */
public class FondSelection extends Actor {
    private StageSelectionEquipe container;

    public FondSelection(StageSelectionEquipe container) {
    this.container = container;
        this.setTouchable(Touchable.enabled);
        setBounds(0, 0, container.getWidth(), container.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.accessible, 0, 0, getWidth(), getHeight());
    }
}
