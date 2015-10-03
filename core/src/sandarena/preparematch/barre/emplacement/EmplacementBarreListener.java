package sandarena.preparematch.barre.emplacement;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 26/07/2015.
 */
public class EmplacementBarreListener extends InputListener {
    EmplacementBarre container;
    private boolean fenetre = false;


    public EmplacementBarreListener(EmplacementBarre container) {
        this.container = container;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        super.touchDown(event, x, y, pointer, button);
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event,x,y,pointer,button);
        if (!fenetre) {
            container.clique();
        } else {
            fenetre = false;
        }
    }

    public void dispose() {
        container = null;
    }

    public void fenetre() {
        this.fenetre = true;
    }
}
