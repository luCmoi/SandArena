package sandarena.selectionequipe.Surcouche.enattenteadversaire;

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
public class BouttonAnnu extends Actor {
    private final EnAttenteDadversaire container;

    public BouttonAnnu(EnAttenteDadversaire container) {
        this.container = container;
        this.addListener(new BouttonAnnuListener(this));
        this.setTouchable(Touchable.enabled);
        setBounds((container.getWidth()/4), container.getHeight()/8, (container.getWidth()/2),(container.getHeight()/6)*2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour,getX(),getY(),getWidth(), getHeight());
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        Font.font.draw(batch, "Annuler", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth()*7/2),getY()+getHeight()-getHeight()/4);
    }

    public void clique() {
        if (container.isVisible()) {
            container.getContainer().setVisible(false);
            SandArena.googleService.quitQuickGame();
        }
    }
}
