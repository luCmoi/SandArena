package sandarena.gestionequipe.barre;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by lucmo on 16/10/2015.
 */
public class QuickMatchButonListener extends InputListener {
    private QuickMatchButon container;

    public QuickMatchButonListener(QuickMatchButon quickMatchButon) {
        container = quickMatchButon;
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