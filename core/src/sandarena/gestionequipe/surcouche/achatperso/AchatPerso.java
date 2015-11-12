package sandarena.gestionequipe.surcouche.achatperso;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.gestionequipe.surcouche.SurcoucheGestionEquipe;

/**
 * Created by lucmo on 20/10/2015.
 */
public class AchatPerso extends Group {
    private final SurcoucheGestionEquipe container;
    private byte place;

    public AchatPerso(SurcoucheGestionEquipe surcoucheGestionEquipe) {
        super();
        container = surcoucheGestionEquipe;
        setBounds(Resolution.width / 4, Resolution.height / 8, Resolution.width / 2, Resolution.height - Resolution.height/4);
        setVisible(false);
        addActor(new BoutonAchat(this, 0));
        addActor(new BoutonAchat(this,1));
        addActor(new BoutonAchat(this,2));
    }

    public void setVisible(byte place) {
        this.place = place;
        this.setVisible(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            batch.draw(Utili.passive, 0, 0, Resolution.width, Resolution.height);
            batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
            batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
            Font.font.setColor(Color.BLACK);
            Font.font.setScale(Resolution.ratioWidth * 5, Resolution.ratioHeight * 5);
            Font.font.draw(batch, "Nouveau Personnage", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 18 / 2), getY() + getHeight() - getHeight() / 20);
            super.draw(batch, parentAlpha);
        }
    }

    public SurcoucheGestionEquipe getContainer() {
        return container;
    }

    public byte getPlace() {
        return place;
    }
}
