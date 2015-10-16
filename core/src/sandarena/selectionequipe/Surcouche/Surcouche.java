package sandarena.selectionequipe.Surcouche;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.selectionequipe.ScreenSelectionEquipe;
import sandarena.selectionequipe.Surcouche.confirmationsuppression.ConfirmationSuppression;
import sandarena.selectionequipe.Surcouche.enattenteadversaire.EnAttenteDadversaire;

/**
 * Created by lucmo on 11/10/2015.
 */
public class Surcouche extends Stage {
    private final ScreenSelectionEquipe container;
    private boolean visible;
    private ConfirmationSuppression confirme;
    private EnAttenteDadversaire attente;

    public Surcouche(ScreenSelectionEquipe screenSelectionEquipe, FillViewport fillViewport, Batch batch) {
        super(fillViewport, batch);
        this.container = screenSelectionEquipe;
        this.visible = false;
        this.confirme = new ConfirmationSuppression(this);
        this.attente = new EnAttenteDadversaire(this);
        this.addActor(confirme);
        this.addActor(attente);
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

    public void activateAttente(int panel) {
        this.attente.setVisible(true, panel);
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
            attente.setVisible(false);
        }

    }

    public ScreenSelectionEquipe getContainer() {
        return container;
    }
}
