/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandarena.partie;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Ecoute les interactions effectuées avec une case
 *
 * @author Guillaume
 */
public class CaseListener extends InputListener {
    private boolean presence;
    private Case caseEcoute;

    public CaseListener(Case aThis) {
        caseEcoute = aThis;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        presence = true;
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
        presence = false;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (presence) {
            caseEcoute.clique();
        }
    }

    public void dispose(){
        caseEcoute = null;
    }

}
