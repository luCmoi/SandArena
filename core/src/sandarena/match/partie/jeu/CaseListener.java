/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandarena.match.partie.jeu;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import sandarena.donnee.donneestatic.Resolution;

/**
 * Ecoute les interactions effectu�es avec une case
 *
 * @author Guillaume
 */
public class CaseListener extends InputListener {
    private boolean presence = true;
    private Case caseEcoute;
    private Vector2 vec;
    private boolean pression;
    private long time;

    public CaseListener(Case aThis) {
        caseEcoute = aThis;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.vec = caseEcoute.getContainer().stageToScreenCoordinates(caseEcoute.localToStageCoordinates(new Vector2(x, y)));
        if (!pression){
            pression = true;
            time = System.currentTimeMillis();
        }
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
    }

    public boolean update() {
        if (pression && time != 0 && System.currentTimeMillis() - time >= 500) {
            time = 0;
            caseEcoute.pression();
            pression = false;
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Vector2 vScreen = caseEcoute.getContainer().stageToScreenCoordinates(caseEcoute.localToStageCoordinates(new Vector2(x, y)));
        if (pression) {
            if (presence && Math.abs(vec.x - vScreen.x) < 50 * Resolution.ratioWidth && Math.abs(vec.y - vScreen.y) < 50 * Resolution.ratioHeight) {
                caseEcoute.clique();
            }
            pression = false;
        }
        presence = true;
        caseEcoute.finPression();
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        presence = false;
    }


    public void dispose(){
        caseEcoute = null;
    }

}

