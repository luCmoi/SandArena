package sandarena.partie.gui.interfacep;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;


/**
 * Created by Guillaume on 10/06/2015.
 */
public class EmplacementInterface extends Actor {
    protected StageInterface container;
    protected int place;

    public EmplacementInterface(int place, StageInterface container) {
        this.place = place;
        this.container = container;
        this.setTouchable(Touchable.enabled);
        this.container = container;
    }


    public int getPlace() {
        return place;
    }
}
