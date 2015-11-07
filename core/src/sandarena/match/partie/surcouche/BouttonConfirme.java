package sandarena.match.partie.surcouche;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 13/10/2015.
 */
class BouttonConfirme extends Actor {
    private  Fin container;

    public BouttonConfirme(Fin container) {
        this.container = container;
        this.addListener(new BouttonConfirmeListener(this));
        this.setTouchable(Touchable.enabled);
        setBounds(container.getWidth()/4 , container.getHeight()/24, container.getWidth()/2,container.getHeight()/6);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        Font.font.draw(batch, "Confirmer", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 9 / 2), getY() + getHeight() - getHeight() / 4);
    }

    public void clique() {
        if (container.isVisible()) {
            container.clique();
            SandArena.googleService.quitQuickGame(false);
        }
    }

    public void dispose() {
        container =null;
    }
}
