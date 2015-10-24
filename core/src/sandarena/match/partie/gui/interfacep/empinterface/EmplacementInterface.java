package sandarena.match.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.match.partie.gui.interfacep.StageInterface;
import sandarena.infowindow.InfoWindow;


/**
 * Created by Guillaume on 10/06/2015.
 */
public abstract class EmplacementInterface extends Actor {
    protected StageInterface container;
    protected int place;
    protected boolean actif;
    protected InfoWindow info;

    public EmplacementInterface(int place, StageInterface container) {
        this.place = place;
        this.container = container;
        this.setTouchable(Touchable.enabled);
        this.container = container;
        this.addListener(new EmplacementInterfaceListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ((EmplacementInterfaceListener) getListeners().get(0)).update();
    }

    public void dispose(){
        ((EmplacementInterfaceListener) getListeners().get(0)).dispose();
        clear();
        remove();
        container = null;
        info = null;
    }

    public int getPlace() {
        return place;
    }

    public abstract void  clique();

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }


    public abstract void pression();
}
