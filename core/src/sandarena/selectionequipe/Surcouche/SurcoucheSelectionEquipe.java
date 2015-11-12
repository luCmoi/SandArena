package sandarena.selectionequipe.Surcouche;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.selectionequipe.ScreenSelectionEquipe;
import sandarena.selectionequipe.Surcouche.confirmationquit.ConfirmationQuit;
import sandarena.selectionequipe.Surcouche.confirmationsuppression.ConfirmationSuppression;
import sandarena.selectionequipe.Surcouche.nouvelleequipe.PanneauEquipe;

/**
 * Created by lucmo on 11/10/2015.
 */
public class SurcoucheSelectionEquipe extends sandarena.interfaceutil.stage.Surcouche {
    private boolean visible;
    private ConfirmationSuppression confirme;
    private ConfirmationQuit quit;
    private PanneauEquipe nouvelleEquipe;

    public SurcoucheSelectionEquipe(ScreenSelectionEquipe screenSelectionEquipe, FillViewport fillViewport, Batch batch) {
        super(screenSelectionEquipe, fillViewport, batch);
        this.confirme = new ConfirmationSuppression(this);
        this.quit = new ConfirmationQuit(this);
        this.addActor(quit);
        this.addActor(confirme);
    }

    public void activateConfirmeSuppr(int panel) {
        this.confirme.setVisible(true, panel);
        this.setVisible(true);
    }

    public void activateNouvelleEquipe(int panel) {
        this.nouvelleEquipe = new PanneauEquipe(this, panel);
        this.addActor(nouvelleEquipe);
        this.setVisible(true);
    }


    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            confirme.setVisible(false);
            quit.setVisible(false);
            if (nouvelleEquipe != null) {
                nouvelleEquipe.dispose();
                nouvelleEquipe = null;
            }
        }
    }

    public ScreenSelectionEquipe getContainer() {
        return (ScreenSelectionEquipe)container;
    }

    public void activateConfirmeQuit() {
        this.quit.setVisible(true);
        this.setVisible(true);
    }
}
