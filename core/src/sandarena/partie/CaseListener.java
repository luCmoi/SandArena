/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandarena.partie;

import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 *
 * @author Guillaume
 */
class CaseListener extends InputListener {

    private Case caseEcoute;

    public CaseListener(Case aThis) {
        caseEcoute = aThis;
    }

}
