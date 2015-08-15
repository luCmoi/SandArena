package sandarena.preparematch.barre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.donnee.Utili;

/**
 * Created by Guillaume on 14/08/2015.
 */
public class AccepteBarre extends Actor {

    private EmplacementBarre container;

    public AccepteBarre(EmplacementBarre container) {
        super();
        this.container = container;
        this.setBounds(0,0, Resolution.differenceBas,Resolution.differenceBas/4);
        this.addListener(new AccepteBarreListener(this));
        this.setTouchable(Touchable.disabled);
    }

    public void clique() {
        container.clique();
        container.fenetre();
        container.getContainer().select(container);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (this.isTouchable()) {
            batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
            batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        }
    }
}
