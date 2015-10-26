package sandarena.match.preparematch.barre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Utili;

/**
 * Created by Guillaume on 16/07/2015.
 */
class Fond extends Actor {
    private final StageBarre container;

    public Fond(StageBarre stageBarre) {
        this.container = stageBarre;
        this.setBounds(0,0,stageBarre.getWidth(),stageBarre.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.fond,((CameraBarre)container.getCamera()).getX(),getY(), getWidth(), getHeight());
    }
}