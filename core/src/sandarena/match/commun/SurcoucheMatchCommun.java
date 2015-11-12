package sandarena.match.commun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.interfaceutil.stage.ScreenSurcouche;
import sandarena.match.partie.ScreenPartie;
import sandarena.match.preparematch.ScreenPrepaMatch;

/**
 * Created by lucmo on 24/10/2015.
 */
public class SurcoucheMatchCommun extends sandarena.interfaceutil.stage.Surcouche {
    private  ChangeTour changeTour;
    private  Timer timer = null;

    public SurcoucheMatchCommun(ScreenSurcouche container, FillViewport fillViewport, Batch batch) {
        super(container,fillViewport,batch);
        if (container instanceof ScreenPartie) {
            this.timer = new Timer(false);
        }else if(container instanceof ScreenPrepaMatch){
            this.timer = new Timer(true);
        }
        this.changeTour = new ChangeTour(this);
        this.addActor(timer);
        this.addActor(changeTour);
    }

    public void activateChangeTour(boolean actif) {
        this.setVisible(true);
        changeTour.setVisible(actif, true);
    }


    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            if (container instanceof ScreenPartie) {
                Gdx.input.setInputProcessor(((ScreenPartie) container).getPartie());
            }else if (container instanceof ScreenPrepaMatch){
                Gdx.input.setInputProcessor(((ScreenPrepaMatch)container).getPrincipal());
            }
            this.visible = visible;
            changeTour.setVisible(false);
        }
    }

    public Timer getTimer(){
        return timer;
    }
    public void reset(){
        timer.reset();
    }

    public void dispose(){
        container = null;
        changeTour.dispose();
        timer.dispose();
        changeTour = null;
        timer = null;
        super.dispose();
    }

}

