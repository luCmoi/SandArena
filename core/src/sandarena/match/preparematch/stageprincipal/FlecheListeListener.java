package sandarena.match.preparematch.stageprincipal;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Guillaume on 25/07/2015.
 */
class FlecheListeListener extends InputListener {
    private FlecheListe flecheListe;

    public FlecheListeListener(FlecheListe flecheListe) {
        this.flecheListe = flecheListe;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (this.flecheListe.isGauche()){
            this.flecheListe.getContainer().getBarre().gauche();
        }else{
            this.flecheListe.getContainer().getBarre().droite();
        }
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        this.flecheListe.getContainer().getBarre().stop();
        super.touchUp(event, x, y, pointer, button);
    }
}
