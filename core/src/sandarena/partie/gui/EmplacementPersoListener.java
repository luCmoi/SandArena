package sandarena.partie.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 10/06/2015.
 */
public class EmplacementPersoListener extends InputListener {
    private boolean pression;
    private EmplacementPerso emplacementEcoute;

    public EmplacementPersoListener(EmplacementPerso aThis) {
        emplacementEcoute = aThis;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        emplacementEcoute.pression();
        pression = true;
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pression) {
            pression = false;
            emplacementEcoute.finPression();
        }
    }

}
