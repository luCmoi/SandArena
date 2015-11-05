package sandarena.match.partie.surcouche;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;
import sandarena.match.partie.ScreenPartie;

/**
 * Created by lucmo on 02/11/2015.
 */
public class Victoire extends Group {
    private  SurcouchePartie container;
    private BouttonConfirme bouton;

    public Victoire(SurcouchePartie surcouche) {
        super();
        container = surcouche;
        setBounds(Resolution.width / 8, Resolution.height / 8, Resolution.width / 8 * 6, (Resolution.height / 8) * 5);
        bouton = new BouttonConfirme(this);
        this.addActor(bouton);
        setVisible(false);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            Son.victoire.play();
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
            Font.font.draw(batch, "Victoire !", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 8 / 2), getY() + getHeight() - getHeight() / 8);
            int nb = ((ScreenPartie) container.getContainer()).getPartie().getJoueurActif().getPersonnages().size();
            for (int i = 0; i < nb ; i++) {
                batch.draw(((ScreenPartie) container.getContainer()).getPartie().getJoueurActif().getPersonnages().get(i).getDonnee().commun.image,
                        getX() + (getHeight() / 3 * i)+ (((getWidth()-getHeight()/3* nb) / (nb+1)) * (i+1)), getY() + getHeight() / 3, getHeight() / 3, getHeight() / 3);
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
    }
}
