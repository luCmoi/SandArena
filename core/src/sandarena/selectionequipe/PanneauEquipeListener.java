package sandarena.selectionequipe;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by lucmo on 05/10/2015.
 */
public class PanneauEquipeListener extends InputListener {
    private PanneauEquipe container;

    public PanneauEquipeListener(PanneauEquipe container) {
        this.container = container;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        container.clique();
        super.touchUp(event, x, y, pointer, button);
    }
}
