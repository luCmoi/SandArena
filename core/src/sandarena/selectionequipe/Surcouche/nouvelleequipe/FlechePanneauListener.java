package sandarena.selectionequipe.Surcouche.nouvelleequipe;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 25/07/2015.
 */
class FlechePanneauListener extends InputListener {
    private FlechePanneau fleche;

    public FlechePanneauListener(FlechePanneau flecheListe) {
        this.fleche = flecheListe;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        fleche.clique();
    }

    public void dispose() {
        fleche = null;
    }
}

