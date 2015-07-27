package sandarena.preparematch.barre;

import com.badlogic.gdx.math.Vector2;
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
        presence = true;
        debutDragged = 0;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        presence = true;
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
            } else {
                Vector2 v = container.localToStageCoordinates(new Vector2(x,y));
                if (!container.getContainer().getPrincipal().relacheUnit(v.x, v.y, container)) {
                    if (System.currentTimeMillis() - debutDragged <= 100) {
                        container.clique();
                    }else{
                        container.dragged();
                    }
                } else {
                    System.out.println("RelacheTermine");
                    container.getContainer().diminueWidthTailleTotale(container.getPlace());
                    container.dispose();
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
            if (System.currentTimeMillis() - debutDragged > 100) {
                presence = false;
                container.dragged();
            }
        }
    }

    public void dispose() {
        container = null;
    }
}
