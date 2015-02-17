package sandarena.gui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import sandarena.partie.Partie;

/**
 * Listener du ScreenPartie
 * @author Guillaume
 */
public class ScreenPartieListener extends InputListener {

    private Partie partie;
    private float anciennePositionX;
    private float anciennePositionY;

    public ScreenPartieListener(Partie container) {
        super();
        this.partie = container;
    }

    @Override
    public boolean keyDown(InputEvent event, int i) {
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
    public boolean keyUp(InputEvent event, int i) {
        switch (i) {
            case Input.Keys.LEFT:
                getPartie().getCamera().setDeplacementGauche(false);
                //mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
            case Input.Keys.RIGHT:
                getPartie().getCamera().setDeplacementDroit(false);
                //mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
            case Input.Keys.DOWN:
                getPartie().getCamera().setDeplacementBas(false);
                //mouseMoved(Gdx.input.getX(), Gdx.input.getY());
                break;
            case Input.Keys.UP:
                getPartie().getCamera().setDeplacementHaut(false);
                //mouseMoved(Gdx.input.getX(), Gdx.input.getY());
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
        anciennePositionX=x;
        anciennePositionY=y;
        
        return false;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    }

    @Override
    public void touchDragged(InputEvent event, float i, float i1, int i2) {
        this.partie.getCamera().dragged(anciennePositionX,anciennePositionY,i, i1);
        anciennePositionX=i;
        anciennePositionY=i1;
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        return false;
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        return false;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    
    public void dispose(){
        this.partie = null;
    }
}
