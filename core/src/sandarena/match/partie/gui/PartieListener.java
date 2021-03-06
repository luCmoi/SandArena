package sandarena.match.partie.gui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import sandarena.match.partie.jeu.Partie;

/**
 * Listener du ScreenPartie
 *
 */
public class PartieListener extends InputListener {

    private Partie partie;
    private float anciennePositionX;
    private float anciennePositionY;
    private boolean dragged = false;

    public PartieListener(Partie container) {
        super();
        this.partie = container;
    }

    @Override
    public boolean keyDown(InputEvent event, int i) {
        switch (event.getKeyCode()) {
            case Input.Keys.LEFT:
                getPartie().getCamera().setDeplacementGauche(true);
                break;
            case Input.Keys.RIGHT:
                getPartie().getCamera().setDeplacementDroit(true);
                break;
            case Input.Keys.DOWN:
                getPartie().getCamera().setDeplacementBas(true);
                break;
            case Input.Keys.UP:
                getPartie().getCamera().setDeplacementHaut(true);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(InputEvent event, int i) {
        switch (event.getKeyCode()) {
            case Input.Keys.LEFT:
                getPartie().getCamera().setDeplacementGauche(false);
                break;
            case Input.Keys.RIGHT:
                getPartie().getCamera().setDeplacementDroit(false);
                break;
            case Input.Keys.DOWN:
                getPartie().getCamera().setDeplacementBas(false);
                break;
            case Input.Keys.UP:
                getPartie().getCamera().setDeplacementHaut(false);
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(InputEvent event, char c) {
        return false;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pointer == 0 && !dragged) {
            anciennePositionX = x;
            anciennePositionY = y;
            dragged = true;
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        anciennePositionX = -1;
        anciennePositionY = -1;
        dragged = false;
    }

    @Override
    public synchronized void touchDragged(InputEvent event, float i, float i1, int i2) {
        if (i2 == 0) {
            this.partie.getCamera().dragged(anciennePositionX, anciennePositionY, i, i1);
            //anciennePositionX = i;
            //anciennePositionY = i1;
            dragged = false;
        }
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        return false;
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        return false;
    }

    private Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public void dispose() {
        this.partie = null;
    }
}
