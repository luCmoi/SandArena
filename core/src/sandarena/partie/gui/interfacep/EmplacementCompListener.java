/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandarena.partie.gui.interfacep;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Ecoute les interactions effectuées avec une case
 *
 * @author Guillaume
 */
public class EmplacementCompListener extends InputListener {
    private boolean pression;
    private EmplacementComp emplacementEcoute;
    private long time;

    public EmplacementCompListener(EmplacementComp aThis) {
        emplacementEcoute = aThis;
    }


    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        //todo check pr infomess
        if (!pression){
            pression = true;
            time = System.currentTimeMillis();
        }
        return true;
    }

    public boolean update() {
        if (pression && time != 0 && System.currentTimeMillis() - time >= 1000) {
            time = 0;
            emplacementEcoute.pression();
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pression) {
            emplacementEcoute.clique();
            pression = false;
            emplacementEcoute.finPression();
        }
    }

}
