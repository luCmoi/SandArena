package sandarena.gestionequipe.surcouche.achatperso;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 20/10/2015.
 */
public class BoutonAchat extends Actor {
    private final AchatPerso container;
    private final int place;

    public BoutonAchat(AchatPerso achatPerso, int i) {
        container = achatPerso;
        place = i;
        this.addListener(new BoutonAchatListener(this));
        this.setTouchable(Touchable.enabled);
        setBounds((container.getWidth() / 8), container.getHeight() / 5 * place + container.getHeight() /16*(place+1), container.getWidth() - container.getWidth() / 4, container.getHeight() / 5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        if (place == 2) {
            Font.font.draw(batch, "Achat Aléatoire", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 15 / 2), getY() + getHeight() - getHeight() / 8);
            Font.font.draw(batch, "1000", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 4 / 2), getY() + getHeight() - getHeight() / 8 - Font.font.getLineHeight());
        } else if (place == 1) {
            Font.font.draw(batch, "Achat Affinite", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 14 / 2), getY() + getHeight() - getHeight() / 8);
            Font.font.draw(batch, "2000", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 4 / 2), getY() + getHeight() - getHeight() / 8 - Font.font.getLineHeight());
        } else if (place == 0) {
            Font.font.draw(batch, "Annuler", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 7 / 2), getY() + getHeight() - getHeight() / 4);
        }if (container.getContainer().getContainer().getEquipe().getOr() < 1000 && place != 0){
            batch.draw(Utili.passive, getX(), getY(), getWidth(), getHeight());
        }else if (container.getContainer().getContainer().getEquipe().getOr() < 2000 && place ==1){
            batch.draw(Utili.passive, getX(), getY(), getWidth(), getHeight());
        }
    }

    public void clique() {
        if (place == 1) {
        } else if (place == 2 && container.getContainer().getContainer().getEquipe().getOr() >= 1000) {
            container.getContainer().getContainer().achatAleat(container.getPlace());
        }
        container.getContainer().setVisible(false);
    }
}
