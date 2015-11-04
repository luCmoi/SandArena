package sandarena.selectionequipe.Surcouche.nouvelleequipe;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by lucmo on 04/11/2015.
 */
public class BoutonAccepteListener extends InputListener {
    private BoutonAccepte boutonAccepte;

    public BoutonAccepteListener(BoutonAccepte boutonAccepte) {
        this.boutonAccepte = boutonAccepte;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        boutonAccepte.clique();
    }

    public void dispose() {
        boutonAccepte = null;
    }
}

