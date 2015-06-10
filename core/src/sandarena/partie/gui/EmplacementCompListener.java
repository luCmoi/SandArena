/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandarena.partie.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Ecoute les interactions effectuées avec une case
 *
 * @author Guillaume
 */
public class EmplacementCompListener extends InputListener {

    private EmplacementComp emplacementEcoute;

    public EmplacementCompListener(EmplacementComp aThis) {
        emplacementEcoute = aThis;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        emplacementEcoute.clique();
    }

}
