package sandarena.selectionequipe.Surcouche.nouvelleequipe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 04/11/2015.
 */
public class BoutonAccepte extends Actor {
    private final PanneauEquipe container;

    public BoutonAccepte(PanneauEquipe panneauEquipe) {
        this.container = panneauEquipe;
        setBounds(container.getWidth() / 2 - container.getWidth() / 8, container.getHeight() / 14, container.getWidth() / 4, container.getHeight() / 6);
        this.addListener(new BoutonAccepteListener(this));
        this.setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        Font.font.setColor(Color.BLACK);
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        Font.font.draw(batch, "Accepter", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 8 / 2),getY()+ getHeight() - (getHeight() / 10));
        super.draw(batch, parentAlpha);
    }

    public void clique() {
        Son.menuSelect.play();
        container.select();
    }

    public void dispose() {

    }
}
