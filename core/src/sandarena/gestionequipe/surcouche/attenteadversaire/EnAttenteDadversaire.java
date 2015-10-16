package sandarena.gestionequipe.surcouche.attenteadversaire;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.gestionequipe.surcouche.Surcouche;

/**
 * Created by lucmo on 13/10/2015.
 */
public class EnAttenteDadversaire extends Group {
    private final Surcouche container;

    public EnAttenteDadversaire(Surcouche surcouche) {
        super();
        container = surcouche;
        setBounds(Resolution.width / 4, Resolution.height / 4, Resolution.width / 2, Resolution.height / 2);
        this.addActor(new BouttonAnnu(this));
        setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            batch.draw(Utili.passive, 0, 0, Resolution.width, Resolution.height);
            batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
            batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
            Font.font.setColor(Color.BLACK);
            Font.font.setScale(Resolution.ratioWidth * 6, Resolution.ratioHeight * 6);
            Font.font.draw(batch, "En attente", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 10 / 2), getY() + getHeight() - getHeight() / 8);
            Font.font.draw(batch, "d'adversaire", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 12 / 2), getY() + getHeight() - getHeight() / 8 - Font.font.getLineHeight());
            super.draw(batch, parentAlpha);
        }
    }

    public Surcouche getContainer() {
        return container;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }
}
