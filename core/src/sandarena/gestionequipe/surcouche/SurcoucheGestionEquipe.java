package sandarena.gestionequipe.surcouche;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.gestionequipe.ScreenGestionEquipe;
import sandarena.gestionequipe.surcouche.achatperso.AchatPerso;
import sandarena.gestionequipe.surcouche.attenteadversaire.EnAttenteDadversaire;
import sandarena.interfaceutil.stage.Surcouche;


/**
 * Created by lucmo on 16/10/2015.
 */
public class SurcoucheGestionEquipe extends Surcouche {
    private final AchatPerso achat;
    private EnAttenteDadversaire attente;

    public SurcoucheGestionEquipe(ScreenGestionEquipe container, FillViewport fillViewport, Batch batch) {
        super(container, fillViewport,batch);
        this.attente = new EnAttenteDadversaire(this);
        this.achat = new AchatPerso(this);
        this.addActor(achat);
        this.addActor(attente);
    }

    public void activateAttente() {
        this.attente.setVisible(true);
        this.setVisible(true);
    }


    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            attente.setVisible(false);
            achat.setVisible(false);
        }
    }

    public void activateAchatPerso(byte place) {
        this.achat.setVisible(place);
        this.setVisible(true);
    }

    public ScreenGestionEquipe getContainer() {
        return (ScreenGestionEquipe)container;
    }
}
