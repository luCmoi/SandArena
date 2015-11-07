package sandarena.match.partie.surcouche;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;
import sandarena.match.partie.ScreenPartie;
import sandarena.match.partie.surcouche.emplacementfinpartie.EmplacementFinPartie;

/**
 * Created by lucmo on 02/11/2015.
 */
public class Fin extends Group {
    private SurcouchePartie container;
    private BouttonConfirme bouton;
    private boolean victoire;
    private EmplacementFinPartie[] emp = new EmplacementFinPartie[4];

    public Fin(SurcouchePartie surcouche, boolean victoire) {
        super();
        this.victoire = victoire;
        container = surcouche;
        setBounds(Resolution.width / 8, Resolution.height / 32, Resolution.width / 8 * 6, (Resolution.height / 8) * 6);
        bouton = new BouttonConfirme(this);
        this.addActor(bouton);
        setVisible(false);
        for (int i = 0; i < 4; i++) {
            emp[i] = new EmplacementFinPartie(this,i,((ScreenPartie) container.getContainer()).getPartie().getJoueurActif().getPersonnages().get(i));
            this.addActor(emp[i]);
        }
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible && victoire) {
            Son.victoire.play();
        } else if (visible && !victoire) {
            Son.defaite.play();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            batch.draw(Utili.passive, 0, 0, Resolution.width, Resolution.height);
            batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
            batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
            Font.font.setColor(Color.BLACK);
            Font.font.setScale(Resolution.ratioWidth * 5, Resolution.ratioHeight * 5);
            if (victoire) {
                Font.font.draw(batch, "Victoire !", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 8 / 2), getY() + getHeight() - getHeight() / 16);
            } else {
                Font.font.draw(batch, "Defaite !", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 7 / 2), getY() + getHeight() - getHeight() / 16);
            }
            super.draw(batch, parentAlpha);
        }
    }

    public SurcouchePartie getContainer() {
        return container;
    }

    public void clique() {
        ((ScreenPartie) container.getContainer()).getContainer().lanceGestionEquipe(((ScreenPartie) container.getContainer()).getJoueurActif());
        container.getContainer().dispose();
    }

    public void dispose() {
        container = null;
        bouton.dispose();
        bouton = null;
        for (int i = 0; i < 4; i++) {
            emp[i].dispose();
        }
        emp =null;
        clear();
        remove();
    }
}
