package sandarena.interfaceutil.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by lucmo on 12/11/2015.
 */
public class Surcouche extends Stage {
    protected boolean visible;
    protected ScreenSurcouche container;

    public Surcouche(ScreenSurcouche container, Viewport fillViewport, Batch batch) {
        super(fillViewport, batch);
        this.visible = false;
        this.container = container;
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if (visible) {
            Gdx.input.setInputProcessor(this);
            this.visible = visible;
        } else {
            container.finInputSurcouche();
            this.visible = visible;
        }
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK){
            container.backKeyPressed();
        }
        return super.keyDown(keyCode);
    }

    public ScreenSurcouche getContainer(){
        return container;
    }
}
