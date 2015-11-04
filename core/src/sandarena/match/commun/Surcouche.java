package sandarena.match.commun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.match.partie.ScreenPartie;
import sandarena.match.preparematch.ScreenPrepaMatch;

/**
 * Created by lucmo on 24/10/2015.
 */
public class Surcouche extends Stage {
    private  Screen container;
    private  ChangeTour changeTour;
    private  Timer timer = null;
    protected boolean visible;

    public Surcouche(Screen container, FillViewport fillViewport, Batch batch) {
        super(fillViewport,batch);
        this.container = container;
        if (container instanceof ScreenPartie) {
            this.timer = new Timer(false);
        }else if(container instanceof ScreenPrepaMatch){
            this.timer = new Timer(true);
        }
        this.changeTour = new ChangeTour(this);
        this.addActor(timer);
        this.addActor(changeTour);
    }

    public void activateChangeTour(boolean actif) {
        this.setVisible(true);
        changeTour.setVisible(actif, true);
    }


    @Override
    public void draw() {
        super.draw();
    }

    public void setVisible(boolean visible) {
        if (visible) {
            Gdx.input.setInputProcessor(this);
            this.visible = visible;
        } else {
            if (container instanceof ScreenPartie) {
                Gdx.input.setInputProcessor(((ScreenPartie) container).getPartie());
            }else if (container instanceof ScreenPrepaMatch){
                Gdx.input.setInputProcessor(((ScreenPrepaMatch)container).getPrincipal());
            }
            this.visible = visible;
            changeTour.setVisible(false);
        }
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK){
        }
        return super.keyDown(keyCode);
    }

    public Screen getContainer() {
        return container;
    }
    public Timer getTimer(){
        return timer;
    }
    public void reset(){
        timer.reset();
    }

    public boolean isVisible() {
        return visible;
    }

    public void dispose(){
        container = null;
        changeTour.dispose();
        timer.dispose();
        changeTour = null;
        timer = null;
        super.dispose();
    }

}

