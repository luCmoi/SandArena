package sandarena.selectionequipe.Surcouche;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.selectionequipe.ScreenSelectionEquipe;

/**
 * Created by lucmo on 11/10/2015.
 */
public class Surcouche extends Stage {
    private final ScreenSelectionEquipe container;
    private boolean visible;

    public Surcouche(ScreenSelectionEquipe screenSelectionEquipe, FillViewport fillViewport, Batch batch) {
        super(fillViewport,batch);
        this.container =screenSelectionEquipe;
        this.visible = false;
    }

    @Override
    public void draw() {
        if (visible) {
            super.draw();
        }
    }

    public void setVisible(boolean visible){
        this.visible=visible;
    }
}
