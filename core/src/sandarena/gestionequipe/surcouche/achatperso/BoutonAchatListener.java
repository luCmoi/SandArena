package sandarena.gestionequipe.surcouche.achatperso;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by lucmo on 20/10/2015.
 */
public class BoutonAchatListener extends InputListener {
    private BoutonAchat container;

    public BoutonAchatListener(BoutonAchat container) {
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