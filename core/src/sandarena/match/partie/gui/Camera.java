package sandarena.match.partie.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.match.partie.jeu.Partie;

/**
 * Camera du ScreenPartie qui permet de parcourir le terrain de jeu
 */
public class Camera extends OrthographicCamera {

    private boolean deplacementGauche = false;
    private boolean deplacementDroit = false;
    private boolean deplacementHaut = false;
    private boolean deplacementBas = false;
    private static int VITESSECAM = 8;
    private Partie partie;

    public Camera(Partie partie) {
        super();
        this.setToOrtho(false, Resolution.width, Resolution.height - Resolution.differenceBas);
        this.position.set(Resolution.width / 2, Resolution.height / 2, 0);
        this.partie = partie;
    }

    public void dispose() {
        this.partie = null;
    }

    public void setDeplacementGauche(boolean deplacementGauche) {
        this.deplacementGauche = deplacementGauche;
    }

    public void setDeplacementDroit(boolean deplacementDroit) {
        this.deplacementDroit = deplacementDroit;
    }


    public void setDeplacementHaut(boolean deplacementHaut) {
        this.deplacementHaut = deplacementHaut;
    }


    public void setDeplacementBas(boolean deplacementBas) {
        this.deplacementBas = deplacementBas;
    }

    /**
     * Action de la caméra si une opération de Dragged est réalisé sur le ScreenPartie
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public synchronized void dragged(float x1, float y1, float x2, float y2) {
        float depX = x1 - x2;
        float depY = y1 - y2;
        if (position.x + depX < partie.getViewport().getWorldWidth() / 2) {
            depX = (partie.getViewport().getWorldWidth() / 2) - position.x;
        } else if (position.x + depX > partie.getWidthTailleTotale() - (partie.getViewport().getWorldWidth() / 2)) {
            depX = (partie.getWidthTailleTotale() - (partie.getViewport().getWorldWidth() / 2)) - position.x;
        }
        if (position.y + depY < (partie.getViewport().getWorldHeight() / 2) - Resolution.differenceBas) {
            depY = ((partie.getViewport().getWorldHeight() / 2) - Resolution.differenceBas) - position.y;
        } else if (position.y + depY > partie.getHeightTailleTotale() - (partie.getViewport().getWorldHeight() / 2)) {
            depY = (partie.getHeightTailleTotale() - (partie.getViewport().getWorldHeight() / 2)) - position.y;
        }
        this.translate(depX, depY);
    }
}
