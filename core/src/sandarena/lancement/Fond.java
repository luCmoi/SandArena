package sandarena.lancement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 11/10/2015.
 */
public class Fond extends Actor {
    private StageLancement container;

    public Fond(StageLancement stageLancement) {
        this.container = stageLancement;
        this.setBounds(0,0,Resolution.width,Resolution.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.desertChargement, 0, 0, Resolution.width, Resolution.height);
    }

    public void dispose() {
        container = null;
    }
}
