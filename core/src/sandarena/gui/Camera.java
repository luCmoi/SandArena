package sandarena.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import sandarena.Resolution;

/**
 * Camera du ScreenPartie qui permet de parcourir le terrain de jeu
 * @author Guillaume
 */
public class Camera extends OrthographicCamera {

    private boolean deplacementGauche = false;
    private boolean deplacementDroit = false;
    private boolean deplacementHaut = false;
    private boolean deplacementBas = false;
    private ScreenPartie partie;

    public Camera(ScreenPartie partie) {
        super();
        this.setToOrtho(false, Resolution.width, Resolution.height - partie.getDifferenceBas());
        this.position.set(Resolution.width / 2, Resolution.height / 2, 0);
        this.partie = partie;
    }

    /**
     * Update de la position de la caméra
     */
    public void updateExt() {
        if (deplacementGauche) {
            if (position.x > Resolution.width / 2) {
                translate(-(int) (8 * Resolution.ratioWidth), 0, 0);
                if (position.x < Resolution.width / 2) {
                    position.x = Resolution.width / 2;
                }
            }
        }
        if (deplacementDroit) {
            if (position.x < partie.getWidthTailleTotale() - Resolution.width / 2) {
                translate((int) (8 * Resolution.ratioWidth), 0, 0);
                if (position.x > partie.getWidthTailleTotale() - Resolution.width / 2) {
                    position.x = partie.getWidthTailleTotale() - Resolution.width / 2;
                }
            }
        }
        if (deplacementBas) {
            if (position.y > Resolution.height / 2 - partie.getDifferenceBas()) {
                translate(0, -(int) (8 * Resolution.ratioHeight), 0);
                if (position.y < Resolution.height / 2 - partie.getDifferenceBas()) {
                    position.y = Resolution.height / 2 - partie.getDifferenceBas();
                }
            }
        }
        if (deplacementHaut) {
            if (position.y < partie.getHeightTailleTotale() - Resolution.height / 2) {
                translate(0, (int) (8 * Resolution.ratioHeight), 0);
                if (position.y > partie.getHeightTailleTotale() - Resolution.height / 2) {
                    position.y = partie.getHeightTailleTotale() - Resolution.height / 2;
                }
            }
        }
    }

    public void dispose() {
        this.partie = null;
    }

    public boolean isDeplacementGauche() {
        return deplacementGauche;
    }

    public void setDeplacementGauche(boolean deplacementGauche) {
        this.deplacementGauche = deplacementGauche;
    }

    public boolean isDeplacementDroit() {
        return deplacementDroit;
    }

    public void setDeplacementDroit(boolean deplacementDroit) {
        this.deplacementDroit = deplacementDroit;
    }

    public boolean isDeplacementHaut() {
        return deplacementHaut;
    }

    public void setDeplacementHaut(boolean deplacementHaut) {
        this.deplacementHaut = deplacementHaut;
    }

    public boolean isDeplacementBas() {
        return deplacementBas;
    }

    public void setDeplacementBas(boolean deplacementBas) {
        this.deplacementBas = deplacementBas;
    }

    /**
     * Action de la caméra si une opération de Dragged est réalisé sur le ScreenPartie
     * @param x1
     * @param y1
     * @param x2
     * @param y2 
     */
    public void dragged(int x1, int y1, int x2, int y2) {
        this.translate(x1 - x2, -(y1 - y2));
        if (position.x < Resolution.width / 2) {
            position.x = Resolution.width / 2;
        }
        if (position.x > partie.getWidthTailleTotale() - Resolution.width / 2) {
            position.x = partie.getWidthTailleTotale() - Resolution.width / 2;
        }
        if (position.y < Resolution.height / 2 - partie.getDifferenceBas()) {
            position.y = Resolution.height / 2 - partie.getDifferenceBas();
        }
        if (position.y > partie.getHeightTailleTotale() - Resolution.height / 2) {
            position.y = partie.getHeightTailleTotale() - Resolution.height / 2;
        }
    }
}
