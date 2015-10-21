package sandarena.selectionequipe.Surcouche;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.selectionequipe.ScreenSelectionEquipe;
import sandarena.selectionequipe.Surcouche.confirmationquit.ConfirmationQuit;
import sandarena.selectionequipe.Surcouche.confirmationsuppression.ConfirmationSuppression;

/**
 * Created by lucmo on 11/10/2015.
 */
public class Surcouche extends Stage {
    private final ScreenSelectionEquipe container;
    private boolean visible;
    private ConfirmationSuppression confirme;
    private ConfirmationQuit quit;

    public Surcouche(ScreenSelectionEquipe screenSelectionEquipe, FillViewport fillViewport, Batch batch) {
        super(fillViewport, batch);
        this.container = screenSelectionEquipe;
        this.visible = false;
        this.confirme = new ConfirmationSuppression(this);
        this.quit = new ConfirmationQuit(this);
        this.addActor(quit);
        this.addActor(confirme);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK){
            container.backKeyPressed();
        }
        return super.keyDown(keyCode);
    }
    @Override
    public void draw() {
        if (visible) {
            super.draw();
        }
    }

    public void activateConfirmeSuppr(int panel) {
        this.confirme.setVisible(true, panel);
        this.setVisible(true);
    }


    public void setVisible(boolean visible) {
        if (visible) {
            Gdx.input.setInputProcessor(this);
            this.visible = visible;
        } else {
            Gdx.input.setInputProcessor(container.getStage());
            this.visible = visible;
            confirme.setVisible(false);
            quit.setVisible(false);
        }

    }

    public ScreenSelectionEquipe getContainer() {
        return container;
    }

    public boolean isVisible() {
        return visible;
    }

    public void activateConfirmeQuit() {
        this.quit.setVisible(true);
        this.setVisible(true);
    }
}
