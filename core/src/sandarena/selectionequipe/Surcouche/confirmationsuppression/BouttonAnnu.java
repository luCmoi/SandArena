package sandarena.selectionequipe.Surcouche.confirmationsuppression;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 13/10/2015.
 */
class BouttonAnnu extends Actor {
    private final ConfirmationSuppression container;

    public BouttonAnnu(ConfirmationSuppression confirmationSuppression) {
        this.container = confirmationSuppression;
        this.addListener(new BouttonAnnuListener(this));
        this.setTouchable(Touchable.enabled);
        setBounds((container.getWidth()/15)*8, container.getHeight()/6, (container.getWidth()/15)*6,(container.getHeight()/6)*2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour,getX(),getY(),getWidth(), getHeight());
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        Font.font.draw(batch, "Annuler", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth()*7/2),getY()+getHeight()-getHeight()/4);
    }

    public void clique() {
        if (container.isVisible()) {
            Son.MenuSelect.play();
            container.getContainer().setVisible(false);
        }
    }
}
