package sandarena.preparematch.stageprincipal;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 27/07/2015.
 */
public class UnitPanelScreenPrepaMatchListener extends InputListener {
    UnitPanelScreenPrepaMatch container;

    public UnitPanelScreenPrepaMatchListener(UnitPanelScreenPrepaMatch container){
        this.container = container;
    }

    public UnitPanelScreenPrepaMatch getContainer() {
        return container;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    }
}
