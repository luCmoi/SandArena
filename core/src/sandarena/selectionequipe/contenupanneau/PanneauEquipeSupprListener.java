package sandarena.selectionequipe.contenupanneau;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by lucmo on 09/10/2015.
 */
public class PanneauEquipeSupprListener extends InputListener {
    private PanneauEquipeSuppr container;

    public PanneauEquipeSupprListener(PanneauEquipeSuppr container) {
        this.container = container;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        container.clique();
    }

    public void dispose() {
        this.container = null;
    }
}