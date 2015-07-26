package sandarena.preparematch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 26/07/2015.
 */
public class UnitBarreListener extends InputListener {
    UnitBarre container;
    boolean presence;
    private long debutDragged;
    private boolean departDragged;


    public UnitBarreListener(UnitBarre container) {
        this.container = container;
        departDragged = false;
        presence = false;
        debutDragged = 0;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pointer == 0) {

            departDragged = false;
            if (!container.isDragged()) {
                if (presence) {
                    container.clique();
                }
                presence = true;
            } else {
                container.dragged();
                if (System.currentTimeMillis() - debutDragged <= 1) {
                    container.clique();
                }
            }
        }
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        presence = false;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if (pointer == 0) {
            if (!departDragged) {
                debutDragged = System.currentTimeMillis();
                departDragged = true;
            }
            if (System.currentTimeMillis() - debutDragged > 1) {
                departDragged = false;
                container.dragged();
            }
        }
    }
}
