package sandarena.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Utili;
import sandarena.partie.gui.interfacep.StageInterface;

/**
 * Created by Guillaume on 16/07/2015.
 */
public class EmplacementFond extends Actor {
    public EmplacementFond(StageInterface stageInterface) {
        this.setBounds(0,0,stageInterface.getWidth(),stageInterface.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.fond,getX(),getY(), getWidth(), getHeight());
    }
}
