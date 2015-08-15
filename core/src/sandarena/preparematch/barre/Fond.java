package sandarena.preparematch.barre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.Utili;
import sandarena.partie.gui.interfacep.StageInterface;

/**
 * Created by Guillaume on 16/07/2015.
 */
public class Fond extends Actor {
    public Fond(StageBarre stageBarre) {
        this.setBounds(0,0,stageBarre.getWidth(),stageBarre.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.fond,getX(),getY(), getWidth(), getHeight());
    }
}
