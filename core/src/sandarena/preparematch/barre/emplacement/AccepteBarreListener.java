package sandarena.preparematch.barre.emplacement;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 14/08/2015.
 */
public class AccepteBarreListener extends InputListener {

    private AccepteBarre container;
    private boolean presse;

    public AccepteBarreListener(AccepteBarre container) {
        this.container = container;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        super.touchDown(event, x, y, pointer, button);
        presse = true;
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        if (presse) {
            container.clique();
        }

    }
}
