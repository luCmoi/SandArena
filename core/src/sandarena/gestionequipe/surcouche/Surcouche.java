package sandarena.gestionequipe.surcouche;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.gestionequipe.ScreenGestionEquipe;
import sandarena.gestionequipe.surcouche.achatperso.AchatPerso;
import sandarena.gestionequipe.surcouche.attenteadversaire.EnAttenteDadversaire;


/**
 * Created by lucmo on 16/10/2015.
 */
public class Surcouche extends Stage {
    private final ScreenGestionEquipe container;
    private final AchatPerso achat;
    private boolean visible;
    private EnAttenteDadversaire attente;

    public Surcouche(ScreenGestionEquipe container, FillViewport fillViewport, Batch batch) {
        super(fillViewport,batch);
        this.container = container;
        this.attente = new EnAttenteDadversaire(this);
        this.achat = new AchatPerso(this);
        this.addActor(achat);
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
            achat.setVisible(false);
        }

    }

    public void activateAchatPerso(byte place) {
        this.achat.setVisible(place);
        this.setVisible(true);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK){
            container.backKeyPressed();
        }
        return super.keyDown(keyCode);
    }

    public ScreenGestionEquipe getContainer() {
        return container;
    }

    public boolean isVisible() {
        return visible;
    }
}