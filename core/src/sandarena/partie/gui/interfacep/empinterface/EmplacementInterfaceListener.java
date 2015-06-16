package sandarena.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 15/06/2015.
 */
public class EmplacementInterfaceListener extends InputListener {
    private boolean pression;
    private EmplacementInterface emplacementEcoute;
    private long time;

    public EmplacementInterfaceListener(EmplacementInterface aThis) {
        emplacementEcoute = aThis;
    }


    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
            pression = false;
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pression) {
            emplacementEcoute.clique();
            pression = false;
        }
        emplacementEcoute.finPression();
    }

    public void dispose() {
        this.emplacementEcoute = null;
    }
}

