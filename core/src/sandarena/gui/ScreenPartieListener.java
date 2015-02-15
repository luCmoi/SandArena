package sandarena.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Listener du ScreenPartie
 * @author Guillaume
 */
public class ScreenPartieListener implements InputProcessor {

    private ScreenPartie partie;
    private int anciennePositionX;
    private int anciennePositionY;

    public ScreenPartieListener(ScreenPartie container) {
        super();
        this.partie = container;
    }

    @Override
    public boolean keyDown(int i) {
        switch (i) {
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
    public boolean keyUp(int i) {
        switch (i) {
            case Input.Keys.LEFT:
                getPartie().getCamera().setDeplacementGauche(false);
                mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
            case Input.Keys.RIGHT:
                getPartie().getCamera().setDeplacementDroit(false);
                mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
            case Input.Keys.DOWN:
                getPartie().getCamera().setDeplacementBas(false);
                mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
            case Input.Keys.UP:
                getPartie().getCamera().setDeplacementHaut(false);
                mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        anciennePositionX=x;
        anciennePositionY=y;
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        this.partie.getCamera().dragged(anciennePositionX,anciennePositionY,i, i1);
        anciennePositionX=i;
        anciennePositionY=i1;
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    public ScreenPartie getPartie() {
        return partie;
    }

    public void setPartie(ScreenPartie partie) {
        this.partie = partie;
    }
    
    public void dispose(){
        this.partie = null;
    }
}
