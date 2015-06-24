/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandarena.partie;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import sandarena.Resolution;

/**
 * Ecoute les interactions effectuées avec une case
 *
 * @author Guillaume
 */
public class CaseListener extends InputListener {
    private boolean presence = true;
    private Case caseEcoute;
    Vector2 vec;

    public CaseListener(Case aThis) {
        caseEcoute = aThis;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.vec = caseEcoute.getContainer().stageToScreenCoordinates(caseEcoute.localToStageCoordinates(new Vector2(x, y)));
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Vector2 vScreen = caseEcoute.getContainer().stageToScreenCoordinates(caseEcoute.localToStageCoordinates(new Vector2(x, y)));
        if (presence && Math.abs(vec.x - vScreen.x)< 50 * Resolution.ratioWidth && Math.abs(vec.y - vScreen.y)< 50 * Resolution.ratioHeight){
            caseEcoute.clique();
        }
        presence = true;
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
