package sandarena.gestionequipe.surcouche;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.gestionequipe.ScreenGestionEquipe;
import sandarena.gestionequipe.surcouche.attenteadversaire.EnAttenteDadversaire;


/**
 * Created by lucmo on 16/10/2015.
 */
public class Surcouche extends Stage {
    private final ScreenGestionEquipe container;
    private boolean visible;
    private EnAttenteDadversaire attente;

    public Surcouche(ScreenGestionEquipe container, FillViewport fillViewport, Batch batch) {
        super(fillViewport,batch);
        this.container = container;
        this.attente = new EnAttenteDadversaire(this);
        this.addActor(attente);
    }

    public void activateAttente() {
        this.attente.setVisible(true);
        this.setVisible(true);
    }


    @Override
    public void draw() {
        super.draw();
    }

    public void setVisible(boolean visible) {
        if (visible) {
            Gdx.input.setInputProcessor(this);
            this.visible = visible;
        } else {
            Gdx.input.setInputProcessor(container.getPersos());
            this.visible = visible;
            attente.setVisible(false);
        }

    }
}
