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
    private final Screen container;
    private  Timer timer = null;
    private boolean visible;

    public Surcouche(Screen container, FillViewport fillViewport, Batch batch) {
        super(fillViewport,batch);
        this.container = container;
        if (container instanceof ScreenPartie) {
            this.timer = new Timer(false);
        }else if(container instanceof ScreenPrepaMatch){
            this.timer = new Timer(true);
        }
        this.addActor(timer);
    }

    public void activateAbandonner() {
        this.setVisible(true);
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
}

