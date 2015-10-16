package sandarena.gestionequipe.stagepersos.emplacement;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by lucmo on 23/09/2015.
 */
public class UnitPersoGestionListener extends InputListener {
    UnitPersoGestion container;
    private boolean pression;
    private long time;

    public UnitPersoGestionListener(UnitPersoGestion unit) {
        container = unit;
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
        if (pression && time != 0 && System.currentTimeMillis() - time >= 500) {
            time = 0;
            container.pression();
            pression = false;
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pression) {
            pression = false;
        }
        container.finPression();
    }

    public void dispose() {
        container = null;
    }
}

